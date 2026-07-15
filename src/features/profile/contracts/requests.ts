export type UpdateProfileRequest = {
  name: string;
  targetLanguage: "ENGLISH" | "SPANISH" | "FRENCH" | null;
  currentLevel: "BEGINNER" | "INTERMEDIATE" | "ADVANCED" | null;
  studyGoal: string | null;
  avatarUrl: string | null;
};
