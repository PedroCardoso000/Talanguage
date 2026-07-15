"use client";

import { useEffect, useState } from "react";

import { ApiClientError } from "@/api/http-client";
import { goalsApi } from "@/features/goals/api/goals-api";
import type {
  GoalSettingsResponse,
  UpdateGoalSettingsRequest,
} from "@/features/goals/contracts";
import { useAppStore } from "@/store/use-app-store";

type GoalsSettingsState = {
  form: GoalSettingsResponse;
  isLoading: boolean;
  isSaving: boolean;
  errorMessage: string | null;
  successMessage: string | null;
};

const EMPTY_GOALS: GoalSettingsResponse = {
  dailyMinutes: 0,
  weeklyMinutes: 0,
  wordsPerDay: 0,
  challengesPerWeek: 0,
};

export function useGoalsSettings() {
  const setGoals = useAppStore((state) => state.saveGoals);
  const [state, setState] = useState<GoalsSettingsState>({
    form: EMPTY_GOALS,
    isLoading: true,
    isSaving: false,
    errorMessage: null,
    successMessage: null,
  });

  useEffect(() => {
    let cancelled = false;

    goalsApi
      .getGoals()
      .then((goals) => {
        if (cancelled) {
          return;
        }

        setGoals(goals);
        setState({
          form: goals,
          isLoading: false,
          isSaving: false,
          errorMessage: null,
          successMessage: null,
        });
      })
      .catch((error) => {
        if (cancelled) {
          return;
        }

        setState((current) => ({
          ...current,
          isLoading: false,
          errorMessage: error instanceof Error ? error.message : "Nao foi possivel carregar as metas.",
        }));
      });

    return () => {
      cancelled = true;
    };
  }, [setGoals]);

  function updateField<K extends keyof GoalSettingsResponse>(field: K, value: GoalSettingsResponse[K]) {
    setState((current) => ({
      ...current,
      form: {
        ...current.form,
        [field]: value,
      },
      errorMessage: null,
      successMessage: null,
    }));
  }

  async function submit() {
    setState((current) => ({
      ...current,
      isSaving: true,
      errorMessage: null,
      successMessage: null,
    }));

    try {
      const savedGoals = await goalsApi.updateGoals(state.form as UpdateGoalSettingsRequest);
      setGoals(savedGoals);
      setState((current) => ({
        ...current,
        form: savedGoals,
        isSaving: false,
        successMessage: "Metas salvas com sucesso.",
      }));
    } catch (error) {
      const message =
        error instanceof ApiClientError || error instanceof Error
          ? error.message
          : "Nao foi possivel salvar as metas agora.";

      setState((current) => ({
        ...current,
        isSaving: false,
        errorMessage: message,
      }));
    }
  }

  return {
    ...state,
    updateField,
    submit,
  };
}
