package com.talalanguage.api.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "mock_test_attempt")
public class MockTestAttemptEntity {

    @Id
    @Column(nullable = false, length = 64)
    private String id;

    @Column(name = "user_id", nullable = false, length = 64)
    private String userId;

    @Column(name = "mock_test_id", nullable = false, length = 128)
    private String mockTestId;

    @Column(nullable = false)
    private int score;

    @Column(name = "total_questions", nullable = false)
    private int totalQuestions;

    @Column(nullable = false, columnDefinition = "text")
    private String recommendation;

    @Column(name = "completed_at", nullable = false)
    private Instant completedAt;

    protected MockTestAttemptEntity() {
    }

    public MockTestAttemptEntity(
            String id,
            String userId,
            String mockTestId,
            int score,
            int totalQuestions,
            String recommendation,
            Instant completedAt
    ) {
        this.id = id;
        this.userId = userId;
        this.mockTestId = mockTestId;
        this.score = score;
        this.totalQuestions = totalQuestions;
        this.recommendation = recommendation;
        this.completedAt = completedAt;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getMockTestId() {
        return mockTestId;
    }

    public int getScore() {
        return score;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public Instant getCompletedAt() {
        return completedAt;
    }
}
