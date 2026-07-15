import type { ProgressData } from "@/types";

export const weeklyLabels = ["Seg", "Ter", "Qua", "Qui", "Sex", "Sab", "Dom"];

export const initialProgress: ProgressData = {
  streak: 0,
  daysPracticed: 0,
  wordsReviewed: 0,
  challengesCompleted: 0,
  flashcardCorrect: 0,
  flashcardWrong: 0,
  overallProgress: 0,
  minutesPracticed: 0,
  speakingScore: 0,
  writingScore: 0,
  mockTestsTaken: 0,
  level: "Nao estimado",
  lastPracticeDate: null,
  weeklyActivity: [0, 0, 0, 0, 0, 0, 0],
};
