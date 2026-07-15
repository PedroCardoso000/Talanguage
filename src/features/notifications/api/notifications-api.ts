import { httpClient } from "@/api/http-client";
import { getAuthorizationHeaders } from "@/features/auth/api/auth-headers";
import type { NotificationResponse } from "@/features/notifications/contracts";

const NOTIFICATIONS_BASE_PATH = "/api/notifications";

export const notificationsApi = {
  getNotifications(): Promise<NotificationResponse[]> {
    return httpClient.get<NotificationResponse[]>(NOTIFICATIONS_BASE_PATH, {
      headers: getAuthorizationHeaders(),
    });
  },

  markAsRead(notificationId: string): Promise<NotificationResponse> {
    return httpClient.patch<NotificationResponse>(`${NOTIFICATIONS_BASE_PATH}/${notificationId}/read`, undefined, {
      headers: getAuthorizationHeaders(),
    });
  },
};
