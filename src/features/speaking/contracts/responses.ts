import type { SpeakingLevel } from "@/features/speaking/contracts/requests";

export type SpeakingSessionStatus = "IN_PROGRESS" | "FINISHED";

export type SpeakingTopic = {
  id: string;
  title: string;
  language: "ENGLISH" | "SPANISH";
  level: SpeakingLevel;
  category: string;
  objective: string;
  mentorMessage: string;
  initialPrompt: string;
};

export type StartSpeakingSessionResponse = {
  sessionId: string;
  status: SpeakingSessionStatus;
  startedAt: string;
  prompt: string;
};

export type SubmitSpeakingResponseResponse = {
  nextPrompt: string;
};

export type FinishSpeakingSessionResponse = {
  sessionId: string;
  status: "FINISHED";
  summary: {
    totalMessages: number;
    approximateDurationMinutes: number;
    topicTitle: string;
    feedback: string;
    nextAction: string;
  };
};

export type RecentSpeakingSession = {
  sessionId: string;
  topicId: string;
  topicTitle: string;
  startedAt: string;
  finishedAt: string;
  totalMessages: number;
  approximateDurationMinutes: number;
  feedback: string;
  nextAction: string;
};
