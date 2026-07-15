package com.talalanguage.api.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "learning_activity")
public class LearningActivityEntity {

    @Id
    @Column(nullable = false, length = 64)
    private String id;

    @Column(name = "user_id", nullable = false, length = 64)
    private String userId;

    @Column(nullable = false, length = 32)
    private String type;

    @Column(nullable = false, length = 32)
    private String skill;

    @Column(nullable = false)
    private int score;

    @Column(name = "completed_at", nullable = false)
    private Instant completedAt;

    @Column(name = "source_id", length = 128)
    private String sourceId;

    protected LearningActivityEntity() {
    }

    public LearningActivityEntity(
            String id,
            String userId,
            String type,
            String skill,
            int score,
            Instant completedAt,
            String sourceId
    ) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.skill = skill;
        this.score = score;
        this.completedAt = completedAt;
        this.sourceId = sourceId;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getType() {
        return type;
    }

    public String getSkill() {
        return skill;
    }

    public int getScore() {
        return score;
    }

    public Instant getCompletedAt() {
        return completedAt;
    }

    public String getSourceId() {
        return sourceId;
    }
}
