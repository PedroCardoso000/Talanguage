"use client";

import { useEffect, useState } from "react";

import { progressApi } from "@/features/progress/api/progress-api";
import type { ProgressSummaryResponse } from "@/features/progress/contracts";

type ProgressSummaryState = {
  summary: ProgressSummaryResponse | null;
  isLoading: boolean;
  errorMessage: string | null;
};

export function useProgressSummary(): ProgressSummaryState {
  const [state, setState] = useState<ProgressSummaryState>({
    summary: null,
    isLoading: true,
    errorMessage: null,
  });

  useEffect(() => {
    let cancelled = false;

    progressApi
      .getSummary()
      .then((summary) => {
        if (cancelled) {
          return;
        }

        setState({
          summary,
          isLoading: false,
          errorMessage: null,
        });
      })
      .catch((error) => {
        if (cancelled) {
          return;
        }

        setState({
          summary: null,
          isLoading: false,
          errorMessage: error instanceof Error ? error.message : "Nao foi possivel carregar o streak agora.",
        });
      });

    return () => {
      cancelled = true;
    };
  }, []);

  return state;
}
