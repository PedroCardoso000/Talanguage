export type RegisterRequest = {
  name: string;
  email: string;
  password: string;
  targetLanguage: "ENGLISH" | "SPANISH" | "FRENCH";
};

export type LoginRequest = {
  email: string;
  password: string;
};
