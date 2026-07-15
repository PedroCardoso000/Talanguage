package com.talalanguage.api.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "notification")
public class NotificationEntity {

    @Id
    @Column(nullable = false, length = 64)
    private String id;

    @Column(name = "user_id", nullable = false, length = 64)
    private String userId;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, length = 500)
    private String message;

    @Column(name = "action_route", length = 255)
    private String actionRoute;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "read_at")
    private Instant readAt;

    protected NotificationEntity() {
    }

    public NotificationEntity(
            String id,
            String userId,
            String title,
            String message,
            String actionRoute,
            Instant createdAt,
            Instant readAt
    ) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.message = message;
        this.actionRoute = actionRoute;
        this.createdAt = createdAt;
        this.readAt = readAt;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getActionRoute() {
        return actionRoute;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getReadAt() {
        return readAt;
    }
}
