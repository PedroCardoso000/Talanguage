export type SpeakingLanguage = "ENGLISH" | "SPANISH";
export type SpeakingLevel = "BEGINNER" | "INTERMEDIATE" | "ADVANCED";

export type StartSpeakingSessionRequest = {
  language: SpeakingLanguage;
  level: SpeakingLevel;
  topicId: string;
};

export type SubmitSpeakingResponseRequest = {
  content: string;
};
