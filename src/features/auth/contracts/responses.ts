export type AuthUser = {
  id: string;
  name: string;
  email: string;
  targetLanguage: "ENGLISH" | "SPANISH" | "FRENCH" | null;
  currentLevel: "BEGINNER" | "INTERMEDIATE" | "ADVANCED" | null;
  studyGoal: string | null;
  avatarUrl: string | null;
};

export type AuthSessionResponse = {
  user: AuthUser;
  accessToken: string;
};

export type CurrentSessionResponse = AuthUser;
