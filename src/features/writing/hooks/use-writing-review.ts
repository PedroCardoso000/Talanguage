"use client";

import { useEffect, useMemo, useState } from "react";

import { writingApi } from "@/features/writing/api/writing-api";
import type {
  RecentWritingSubmission,
  WritingChallenge,
  WritingReviewFeedback,
} from "@/features/writing/contracts";
import { toWritingChallengeDisplay } from "@/features/writing/mocks/challenges";
import { useAppStore } from "@/store/use-app-store";

type WritingChallengeViewModel = ReturnType<typeof toWritingChallengeDisplay>;

type WritingFeedbackViewModel = {
  strengths: string[];
  improvements: string[];
  nextAction: string;
};

function toFeedbackViewModel(feedback: WritingReviewFeedback): WritingFeedbackViewModel {
  return {
    strengths: feedback.strengths,
    improvements: feedback.improvements,
    nextAction: feedback.nextAction,
  };
}

export function useWritingReview() {
  const completeWritingChallenge = useAppStore((state) => state.completeWritingChallenge);
  const [challenges, setChallenges] = useState<WritingChallenge[]>([]);
  const [recentSubmissions, setRecentSubmissions] = useState<RecentWritingSubmission[]>([]);
  const [selectedChallengeId, setSelectedChallengeId] = useState<string | null>(null);
  const [text, setText] = useState("");
  const [feedback, setFeedback] = useState<WritingFeedbackViewModel | null>(null);
  const [isLoading, setIsLoading] = useState(true);
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [errorMessage, setErrorMessage] = useState<string | null>(null);
  const [successMessage, setSuccessMessage] = useState<string | null>(null);

  useEffect(() => {
    let cancelled = false;

    Promise.all([
      writingApi.getChallenges(),
      writingApi.getRecentSubmissions(),
      writingApi.getCurrentChallenge(),
    ])
      .then(([challengeList, recentList, currentChallenge]) => {
        if (cancelled) {
          return;
        }

        setChallenges(challengeList);
        setRecentSubmissions(recentList);
        setSelectedChallengeId((current) => current ?? currentChallenge?.id ?? challengeList[0]?.id ?? null);
        setIsLoading(false);
      })
      .catch((error) => {
        if (cancelled) {
          return;
        }

        setErrorMessage(error instanceof Error ? error.message : "Could not load the writing review flow.");
        setIsLoading(false);
      });

    return () => {
      cancelled = true;
    };
  }, []);

  const currentChallenge = useMemo<WritingChallengeViewModel | null>(() => {
    if (!selectedChallengeId) {
      return null;
    }

    const challenge = challenges.find((item) => item.id === selectedChallengeId);
    return challenge ? toWritingChallengeDisplay(challenge) : null;
  }, [challenges, selectedChallengeId]);

  const canSubmit = text.trim().length > 0 && Boolean(currentChallenge) && !isSubmitting;
  const isEmpty = !isLoading && challenges.length === 0;
  const lastSubmission = recentSubmissions[0] ?? null;

  function updateText(value: string) {
    setText(value);
    setFeedback(null);
    setErrorMessage(null);
    setSuccessMessage(null);
  }

  function nextChallenge() {
    if (challenges.length === 0 || !selectedChallengeId) {
      return;
    }

    const currentIndex = challenges.findIndex((item) => item.id === selectedChallengeId);
    const nextIndex = currentIndex >= 0 ? (currentIndex + 1) % challenges.length : 0;

    setSelectedChallengeId(challenges[nextIndex]?.id ?? null);
    setText("");
    setFeedback(null);
    setErrorMessage(null);
    setSuccessMessage(null);
  }

  async function submit() {
    if (!currentChallenge) {
      setErrorMessage("No writing challenge is selected.");
      return;
    }

    if (!text.trim()) {
      setErrorMessage("Write an answer before asking for feedback.");
      return;
    }

    setIsSubmitting(true);
    setErrorMessage(null);
    setSuccessMessage(null);

    try {
      const response = await writingApi.submit({
        challengeId: currentChallenge.id,
        content: text,
      });

      completeWritingChallenge();
      setFeedback(toFeedbackViewModel(response.feedback));
      setRecentSubmissions(await writingApi.getRecentSubmissions());
      setText("");
      setSuccessMessage("Texto revisado com sucesso.");
    } catch (error) {
      setErrorMessage(error instanceof Error ? error.message : "Could not submit your writing right now.");
    } finally {
      setIsSubmitting(false);
    }
  }

  return {
    currentChallenge,
    text,
    feedback,
    isLoading,
    isSubmitting,
    isEmpty,
    canSubmit,
    errorMessage,
    successMessage,
    lastSubmission,
    updateText,
    nextChallenge,
    submit,
  };
}
