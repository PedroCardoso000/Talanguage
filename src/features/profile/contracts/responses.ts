export type ProfileResponse = {
  id: string;
  name: string;
  email: string;
  targetLanguage: "ENGLISH" | "SPANISH" | "FRENCH" | null;
  currentLevel: "BEGINNER" | "INTERMEDIATE" | "ADVANCED" | null;
  studyGoal: string | null;
  avatarUrl: string | null;
};
