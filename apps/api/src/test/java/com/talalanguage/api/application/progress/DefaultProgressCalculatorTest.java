package com.talalanguage.api.application.progress;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.progress.ActivityType;
import com.talalanguage.api.domain.progress.DailyGoal;
import com.talalanguage.api.domain.progress.LearningActivity;
import com.talalanguage.api.domain.progress.SkillType;
import com.talalanguage.api.infrastructure.progress.DefaultProgressCalculator;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;
import org.junit.jupiter.api.Test;

class DefaultProgressCalculatorTest {

    private final DefaultProgressCalculator calculator = new DefaultProgressCalculator();

    @Test
    void shouldReturnInitialStateForNewUser() {
        UserId userId = UserId.create();

        var summary = calculator.calculateSummary(
                userId,
                List.of(),
                new DailyGoal("goal-1", userId, 3, LocalDate.now(ZoneOffset.UTC))
        );

        assertEquals(0, summary.streakDays());
        assertEquals(0, summary.longestStreakDays());
        assertEquals(null, summary.lastActivityDate());
        assertEquals(0, summary.totalActivities());
        assertEquals("NOT_STARTED", summary.dailyGoal().status());
        assertEquals(0, summary.skillProgress().speaking());
    }

    @Test
    void shouldCalculateStreakAndDailyGoal() {
        UserId userId = UserId.create();
        Instant now = Instant.now();

        List<LearningActivity> activities = List.of(
                LearningActivity.restore("1", userId, ActivityType.SPEAKING, SkillType.SPEAKING, 70, now.minusSeconds(3600), "s1"),
                LearningActivity.restore("2", userId, ActivityType.WRITING, SkillType.WRITING, 80, now.minusSeconds(86400), "w1")
        );

        var summary = calculator.calculateSummary(
                userId,
                activities,
                new DailyGoal("goal-1", userId, 3, LocalDate.now(ZoneOffset.UTC))
        );

        assertEquals(2, summary.streakDays());
        assertEquals(2, summary.longestStreakDays());
        assertEquals(1, summary.dailyGoal().completed());
        assertEquals("IN_PROGRESS", summary.dailyGoal().status());
    }

    @Test
    void shouldCalculateSkillProgress() {
        UserId userId = UserId.create();
        Instant now = Instant.now();

        List<LearningActivity> activities = List.of(
                LearningActivity.restore("1", userId, ActivityType.SPEAKING, SkillType.SPEAKING, 60, now, "s1"),
                LearningActivity.restore("2", userId, ActivityType.SPEAKING, SkillType.SPEAKING, 80, now, "s2"),
                LearningActivity.restore("3", userId, ActivityType.FLASHCARDS, SkillType.VOCABULARY, 90, now, "f1")
        );

        var summary = calculator.calculateSummary(
                userId,
                activities,
                new DailyGoal("goal-1", userId, 3, LocalDate.now(ZoneOffset.UTC))
        );

        assertEquals(70, summary.skillProgress().speaking());
        assertEquals(90, summary.skillProgress().vocabulary());
    }

    @Test
    void shouldNotMixUsers() {
        UserId targetUserId = UserId.create();
        UserId otherUserId = UserId.create();
        Instant now = Instant.now();

        List<LearningActivity> activities = List.of(
                LearningActivity.restore("1", targetUserId, ActivityType.SPEAKING, SkillType.SPEAKING, 60, now, "s1"),
                LearningActivity.restore("2", otherUserId, ActivityType.WRITING, SkillType.WRITING, 95, now, "w1")
        );

        var summary = calculator.calculateSummary(
                targetUserId,
                activities,
                new DailyGoal("goal-1", targetUserId, 3, LocalDate.now(ZoneOffset.UTC))
        );

        assertEquals(1, summary.totalActivities());
        assertEquals(0, summary.skillProgress().writing());
        assertEquals(60, summary.skillProgress().speaking());
    }

    @Test
    void shouldBreakCurrentStreakAfterMissingFullDayButPreserveLongest() {
        UserId userId = UserId.create();
        Instant now = Instant.now();

        List<LearningActivity> activities = List.of(
                LearningActivity.restore("1", userId, ActivityType.SPEAKING, SkillType.SPEAKING, 70, now.minusSeconds(86400L * 3), "s1"),
                LearningActivity.restore("2", userId, ActivityType.WRITING, SkillType.WRITING, 82, now.minusSeconds(86400L * 2), "w1")
        );

        var summary = calculator.calculateSummary(
                userId,
                activities,
                new DailyGoal("goal-1", userId, 3, LocalDate.now(ZoneOffset.UTC))
        );

        assertEquals(0, summary.streakDays());
        assertEquals(2, summary.longestStreakDays());
    }
}
