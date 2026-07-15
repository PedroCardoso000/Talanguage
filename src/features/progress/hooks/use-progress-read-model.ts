"use client";

import { useEffect, useState } from "react";

import { progressApi } from "@/features/progress/api/progress-api";
import {
  buildProgressReadModelFromResponses,
  type ProgressReadModel,
} from "@/features/progress/lib/read-model";

type ProgressReadModelState = {
  data: ProgressReadModel | null;
  isLoading: boolean;
  errorMessage: string | null;
};

export function useProgressReadModel(): ProgressReadModelState {
  const [state, setState] = useState<ProgressReadModelState>({
    data: null,
    isLoading: true,
    errorMessage: null,
  });

  useEffect(() => {
    let cancelled = false;

    Promise.all([
      progressApi.getSummary(),
      progressApi.getActivities(),
      progressApi.getWeeklySummary(),
    ])
      .then(([summary, activities, weeklySummary]) => {
        if (cancelled) {
          return;
        }

        setState({
          data: buildProgressReadModelFromResponses({
            summary,
            activities,
            weeklySummary,
          }),
          isLoading: false,
          errorMessage: null,
        });
      })
      .catch((error) => {
        if (cancelled) {
          return;
        }

        setState({
          data: null,
          isLoading: false,
          errorMessage: error instanceof Error ? error.message : "Nao foi possivel carregar o progresso agora.",
        });
      });

    return () => {
      cancelled = true;
    };
  }, []);

  return state;
}
