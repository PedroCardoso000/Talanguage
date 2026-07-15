package com.talalanguage.api.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "writing_submission_metric")
public class WritingSubmissionMetricEntity {

    @Id
    @Column(nullable = false, length = 64)
    private String id;

    @Column(name = "user_id", nullable = false, length = 64)
    private String userId;

    @Column(name = "challenge_id", nullable = false, length = 128)
    private String challengeId;

    @Column(nullable = false, length = 32)
    private String language;

    @Column(nullable = false, length = 32)
    private String level;

    @Column(nullable = false, length = 32)
    private String status;

    @Column(nullable = false)
    private int score;

    @Column(name = "strengths_json", nullable = false, columnDefinition = "text")
    private String strengthsJson;

    @Column(name = "improvements_json", nullable = false, columnDefinition = "text")
    private String improvementsJson;

    @Column(name = "next_action", nullable = false, columnDefinition = "text")
    private String nextAction;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "reviewed_at", nullable = false)
    private Instant reviewedAt;

    protected WritingSubmissionMetricEntity() {
    }

    public WritingSubmissionMetricEntity(
            String id,
            String userId,
            String challengeId,
            String language,
            String level,
            String status,
            int score,
            String strengthsJson,
            String improvementsJson,
            String nextAction,
            Instant createdAt,
            Instant reviewedAt
    ) {
        this.id = id;
        this.userId = userId;
        this.challengeId = challengeId;
        this.language = language;
        this.level = level;
        this.status = status;
        this.score = score;
        this.strengthsJson = strengthsJson;
        this.improvementsJson = improvementsJson;
        this.nextAction = nextAction;
        this.createdAt = createdAt;
        this.reviewedAt = reviewedAt;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getChallengeId() {
        return challengeId;
    }

    public String getLanguage() {
        return language;
    }

    public String getLevel() {
        return level;
    }

    public String getStatus() {
        return status;
    }

    public int getScore() {
        return score;
    }

    public String getStrengthsJson() {
        return strengthsJson;
    }

    public String getImprovementsJson() {
        return improvementsJson;
    }

    public String getNextAction() {
        return nextAction;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getReviewedAt() {
        return reviewedAt;
    }
}
