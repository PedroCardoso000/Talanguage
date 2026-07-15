package com.talalanguage.api.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "user_session")
public class UserSessionEntity {

    @Id
    @Column(nullable = false, length = 255)
    private String token;

    @Column(name = "user_id", nullable = false, length = 64)
    private String userId;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    protected UserSessionEntity() {
    }

    public UserSessionEntity(String token, String userId, Instant createdAt) {
        this.token = token;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    public String getToken() {
        return token;
    }

    public String getUserId() {
        return userId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
