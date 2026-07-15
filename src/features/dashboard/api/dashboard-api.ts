import type { DashboardSummaryResponse } from "@/features/dashboard/contracts";
import { httpClient } from "@/api/http-client";
import { getAuthorizationHeaders } from "@/features/auth/api/auth-headers";

const DASHBOARD_BASE_PATH = "/api/dashboard";

export const dashboardApi = {
  getSummary(): Promise<DashboardSummaryResponse> {
    return httpClient.get<DashboardSummaryResponse>(`${DASHBOARD_BASE_PATH}/summary`, {
      headers: getAuthorizationHeaders(),
    });
  },
};
