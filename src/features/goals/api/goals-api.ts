import type {
  GoalSettingsResponse,
  UpdateGoalSettingsRequest,
} from "@/features/goals/contracts";
import { httpClient } from "@/api/http-client";
import { getAuthorizationHeaders } from "@/features/auth/api/auth-headers";

const GOALS_BASE_PATH = "/api/goals";

export const goalsApi = {
  getGoals(): Promise<GoalSettingsResponse> {
    return httpClient.get<GoalSettingsResponse>(GOALS_BASE_PATH, {
      headers: getAuthorizationHeaders(),
    });
  },

  updateGoals(payload: UpdateGoalSettingsRequest): Promise<GoalSettingsResponse> {
    return httpClient.put<GoalSettingsResponse, UpdateGoalSettingsRequest>(GOALS_BASE_PATH, payload, {
      headers: getAuthorizationHeaders(),
    });
  },
};
