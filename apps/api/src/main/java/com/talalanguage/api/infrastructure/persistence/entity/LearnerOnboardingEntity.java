package com.talalanguage.api.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "learner_onboarding")
public class LearnerOnboardingEntity {
    @Id
    @Column(name = "user_id", nullable = false, length = 64)
    private String userId;
    @Column(name = "age_range", length = 32)
    private String ageRange;
    @Column(nullable = false, length = 32)
    private String occupation;
    @Column(name = "occupation_description", length = 120)
    private String occupationDescription;
    @Column(name = "learning_motivations_json", nullable = false, columnDefinition = "text")
    private String learningMotivationsJson;
    @Column(name = "primary_goal", nullable = false, length = 240)
    private String primaryGoal;
    @Column(name = "difficulty_skills_json", nullable = false, columnDefinition = "text")
    private String difficultySkillsJson;
    @Column(name = "current_level", nullable = false, length = 32)
    private String currentLevel;
    @Column(name = "weekly_availability_minutes", nullable = false)
    private int weeklyAvailabilityMinutes;
    @Column(name = "completed_at", nullable = false)
    private Instant completedAt;
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    protected LearnerOnboardingEntity() { }

    public LearnerOnboardingEntity(String userId, String ageRange, String occupation,
            String occupationDescription, String learningMotivationsJson, String primaryGoal,
            String difficultySkillsJson, String currentLevel, int weeklyAvailabilityMinutes,
            Instant completedAt, Instant updatedAt) {
        this.userId = userId; this.ageRange = ageRange; this.occupation = occupation;
        this.occupationDescription = occupationDescription; this.learningMotivationsJson = learningMotivationsJson;
        this.primaryGoal = primaryGoal; this.difficultySkillsJson = difficultySkillsJson;
        this.currentLevel = currentLevel; this.weeklyAvailabilityMinutes = weeklyAvailabilityMinutes;
        this.completedAt = completedAt; this.updatedAt = updatedAt;
    }

    public String getUserId() { return userId; }
    public String getAgeRange() { return ageRange; }
    public String getOccupation() { return occupation; }
    public String getOccupationDescription() { return occupationDescription; }
    public String getLearningMotivationsJson() { return learningMotivationsJson; }
    public String getPrimaryGoal() { return primaryGoal; }
    public String getDifficultySkillsJson() { return difficultySkillsJson; }
    public String getCurrentLevel() { return currentLevel; }
    public int getWeeklyAvailabilityMinutes() { return weeklyAvailabilityMinutes; }
    public Instant getCompletedAt() { return completedAt; }
    public Instant getUpdatedAt() { return updatedAt; }
}
