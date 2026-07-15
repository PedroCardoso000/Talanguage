"use client";

import { useEffect, useState } from "react";

import { ApiClientError } from "@/api/http-client";
import { toUserProfile } from "@/features/auth/lib/session";
import { profileApi } from "@/features/profile/api/profile-api";
import { toProfileForm, toProfileRequest, type ProfileFormValues } from "@/features/profile/lib/profile-session";
import { useAppStore } from "@/store/use-app-store";

type ProfileSettingsState = {
  form: ProfileFormValues;
  isLoading: boolean;
  isSaving: boolean;
  errorMessage: string | null;
  successMessage: string | null;
};

const EMPTY_FORM: ProfileFormValues = {
  name: "",
  email: "",
  targetLanguage: "Ingles",
  currentLevel: null,
  studyGoal: "",
  avatarUrl: "",
};

export function useProfileSettings() {
  const setUser = useAppStore((state) => state.setUser);
  const [state, setState] = useState<ProfileSettingsState>({
    form: EMPTY_FORM,
    isLoading: true,
    isSaving: false,
    errorMessage: null,
    successMessage: null,
  });

  useEffect(() => {
    let cancelled = false;

    profileApi
      .getProfile()
      .then((profile) => {
        if (cancelled) {
          return;
        }

        setUser(toUserProfile(profile));
        setState({
          form: toProfileForm(profile),
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
          errorMessage: error instanceof Error ? error.message : "Nao foi possivel carregar seu perfil.",
        }));
      });

    return () => {
      cancelled = true;
    };
  }, [setUser]);

  function updateField<K extends keyof ProfileFormValues>(field: K, value: ProfileFormValues[K]) {
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
      const savedProfile = await profileApi.updateProfile(toProfileRequest(state.form));
      setUser(toUserProfile(savedProfile));
      setState((current) => ({
        ...current,
        form: toProfileForm(savedProfile),
        isSaving: false,
        successMessage: "Perfil salvo com sucesso.",
      }));
    } catch (error) {
      const message =
        error instanceof ApiClientError || error instanceof Error
          ? error.message
          : "Nao foi possivel salvar seu perfil agora.";

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
