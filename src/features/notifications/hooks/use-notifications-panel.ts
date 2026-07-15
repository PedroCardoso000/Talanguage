"use client";

import { useEffect, useMemo, useState } from "react";

import { notificationsApi } from "@/features/notifications/api/notifications-api";
import type { NotificationResponse } from "@/features/notifications/contracts";

type NotificationsPanelState = {
  notifications: NotificationResponse[];
  isLoading: boolean;
  errorMessage: string | null;
};

export function useNotificationsPanel() {
  const [state, setState] = useState<NotificationsPanelState>({
    notifications: [],
    isLoading: true,
    errorMessage: null,
  });
  const [isUpdatingId, setIsUpdatingId] = useState<string | null>(null);

  useEffect(() => {
    let cancelled = false;

    notificationsApi
      .getNotifications()
      .then((notifications) => {
        if (cancelled) {
          return;
        }

        setState({
          notifications,
          isLoading: false,
          errorMessage: null,
        });
      })
      .catch((error) => {
        if (cancelled) {
          return;
        }

        setState({
          notifications: [],
          isLoading: false,
          errorMessage: error instanceof Error ? error.message : "Nao foi possivel carregar as notificacoes.",
        });
      });

    return () => {
      cancelled = true;
    };
  }, []);

  async function markAsRead(notificationId: string) {
    const notification = state.notifications.find((item) => item.id === notificationId);

    if (!notification || notification.read) {
      return;
    }

    setIsUpdatingId(notificationId);

    try {
      const updated = await notificationsApi.markAsRead(notificationId);
      setState((current) => ({
        ...current,
        notifications: current.notifications.map((item) => (item.id === notificationId ? updated : item)),
      }));
    } finally {
      setIsUpdatingId(null);
    }
  }

  const unreadCount = useMemo(
    () => state.notifications.filter((notification) => !notification.read).length,
    [state.notifications],
  );

  return {
    ...state,
    unreadCount,
    isUpdatingId,
    markAsRead,
  };
}
