package com.talalanguage.api.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "community_interest")
public class CommunityInterestEntity {

    @Id
    @Column(nullable = false, length = 64)
    private String id;

    @Column(name = "user_id", nullable = false, length = 64)
    private String userId;

    @Column(name = "target_type", nullable = false, length = 32)
    private String targetType;

    @Column(name = "target_id", nullable = false, length = 128)
    private String targetId;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    protected CommunityInterestEntity() {
    }

    public CommunityInterestEntity(String id, String userId, String targetType, String targetId, Instant createdAt) {
        this.id = id;
        this.userId = userId;
        this.targetType = targetType;
        this.targetId = targetId;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getTargetType() {
        return targetType;
    }

    public String getTargetId() {
        return targetId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
