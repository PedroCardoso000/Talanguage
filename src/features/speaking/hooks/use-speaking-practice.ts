"use client";

import { useEffect, useMemo, useState } from "react";

import { speakingApi } from "@/features/speaking/api/speaking-api";
import type {
  FinishSpeakingSessionResponse,
  RecentSpeakingSession,
  SpeakingTopic,
} from "@/features/speaking/contracts";
import { toSpeakingScenarioDisplay } from "@/features/speaking/mocks/scenarios";
import { useAppStore } from "@/store/use-app-store";

type ConversationMessage = {
  role: "mentor" | "user";
  content: string;
};

export function useSpeakingPractice() {
  const completeSpeakSession = useAppStore((state) => state.completeSpeakSession);
  const progress = useAppStore((state) => state.progress);
  const goals = useAppStore((state) => state.goals);
  const [topics, setTopics] = useState<SpeakingTopic[]>([]);
  const [selectedTopicId, setSelectedTopicId] = useState<string | null>(null);
  const [activeSessionId, setActiveSessionId] = useState<string | null>(null);
  const [currentPrompt, setCurrentPrompt] = useState<string | null>(null);
  const [conversation, setConversation] = useState<ConversationMessage[]>([]);
  const [answer, setAnswer] = useState("");
  const [summary, setSummary] = useState<FinishSpeakingSessionResponse["summary"] | null>(null);
  const [recentSessions, setRecentSessions] = useState<RecentSpeakingSession[]>([]);
  const [isLoadingTopics, setIsLoadingTopics] = useState(true);
  const [isStarting, setIsStarting] = useState(false);
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [isFinishing, setIsFinishing] = useState(false);
  const [errorMessage, setErrorMessage] = useState<string | null>(null);
  const [successMessage, setSuccessMessage] = useState<string | null>(null);

  async function activateTopic(topic: SpeakingTopic) {
    setSelectedTopicId(topic.id);
    setIsStarting(true);
    setErrorMessage(null);
    setSuccessMessage(null);
    setSummary(null);
    setAnswer("");
    setConversation([]);

    try {
      const session = await speakingApi.startSession({
        language: topic.language,
        level: topic.level,
        topicId: topic.id,
      });

      setActiveSessionId(session.sessionId);
      setCurrentPrompt(session.prompt);
      setConversation([{ role: "mentor", content: session.prompt }]);
    } catch (error) {
      setActiveSessionId(null);
      setCurrentPrompt(null);
      setConversation([]);
      setErrorMessage(error instanceof Error ? error.message : "Could not start the speaking session.");
    } finally {
      setIsStarting(false);
    }
  }

  useEffect(() => {
    let cancelled = false;

    Promise.all([speakingApi.getTopics(), speakingApi.getRecentSessions()])
      .then(([topicList, recentList]) => {
        if (cancelled) {
          return;
        }

        setTopics(topicList);
        setRecentSessions(recentList);
        setIsLoadingTopics(false);

        if (topicList[0]) {
          void activateTopic(topicList[0]);
        }
      })
      .catch((error) => {
        if (cancelled) {
          return;
        }

        setErrorMessage(error instanceof Error ? error.message : "Could not load speaking topics.");
        setIsLoadingTopics(false);
      });

    return () => {
      cancelled = true;
    };
  }, []);

  const currentTopic = useMemo(() => {
    if (!selectedTopicId) {
      return null;
    }

    return topics.find((topic) => topic.id === selectedTopicId) ?? null;
  }, [selectedTopicId, topics]);

  const currentScenario = useMemo(() => {
    return currentTopic ? toSpeakingScenarioDisplay(currentTopic) : null;
  }, [currentTopic]);

  const isBusy = isLoadingTopics || isStarting || isSubmitting || isFinishing;
  const canSend = Boolean(activeSessionId) && answer.trim().length > 0 && !isBusy;
  const canFinish = Boolean(activeSessionId) && conversation.some((message) => message.role === "user") && !isBusy;
  const dailyTarget = Math.max(goals.dailyMinutes, 1);
  const practicedMinutes = Math.min(progress.minutesPracticed, dailyTarget);
  const missingMinutes = Math.max(dailyTarget - practicedMinutes, 0);
  const progressPercent = Math.min(Math.round((practicedMinutes / dailyTarget) * 100), 100);
  const latestSession = recentSessions[0] ?? null;

  function nextScenario() {
    if (topics.length === 0 || !selectedTopicId) {
      return;
    }

    const currentIndex = topics.findIndex((topic) => topic.id === selectedTopicId);
    const nextIndex = currentIndex >= 0 ? (currentIndex + 1) % topics.length : 0;
    const nextTopic = topics[nextIndex];

    if (nextTopic) {
      void activateTopic(nextTopic);
    }
  }

  function updateAnswer(value: string) {
    setAnswer(value);
    setErrorMessage(null);
    setSuccessMessage(null);
  }

  async function sendMessage() {
    if (!activeSessionId) {
      setErrorMessage("No active speaking session was found.");
      return;
    }

    if (!answer.trim()) {
      setErrorMessage("Write your answer before sending it.");
      return;
    }

    setIsSubmitting(true);
    setErrorMessage(null);
    setSuccessMessage(null);

    const userAnswer = answer.trim();

    try {
      const response = await speakingApi.submitResponse(activeSessionId, {
        content: userAnswer,
      });

      setConversation((current) => [
        ...current,
        { role: "user", content: userAnswer },
        { role: "mentor", content: response.nextPrompt },
      ]);
      setCurrentPrompt(response.nextPrompt);
      setAnswer("");
      setSuccessMessage("Mensagem registrada. Continue a conversa ou finalize quando fizer sentido.");
    } catch (error) {
      setErrorMessage(error instanceof Error ? error.message : "Could not submit your message right now.");
    } finally {
      setIsSubmitting(false);
    }
  }

  async function finishSession() {
    if (!activeSessionId) {
      setErrorMessage("No active speaking session was found.");
      return;
    }

    setIsFinishing(true);
    setErrorMessage(null);
    setSuccessMessage(null);

    try {
      const finishedSession = await speakingApi.finishSession(activeSessionId);
      completeSpeakSession();
      setSummary(finishedSession.summary);
      setRecentSessions(await speakingApi.getRecentSessions());
      setActiveSessionId(null);
      setAnswer("");
      setSuccessMessage("Sessao de fala concluida com sucesso.");
    } catch (error) {
      setErrorMessage(error instanceof Error ? error.message : "Could not complete the speaking session.");
    } finally {
      setIsFinishing(false);
    }
  }

  return {
    currentScenario,
    currentPrompt,
    conversation,
    answer,
    summary,
    latestSession,
    isLoadingTopics,
    isStarting,
    isSubmitting,
    isFinishing,
    isBusy,
    canSend,
    canFinish,
    errorMessage,
    successMessage,
    practicedMinutes,
    dailyTarget,
    missingMinutes,
    progressPercent,
    updateAnswer,
    nextScenario,
    sendMessage,
    finishSession,
  };
}
