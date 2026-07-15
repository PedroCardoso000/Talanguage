export type LanguageOption = "Ingles" | "Espanhol" | "Frances";
export type LanguageLevelOption = "Basico" | "Intermediario" | "Avancado";

export type UserProfile = {
  id: string;
  name: string;
  email: string;
  targetLanguage: LanguageOption;
  currentLevel: LanguageLevelOption | null;
  studyGoal: string | null;
  avatarUrl: string | null;
};

export type ProgressData = {
  streak: number;
  daysPracticed: number;
  wordsReviewed: number;
  challengesCompleted: number;
  flashcardCorrect: number;
  flashcardWrong: number;
  overallProgress: number;
  minutesPracticed: number;
  speakingScore: number;
  writingScore: number;
  mockTestsTaken: number;
  level: string;
  lastPracticeDate: string | null;
  weeklyActivity: number[];
};

export type GoalsData = {
  dailyMinutes: number;
  weeklyMinutes: number;
  wordsPerDay: number;
  challengesPerWeek: number;
};

export type WritingChallenge = {
  id: string;
  title: string;
  prompt: string;
  focus: string;
};

export type Flashcard = {
  id: string;
  term: string;
  meaning: string;
  example: string;
  category: string;
};

export type MockQuestion = {
  id: string;
  question: string;
  options: string[];
  correctAnswer: string;
  explanation: string;
};
