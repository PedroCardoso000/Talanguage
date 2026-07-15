import type {
  ProgressActivity,
  ProgressSummaryResponse,
  WeeklyProgressSummary,
} from "@/features/progress/contracts";
import { httpClient } from "@/api/http-client";
import { getAuthorizationHeaders } from "@/features/auth/api/auth-headers";

const PROGRESS_BASE_PATH = "/api/progress";

export const progressApi = {
  getSummary(): Promise<ProgressSummaryResponse> {
    return httpClient.get<ProgressSummaryResponse>(`${PROGRESS_BASE_PATH}/summary`, {
      headers: getAuthorizationHeaders(),
    });
  },

  getActivities(): Promise<ProgressActivity[]> {
    return httpClient.get<ProgressActivity[]>(`${PROGRESS_BASE_PATH}/activities`, {
      headers: getAuthorizationHeaders(),
    });
  },

  getWeeklySummary(): Promise<WeeklyProgressSummary> {
    return httpClient.get<WeeklyProgressSummary>(`${PROGRESS_BASE_PATH}/weekly-summary`, {
      headers: getAuthorizationHeaders(),
    });
  },
};
