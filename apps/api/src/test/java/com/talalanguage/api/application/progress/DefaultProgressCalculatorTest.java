package com.talalanguage.api.application.progress;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.talalanguage.api.application.progress.model.ProgressSummaryView;
import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.progress.ActivityType;
import com.talalanguage.api.domain.progress.DailyGoal;
import com.talalanguage.api.domain.progress.LearningActivity;
import com.talalanguage.api.domain.progress.SkillType;
import com.talalanguage.api.infrastructure.progress.DefaultProgressCalculator;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import org.junit.jupiter.api.Test;

class DefaultProgressCalculatorTest {

    private static final Instant NOW = Instant.parse("2026-07-17T12:00:00Z");
    private static final LocalDate TODAY = LocalDate.parse("2026-07-17");
    private final DefaultProgressCalculator calculator = new DefaultProgressCalculator(
            Clock.fixed(NOW, ZoneId.of("America/Fortaleza"))
    );

    @Test
    void shouldReturnInitialStateForNewUser() {
        UserId userId = UserId.create();

        var summary = calculate(userId, List.of());

        assertEquals(0, summary.streakDays());
        assertEquals(0, summary.longestStreakDays());
        assertEquals(null, summary.lastActivityDate());
        assertEquals(0, summary.totalActivities());
        assertEquals("NOT_STARTED", summary.dailyGoal().status());
    }

    @Test
    void shouldStartStreakAtOneOnFirstActivity() {
        UserId userId = UserId.create();

        var summary = calculate(userId, List.of(activity("1", userId, "2026-07-17T08:00:00Z", "s1")));

        assertEquals(1, summary.streakDays());
        assertEquals(1, summary.longestStreakDays());
    }

    @Test
    void shouldCountSeveralActivitiesOnSameDayOnlyOnceForStreak() {
        UserId userId = UserId.create();

        var summary = calculate(userId, List.of(
                activity("1", userId, "2026-07-17T08:00:00Z", "s1"),
                activity("2", userId, "2026-07-17T20:00:00Z", "s2")
        ));

        assertEquals(1, summary.streakDays());
        assertEquals(2, summary.dailyGoal().completed());
    }

    @Test
    void shouldIncrementStreakOnFollowingDay() {
        UserId userId = UserId.create();

        var summary = calculate(userId, List.of(
                activity("1", userId, "2026-07-16T12:00:00Z", "s1"),
                activity("2", userId, "2026-07-17T12:00:00Z", "s2")
        ));

        assertEquals(2, summary.streakDays());
    }

    @Test
    void shouldKeepYesterdayStreakWithoutIncrementWhenTodayHasNoActivity() {
        UserId userId = UserId.create();

        var summary = calculate(userId, List.of(
                activity("1", userId, "2026-07-15T12:00:00Z", "s1"),
                activity("2", userId, "2026-07-16T12:00:00Z", "s2")
        ));

        assertEquals(2, summary.streakDays());
    }

    @Test
    void shouldBreakCurrentStreakAfterMissingFullDayButPreserveLongest() {
        UserId userId = UserId.create();

        var summary = calculate(userId, List.of(
                activity("1", userId, "2026-07-13T12:00:00Z", "s1"),
                activity("2", userId, "2026-07-14T12:00:00Z", "s2")
        ));

        assertEquals(0, summary.streakDays());
        assertEquals(2, summary.longestStreakDays());
    }

    @Test
    void shouldPreserveHistoricalLongestStreakAfterAReset() {
        UserId userId = UserId.create();

        var summary = calculate(userId, List.of(
                activity("1", userId, "2026-07-10T12:00:00Z", "s1"),
                activity("2", userId, "2026-07-11T12:00:00Z", "s2"),
                activity("3", userId, "2026-07-12T12:00:00Z", "s3"),
                activity("4", userId, "2026-07-17T12:00:00Z", "s4")
        ));

        assertEquals(1, summary.streakDays());
        assertEquals(3, summary.longestStreakDays());
    }

    @Test
    void shouldUseUtcDayBoundaryRegardlessOfHostClockZone() {
        UserId userId = UserId.create();

        var summary = calculate(userId, List.of(
                activity("1", userId, "2026-07-16T23:30:00Z", "s1"),
                activity("2", userId, "2026-07-17T00:30:00Z", "s2")
        ));

        assertEquals(2, summary.streakDays());
        assertEquals("2026-07-17", summary.lastActivityDate());
    }

    @Test
    void shouldNotMixUsers() {
        UserId targetUserId = UserId.create();
        UserId otherUserId = UserId.create();

        var summary = calculate(targetUserId, List.of(
                activity("1", targetUserId, "2026-07-17T08:00:00Z", "s1"),
                activity("2", otherUserId, "2026-07-16T08:00:00Z", "s2")
        ));

        assertEquals(1, summary.streakDays());
        assertEquals(1, summary.totalActivities());
    }

    @Test
    void shouldCalculateSkillProgressFromUserActivities() {
        UserId userId = UserId.create();
        List<LearningActivity> activities = List.of(
                LearningActivity.restore("1", userId, ActivityType.SPEAKING, SkillType.SPEAKING, 60, NOW, "s1"),
                LearningActivity.restore("2", userId, ActivityType.SPEAKING, SkillType.SPEAKING, 80, NOW, "s2")
        );

        var summary = calculate(userId, activities);

        assertEquals(70, summary.skillProgress().speaking());
    }

    private ProgressSummaryView calculate(UserId userId, List<LearningActivity> activities) {
        return calculator.calculateSummary(userId, activities, new DailyGoal("goal-1", userId, 3, TODAY));
    }

    private LearningActivity activity(String id, UserId userId, String completedAt, String sourceId) {
        return LearningActivity.restore(
                id,
                userId,
                ActivityType.SPEAKING,
                SkillType.SPEAKING,
                70,
                Instant.parse(completedAt),
                sourceId
        );
    }
}
