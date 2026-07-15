package com.talalanguage.api.web.notifications.dto;

import com.talalanguage.api.application.notifications.model.NotificationView;

public record NotificationResponseDto(
        String id,
        String title,
        String message,
        String actionRoute,
        String createdAt,
        String readAt,
        boolean read
) {

    public static NotificationResponseDto from(NotificationView view) {
        return new NotificationResponseDto(
                view.id(),
                view.title(),
                view.message(),
                view.actionRoute(),
                view.createdAt(),
                view.readAt(),
                view.read()
        );
    }
}
