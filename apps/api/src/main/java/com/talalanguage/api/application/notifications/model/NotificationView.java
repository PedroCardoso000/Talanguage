package com.talalanguage.api.application.notifications.model;

import com.talalanguage.api.domain.notifications.Notification;

public record NotificationView(
        String id,
        String type,
        String title,
        String message,
        String actionRoute,
        String createdAt,
        String readAt,
        boolean read
) {

    public static NotificationView from(Notification notification) {
        return new NotificationView(
                notification.id(),
                notification.type().name(),
                notification.title(),
                notification.message(),
                notification.actionRoute(),
                notification.createdAt().toString(),
                notification.readAt() == null ? null : notification.readAt().toString(),
                notification.readAt() != null
        );
    }
}
