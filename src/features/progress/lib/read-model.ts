import type { DashboardSummaryResponse } from "@/features/dashboard/contracts";
import type {
  ProgressActivity,
  ProgressSummaryResponse,
  WeeklyProgressSummary,
} from "@/features/progress/contracts";
import type { UserProfile } from "@/types";

export type DashboardReadModel = DashboardSummaryResponse & {
  targetLanguage: string;
};

export type ProgressReadModel = {
  summary: ProgressSummaryResponse;
  activities: ProgressActivity[];
  weeklySummary: WeeklyProgressSummary;
  weeklyActivity: number[];
  isEmpty: boolean;
  levelStatus: string;
};

export function buildDashboardReadModelFromSummary(
  source: {
    user: UserProfile | null;
    summary: DashboardSummaryResponse;
  },
): DashboardReadModel {
  return {
    ...source.summary,
    targetLanguage: source.user?.targetLanguage ?? "Ingles",
  };
}

export function buildProgressReadModelFromResponses(source: {
  summary: ProgressSummaryResponse;
  activities: ProgressActivity[];
  weeklySummary: WeeklyProgressSummary;
}): ProgressReadModel {
  return {
    summary: source.summary,
    activities: source.activities,
    weeklySummary: source.weeklySummary,
    weeklyActivity: source.weeklySummary.dailyActivityCounts,
    isEmpty: source.summary.totalActivities === 0,
    levelStatus: "Ainda indisponivel",
  };
}
