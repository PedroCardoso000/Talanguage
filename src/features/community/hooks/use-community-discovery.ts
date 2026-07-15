"use client";

import { useEffect, useState } from "react";

import { communityApi } from "@/features/community/api/community-api";
import type {
  CommunityInterestTargetType,
  CommunityLanguage,
  PracticeGroup,
  PracticePartner,
} from "@/features/community/contracts";

type CommunityState = {
  groups: PracticeGroup[];
  partners: PracticePartner[];
  selectedLanguage: CommunityLanguage | "ALL";
  loading: boolean;
  errorMessage: string | null;
  activeInterestTarget: string | null;
  interestedTargets: Set<string>;
};

export function useCommunityDiscovery() {
  const [state, setState] = useState<CommunityState>({
    groups: [],
    partners: [],
    selectedLanguage: "ALL",
    loading: true,
    errorMessage: null,
    activeInterestTarget: null,
    interestedTargets: new Set<string>(),
  });

  useEffect(() => {
    let cancelled = false;
    const language = state.selectedLanguage === "ALL" ? undefined : state.selectedLanguage;

    setState((current) => ({ ...current, loading: true, errorMessage: null }));

    Promise.all([
      communityApi.listGroups({ language }),
      communityApi.listPartners({ language }),
    ])
      .then(([groups, partners]) => {
        if (cancelled) {
          return;
        }

        setState((current) => ({
          ...current,
          groups,
          partners,
          loading: false,
          errorMessage: null,
        }));
      })
      .catch((error) => {
        if (cancelled) {
          return;
        }

        setState((current) => ({
          ...current,
          loading: false,
          errorMessage:
            error instanceof Error
              ? error.message
              : "Nao foi possivel carregar a comunidade agora.",
        }));
      });

    return () => {
      cancelled = true;
    };
  }, [state.selectedLanguage]);

  function changeLanguage(language: CommunityLanguage | "ALL") {
    setState((current) => ({ ...current, selectedLanguage: language }));
  }

  async function registerInterest(targetType: CommunityInterestTargetType, targetId: string) {
    setState((current) => ({ ...current, activeInterestTarget: targetId, errorMessage: null }));

    try {
      await communityApi.registerInterest({ targetType, targetId });
      setState((current) => ({
        ...current,
        activeInterestTarget: null,
        interestedTargets: new Set([...current.interestedTargets, targetId]),
      }));
    } catch (error) {
      setState((current) => ({
        ...current,
        activeInterestTarget: null,
        errorMessage:
          error instanceof Error
            ? error.message
            : "Nao foi possivel registrar seu interesse agora.",
      }));
    }
  }

  return {
    ...state,
    isEmpty: !state.loading && state.groups.length === 0 && state.partners.length === 0,
    changeLanguage,
    registerInterest,
  };
}
