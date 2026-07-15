package com.talalanguage.api.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "goal_settings")
public class GoalSettingsEntity {

    @Id
    @Column(name = "user_id", nullable = false, length = 64)
    private String userId;

    @Column(name = "daily_minutes", nullable = false)
    private int dailyMinutes;

    @Column(name = "weekly_minutes", nullable = false)
    private int weeklyMinutes;

    @Column(name = "words_per_day", nullable = false)
    private int wordsPerDay;

    @Column(name = "challenges_per_week", nullable = false)
    private int challengesPerWeek;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    protected GoalSettingsEntity() {
    }

    public GoalSettingsEntity(
            String userId,
            int dailyMinutes,
            int weeklyMinutes,
            int wordsPerDay,
            int challengesPerWeek,
            Instant updatedAt
    ) {
        this.userId = userId;
        this.dailyMinutes = dailyMinutes;
        this.weeklyMinutes = weeklyMinutes;
        this.wordsPerDay = wordsPerDay;
        this.challengesPerWeek = challengesPerWeek;
        this.updatedAt = updatedAt;
    }

    public String getUserId() {
        return userId;
    }

    public int getDailyMinutes() {
        return dailyMinutes;
    }

    public int getWeeklyMinutes() {
        return weeklyMinutes;
    }

    public int getWordsPerDay() {
        return wordsPerDay;
    }

    public int getChallengesPerWeek() {
        return challengesPerWeek;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
