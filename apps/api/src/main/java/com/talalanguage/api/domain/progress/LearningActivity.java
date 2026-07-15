package com.talalanguage.api.domain.progress;

import com.talalanguage.api.domain.auth.UserId;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public final class LearningActivity {

    private final String id;
    private final UserId userId;
    private final ActivityType type;
    private final SkillType skill;
    private final int score;
    private final Instant completedAt;
    private final String sourceId;

    private LearningActivity(
            String id,
            UserId userId,
            ActivityType type,
            SkillType skill,
            int score,
            Instant completedAt,
            String sourceId
    ) {
        this.id = requireText(id, "Activity id is required.");
        this.userId = Objects.requireNonNull(userId, "User id is required.");
        this.type = Objects.requireNonNull(type, "Activity type is required.");
        this.skill = Objects.requireNonNull(skill, "Skill type is required.");
        this.score = validateScore(score);
        this.completedAt = Objects.requireNonNull(completedAt, "Completed at is required.");
        this.sourceId = sourceId == null ? null : sourceId.trim();
    }

    public static LearningActivity create(
            UserId userId,
            ActivityType type,
            SkillType skill,
            int score,
            Instant completedAt,
            String sourceId
    ) {
        return new LearningActivity(UUID.randomUUID().toString(), userId, type, skill, score, completedAt, sourceId);
    }

    public static LearningActivity restore(
            String id,
            UserId userId,
            ActivityType type,
            SkillType skill,
            int score,
            Instant completedAt,
            String sourceId
    ) {
        return new LearningActivity(id, userId, type, skill, score, completedAt, sourceId);
    }

    private static String requireText(String value, String message) {
        Objects.requireNonNull(value, message);
        String normalized = value.trim();
        if (normalized.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
        return normalized;
    }

    private static int validateScore(int value) {
        if (value < 0 || value > 100) {
            throw new IllegalArgumentException("Score must be between 0 and 100.");
        }
        return value;
    }

    public String id() {
        return id;
    }

    public UserId userId() {
        return userId;
    }

    public ActivityType type() {
        return type;
    }

    public SkillType skill() {
        return skill;
    }

    public int score() {
        return score;
    }

    public Instant completedAt() {
        return completedAt;
    }

    public String sourceId() {
        return sourceId;
    }
}
