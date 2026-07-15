export type ProgressSummaryResponse = {
  streakDays: number;
  longestStreakDays: number;
  lastActivityDate: string | null;
  dailyGoal: {
    target: number;
    completed: number;
    status: "NOT_STARTED" | "IN_PROGRESS" | "COMPLETED";
  };
  skillProgress: {
    speaking: number;
    writing: number;
    vocabulary: number;
    consistency: number;
  };
  totalActivities: number;
  activityTotals: {
    daysPracticed: number;
    speakingSessions: number;
    writingSubmissions: number;
    flashcardReviews: number;
    mockTestsCompleted: number;
    goalsUpdated: number;
  };
};

export type ProgressActivity = {
  id: string;
  type: "SPEAKING" | "WRITING" | "FLASHCARDS" | "GOALS" | "MOCK_TEST";
  title: string;
  occurredAt: string;
};

export type WeeklyProgressSummary = {
  weekStart: string;
  weekEnd: string;
  activitiesCompleted: number;
  streakDays: number;
  skillProgress: ProgressSummaryResponse["skillProgress"];
  dailyActivityCounts: number[];
};
