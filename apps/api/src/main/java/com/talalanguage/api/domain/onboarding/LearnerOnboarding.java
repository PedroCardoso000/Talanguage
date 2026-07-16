package com.talalanguage.api.domain.onboarding;

import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.progress.SkillType;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public final class LearnerOnboarding {
    public static final int MAX_WEEKLY_MINUTES = 10_080;
    private final UserId userId;
    private final AgeRange ageRange;
    private final Occupation occupation;
    private final String occupationDescription;
    private final List<LearningMotivation> learningMotivations;
    private final String primaryGoal;
    private final List<SkillType> difficultySkills;
    private final CurrentLevel currentLevel;
    private final int weeklyAvailabilityMinutes;
    private final Instant completedAt;
    private final Instant updatedAt;

    public LearnerOnboarding(UserId userId, AgeRange ageRange, Occupation occupation,
            String occupationDescription, List<LearningMotivation> learningMotivations, String primaryGoal,
            List<SkillType> difficultySkills, CurrentLevel currentLevel, int weeklyAvailabilityMinutes,
            Instant completedAt, Instant updatedAt) {
        this.userId = Objects.requireNonNull(userId, "User id is required.");
        this.ageRange = ageRange;
        this.occupation = Objects.requireNonNull(occupation, "Occupation is required.");
        this.occupationDescription = validateOccupationDescription(occupation, occupationDescription);
        this.learningMotivations = validateList(learningMotivations, "Learning motivations");
        this.primaryGoal = validateText(primaryGoal, 240, "Primary goal");
        this.difficultySkills = validateList(difficultySkills, "Difficulty skills");
        this.currentLevel = Objects.requireNonNull(currentLevel, "Current level is required.");
        if (weeklyAvailabilityMinutes <= 0 || weeklyAvailabilityMinutes > MAX_WEEKLY_MINUTES) {
            throw new IllegalArgumentException("Weekly availability minutes must be between 1 and 10080.");
        }
        this.weeklyAvailabilityMinutes = weeklyAvailabilityMinutes;
        this.completedAt = Objects.requireNonNull(completedAt, "Completed at is required.");
        this.updatedAt = Objects.requireNonNull(updatedAt, "Updated at is required.");
    }

    public static LearnerOnboarding save(UserId userId, LearnerOnboarding existing, AgeRange ageRange,
            Occupation occupation, String occupationDescription, List<LearningMotivation> motivations,
            String primaryGoal, List<SkillType> skills, CurrentLevel level, int weeklyMinutes, Instant now) {
        Instant completedAt = existing == null ? now : existing.completedAt();
        return new LearnerOnboarding(userId, ageRange, occupation, occupationDescription, motivations,
                primaryGoal, skills, level, weeklyMinutes, completedAt, now);
    }

    private static String validateOccupationDescription(Occupation occupation, String value) {
        if (occupation != Occupation.OTHER) { return null; }
        return validateText(value, 120, "Occupation description");
    }

    private static String validateText(String value, int max, String field) {
        if (value == null || value.trim().isEmpty()) { throw new IllegalArgumentException(field + " is required."); }
        String normalized = value.trim();
        if (normalized.length() > max) { throw new IllegalArgumentException(field + " is too long."); }
        return normalized;
    }

    private static <T> List<T> validateList(List<T> values, String field) {
        if (values == null || values.isEmpty()) { throw new IllegalArgumentException(field + " must not be empty."); }
        if (values.stream().anyMatch(Objects::isNull) || new HashSet<>(values).size() != values.size()) {
            throw new IllegalArgumentException(field + " must contain valid values without duplicates.");
        }
        return List.copyOf(values);
    }

    public UserId userId() { return userId; }
    public AgeRange ageRange() { return ageRange; }
    public Occupation occupation() { return occupation; }
    public String occupationDescription() { return occupationDescription; }
    public List<LearningMotivation> learningMotivations() { return learningMotivations; }
    public String primaryGoal() { return primaryGoal; }
    public List<SkillType> difficultySkills() { return difficultySkills; }
    public CurrentLevel currentLevel() { return currentLevel; }
    public int weeklyAvailabilityMinutes() { return weeklyAvailabilityMinutes; }
    public Instant completedAt() { return completedAt; }
    public Instant updatedAt() { return updatedAt; }
}
