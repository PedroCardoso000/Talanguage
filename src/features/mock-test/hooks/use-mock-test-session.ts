"use client";

import { useEffect, useMemo, useState } from "react";

import { mockTestApi } from "@/features/mock-test/api/mock-test-api";
import type { CurrentMockTestResponse, MockTestQuestion, MockTestResult } from "@/features/mock-test/contracts";
import { useAppStore } from "@/store/use-app-store";

type MockTestSessionState = {
  currentTest: CurrentMockTestResponse | null;
  questions: MockTestQuestion[];
  answers: Record<string, string>;
  result: MockTestResult | null;
  isLoading: boolean;
  isSubmitting: boolean;
  errorMessage: string | null;
  canSubmit: boolean;
  selectAnswer: (questionId: string, option: string) => void;
  submit: () => Promise<void>;
};

export function useMockTestSession(): MockTestSessionState {
  const completeMockTest = useAppStore((state) => state.completeMockTest);
  const [currentTest, setCurrentTest] = useState<CurrentMockTestResponse | null>(null);
  const [answers, setAnswers] = useState<Record<string, string>>({});
  const [result, setResult] = useState<MockTestResult | null>(null);
  const [isLoading, setIsLoading] = useState(true);
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [errorMessage, setErrorMessage] = useState<string | null>(null);

  useEffect(() => {
    let cancelled = false;

    mockTestApi
      .getCurrent()
      .then((response) => {
        if (cancelled) {
          return;
        }

        setCurrentTest(response);
        setErrorMessage(null);
        setIsLoading(false);
      })
      .catch((error) => {
        if (cancelled) {
          return;
        }

        setErrorMessage(error instanceof Error ? error.message : "Nao foi possivel carregar o simulado.");
        setIsLoading(false);
      });

    return () => {
      cancelled = true;
    };
  }, []);

  const questions = currentTest?.questions ?? [];

  const canSubmit = useMemo(
    () => questions.length > 0 && Object.keys(answers).length === questions.length && !result,
    [answers, questions.length, result],
  );

  function selectAnswer(questionId: string, option: string) {
    if (result) {
      return;
    }

    setAnswers((current) => ({
      ...current,
      [questionId]: option,
    }));
  }

  async function submit() {
    if (!currentTest) {
      return;
    }

    setIsSubmitting(true);
    setErrorMessage(null);

    try {
      const summary = await mockTestApi.submit({
        mockTestId: currentTest.id,
        answers: Object.entries(answers).map(([questionId, selectedOption]) => ({
          questionId,
          selectedOption,
        })),
      });
      completeMockTest(summary.score);
      setResult(summary);
    } catch (error) {
      setErrorMessage(error instanceof Error ? error.message : "Nao foi possivel finalizar o simulado.");
    } finally {
      setIsSubmitting(false);
    }
  }

  return {
    currentTest,
    questions,
    answers,
    result,
    isLoading,
    isSubmitting,
    errorMessage,
    canSubmit,
    selectAnswer,
    submit,
  };
}
