package com.talalanguage.api.domain.notifications;

import com.talalanguage.api.domain.auth.UserId;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public final class Notification {

    private final String id;
    private final UserId userId;
    private final NotificationType type;
    private final String deduplicationKey;
    private final String title;
    private final String message;
    private final String actionRoute;
    private final Instant createdAt;
    private final Instant readAt;

    private Notification(
            String id,
            UserId userId,
            NotificationType type,
            String deduplicationKey,
            String title,
            String message,
            String actionRoute,
            Instant createdAt,
            Instant readAt
    ) {
        this.id = requireText(id, "Notification id is required.");
        this.userId = Objects.requireNonNull(userId, "User id is required.");
        this.type = Objects.requireNonNull(type, "Notification type is required.");
        this.deduplicationKey = requireText(deduplicationKey, "Deduplication key is required.");
        this.title = requireText(title, "Notification title is required.");
        this.message = requireText(message, "Notification message is required.");
        this.actionRoute = normalizeRoute(actionRoute);
        this.createdAt = Objects.requireNonNull(createdAt, "Created at is required.");
        this.readAt = readAt;
    }

    public static Notification create(UserId userId, NotificationType type, String deduplicationKey,
            String title, String message, String actionRoute) {
        return new Notification(UUID.randomUUID().toString(), userId, type, deduplicationKey,
                title, message, actionRoute, Instant.now(), null);
    }

    public static Notification restore(
            String id,
            UserId userId,
            NotificationType type,
            String deduplicationKey,
            String title,
            String message,
            String actionRoute,
            Instant createdAt,
            Instant readAt
    ) {
        return new Notification(id, userId, type, deduplicationKey, title, message, actionRoute, createdAt, readAt);
    }

    public Notification markAsRead(Instant when) {
        if (readAt != null) {
            return this;
        }

        return new Notification(id, userId, type, deduplicationKey, title, message, actionRoute, createdAt,
                Objects.requireNonNull(when));
    }

    private static String requireText(String value, String message) {
        Objects.requireNonNull(value, message);
        String normalized = value.trim();
        if (normalized.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
        return normalized;
    }

    private static String normalizeRoute(String value) {
        if (value == null) {
            return null;
        }

        String normalized = value.trim();
        if (normalized.isEmpty()) {
            return null;
        }

        if (!normalized.startsWith("/")) {
            throw new IllegalArgumentException("Notification route must start with /.");
        }

        return normalized;
    }

    public String id() {
        return id;
    }

    public UserId userId() {
        return userId;
    }

    public NotificationType type() {
        return type;
    }

    public String deduplicationKey() {
        return deduplicationKey;
    }

    public String title() {
        return title;
    }

    public String message() {
        return message;
    }

    public String actionRoute() {
        return actionRoute;
    }

    public Instant createdAt() {
        return createdAt;
    }

    public Instant readAt() {
        return readAt;
    }
}
