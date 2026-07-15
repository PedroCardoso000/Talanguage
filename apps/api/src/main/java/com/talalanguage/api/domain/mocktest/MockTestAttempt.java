package com.talalanguage.api.domain.mocktest;

import com.talalanguage.api.domain.auth.UserId;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public final class MockTestAttempt {

    private final String id;
    private final UserId userId;
    private final String mockTestId;
    private final int score;
    private final int totalQuestions;
    private final String recommendation;
    private final Instant completedAt;
    private final List<MockTestAttemptAnswer> answers;

    private MockTestAttempt(
            String id,
            UserId userId,
            String mockTestId,
            int score,
            int totalQuestions,
            String recommendation,
            Instant completedAt,
            List<MockTestAttemptAnswer> answers
    ) {
        this.id = requireText(id, "Attempt id is required.");
        this.userId = Objects.requireNonNull(userId, "User id is required.");
        this.mockTestId = requireText(mockTestId, "Mock test id is required.");
        this.score = Math.max(score, 0);
        this.totalQuestions = Math.max(totalQuestions, 0);
        this.recommendation = requireText(recommendation, "Recommendation is required.");
        this.completedAt = Objects.requireNonNull(completedAt, "Completed at is required.");
        this.answers = List.copyOf(Objects.requireNonNull(answers, "Answers are required."));
    }

    public static MockTestAttempt create(
            UserId userId,
            String mockTestId,
            int score,
            int totalQuestions,
            String recommendation,
            List<MockTestAttemptAnswer> answers
    ) {
        return new MockTestAttempt(
                UUID.randomUUID().toString(),
                userId,
                mockTestId,
                score,
                totalQuestions,
                recommendation,
                Instant.now(),
                answers
        );
    }

    public static MockTestAttempt restore(
            String id,
            UserId userId,
            String mockTestId,
            int score,
            int totalQuestions,
            String recommendation,
            Instant completedAt,
            List<MockTestAttemptAnswer> answers
    ) {
        return new MockTestAttempt(id, userId, mockTestId, score, totalQuestions, recommendation, completedAt, answers);
    }

    private static String requireText(String value, String message) {
        Objects.requireNonNull(value, message);
        String normalized = value.trim();
        if (normalized.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
        return normalized;
    }

    public String id() {
        return id;
    }

    public UserId userId() {
        return userId;
    }

    public String mockTestId() {
        return mockTestId;
    }

    public int score() {
        return score;
    }

    public int totalQuestions() {
        return totalQuestions;
    }

    public String recommendation() {
        return recommendation;
    }

    public Instant completedAt() {
        return completedAt;
    }

    public List<MockTestAttemptAnswer> answers() {
        return answers;
    }
}
