package com.talalanguage.api.domain.writing;

import com.talalanguage.api.domain.auth.UserId;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public final class WritingSubmission {

    private final String id;
    private final UserId userId;
    private final String challengeId;
    private final WritingLanguage language;
    private final WritingLevel level;
    private final String content;
    private final WritingSubmissionStatus status;
    private final WritingFeedback feedback;
    private final Instant submittedAt;
    private final Instant reviewedAt;

    private WritingSubmission(
            String id,
            UserId userId,
            String challengeId,
            WritingLanguage language,
            WritingLevel level,
            String content,
            WritingSubmissionStatus status,
            WritingFeedback feedback,
            Instant submittedAt,
            Instant reviewedAt
    ) {
        this.id = requireText(id, "Submission id is required.");
        this.userId = Objects.requireNonNull(userId, "User id is required.");
        this.challengeId = requireText(challengeId, "Challenge id is required.");
        this.language = Objects.requireNonNull(language, "Language is required.");
        this.level = Objects.requireNonNull(level, "Level is required.");
        this.content = requireText(content, "Submission content is required.");
        this.status = Objects.requireNonNull(status, "Status is required.");
        this.feedback = Objects.requireNonNull(feedback, "Feedback is required.");
        this.submittedAt = Objects.requireNonNull(submittedAt, "Submitted at is required.");
        this.reviewedAt = Objects.requireNonNull(reviewedAt, "Reviewed at is required.");
    }

    public static WritingSubmission create(
            UserId userId,
            String challengeId,
            WritingLanguage language,
            WritingLevel level,
            String content,
            WritingFeedback feedback
    ) {
        Instant now = Instant.now();
        return new WritingSubmission(
                UUID.randomUUID().toString(),
                userId,
                challengeId,
                language,
                level,
                content,
                WritingSubmissionStatus.REVIEWED,
                feedback,
                now,
                now
        );
    }

    public static WritingSubmission restore(
            String id,
            UserId userId,
            String challengeId,
            WritingLanguage language,
            WritingLevel level,
            String content,
            WritingSubmissionStatus status,
            WritingFeedback feedback,
            Instant submittedAt,
            Instant reviewedAt
    ) {
        return new WritingSubmission(
                id,
                userId,
                challengeId,
                language,
                level,
                content,
                status,
                feedback,
                submittedAt,
                reviewedAt
        );
    }

    public String id() {
        return id;
    }

    public UserId userId() {
        return userId;
    }

    public String challengeId() {
        return challengeId;
    }

    public WritingLanguage language() {
        return language;
    }

    public WritingLevel level() {
        return level;
    }

    public String content() {
        return content;
    }

    public WritingSubmissionStatus status() {
        return status;
    }

    public WritingFeedback feedback() {
        return feedback;
    }

    public Instant submittedAt() {
        return submittedAt;
    }

    public Instant reviewedAt() {
        return reviewedAt;
    }

    private static String requireText(String value, String message) {
        Objects.requireNonNull(value, message);
        String normalized = value.trim();
        if (normalized.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
        return normalized;
    }
}
