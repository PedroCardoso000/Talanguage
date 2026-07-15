"use client";

import { create } from "zustand";
import { persist } from "zustand/middleware";

import { initialGoals } from "@/features/goals/mocks/goals";
import { initialProgress } from "@/features/progress/mocks/progress";
import { clamp, getTodayKey, getWeekIndex } from "@/lib/utils";
import { GoalsData, ProgressData, UserProfile } from "@/types";

type AppState = {
  user: UserProfile | null;
  progress: ProgressData;
  goals: GoalsData;
  setUser: (user: UserProfile | null) => void;
  clearUser: () => void;
  completeSpeakSession: () => void;
  completeWritingChallenge: () => void;
  recordFlashcardResult: (knew: boolean) => void;
  saveGoals: (goals: GoalsData) => void;
  completeMockTest: (score: number) => void;
};

function applyActivity(progress: ProgressData, minutes: number) {
  const today = getTodayKey();
  const alreadyPracticedToday = progress.lastPracticeDate === today;
  const weeklyActivity = [...progress.weeklyActivity];
  const weekIndex = getWeekIndex();
  weeklyActivity[weekIndex] = (weeklyActivity[weekIndex] ?? 0) + minutes;

  return {
    ...progress,
    weeklyActivity,
    minutesPracticed: progress.minutesPracticed + minutes,
    daysPracticed: alreadyPracticedToday
      ? progress.daysPracticed
      : progress.daysPracticed + 1,
    streak: alreadyPracticedToday ? progress.streak : progress.streak + 1,
    lastPracticeDate: today,
  };
}

export const useAppStore = create<AppState>()(
  persist(
    (set) => ({
      user: null,
      progress: initialProgress,
      goals: initialGoals,
      setUser: (user) => set(() => ({ user })),
      clearUser: () => set(() => ({ user: null })),
      completeSpeakSession: () =>
        set((state) => {
          const progress = applyActivity(state.progress, 12);
          return {
            progress: {
              ...progress,
              challengesCompleted: progress.challengesCompleted + 1,
              overallProgress: clamp(progress.overallProgress + 4, 0, 100),
              speakingScore: clamp(progress.speakingScore + 2, 0, 100),
            },
          };
        }),
      completeWritingChallenge: () =>
        set((state) => {
          const progress = applyActivity(state.progress, 15);
          return {
            progress: {
              ...progress,
              challengesCompleted: progress.challengesCompleted + 1,
              overallProgress: clamp(progress.overallProgress + 5, 0, 100),
              writingScore: clamp(progress.writingScore + 3, 0, 100),
            },
          };
        }),
      recordFlashcardResult: (knew) =>
        set((state) => {
          const progress = applyActivity(state.progress, 4);
          return {
            progress: {
              ...progress,
              wordsReviewed: progress.wordsReviewed + 1,
              overallProgress: clamp(progress.overallProgress + 1, 0, 100),
              flashcardCorrect: knew
                ? progress.flashcardCorrect + 1
                : progress.flashcardCorrect,
              flashcardWrong: knew
                ? progress.flashcardWrong
                : progress.flashcardWrong + 1,
            },
          };
        }),
      saveGoals: (goals) => set(() => ({ goals })),
      completeMockTest: (score) =>
        set((state) => {
          const progress = applyActivity(state.progress, 18);
          const nextLevel =
            score >= 4 ? "Intermediario forte" : score >= 2 ? "Intermediario" : "Basico";

          return {
            progress: {
              ...progress,
              challengesCompleted: progress.challengesCompleted + 1,
              overallProgress: clamp(progress.overallProgress + score * 2, 0, 100),
              mockTestsTaken: progress.mockTestsTaken + 1,
              level: nextLevel,
            },
          };
        }),
    }),
    {
      name: "tala-language-store",
    },
  ),
);
