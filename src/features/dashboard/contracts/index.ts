export type DashboardActivityType = "SPEAKING" | "WRITING" | "FLASHCARDS" | "GOALS" | "MOCK_TEST";

export type DashboardSummaryResponse = {
  userName: string;
  currentStreakDays: number;
  completedActivitiesToday: number;
  weeklyActivityCount: number;
  goalCompletionPercent: number;
  moduleProgress: Record<string, number>;
  weeklyActivity: number[];
  dailyGoal: {
    target: number;
    completed: number;
  };
  skillProgress: {
    speaking: number;
    writing: number;
    vocabulary: number;
    consistency: number;
  };
  recentActivities: Array<{
    id: string;
    type: DashboardActivityType;
    title: string;
    completedAt: string;
  }>;
  nextSuggestedAction: {
    type: DashboardActivityType;
    label: string;
    route: string;
  };
};
