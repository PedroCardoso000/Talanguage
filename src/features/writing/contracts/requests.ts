export type WritingLanguage = "ENGLISH" | "SPANISH" | "FRENCH";
export type WritingLevel = "BEGINNER" | "INTERMEDIATE" | "ADVANCED";

export type GetWritingChallengesQuery = {
  language?: WritingLanguage;
  level?: WritingLevel;
};

export type CreateWritingSubmissionRequest = {
  challengeId: string;
  content: string;
};
