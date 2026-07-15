"use client";

import { useEffect, useState } from "react";

import { dashboardApi } from "@/features/dashboard/api/dashboard-api";
import {
  buildDashboardReadModelFromSummary,
  type DashboardReadModel,
} from "@/features/progress/lib/read-model";
import { useAppStore } from "@/store/use-app-store";

type DashboardReadModelState = {
  data: DashboardReadModel | null;
  isLoading: boolean;
  errorMessage: string | null;
};

export function useDashboardReadModel(): DashboardReadModelState {
  const user = useAppStore((state) => state.user);
  const [state, setState] = useState<DashboardReadModelState>({
    data: null,
    isLoading: true,
    errorMessage: null,
  });

  useEffect(() => {
    let cancelled = false;

    dashboardApi
      .getSummary()
      .then((summary) => {
        if (cancelled) {
          return;
        }

        setState({
          data: buildDashboardReadModelFromSummary({ user, summary }),
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
          errorMessage: error instanceof Error ? error.message : "Nao foi possivel carregar o dashboard agora.",
        });
      });

    return () => {
      cancelled = true;
    };
  }, [user]);

  return state;
}
