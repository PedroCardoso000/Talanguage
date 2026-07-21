package com.talalanguage.api.application.dashboard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.talalanguage.api.domain.auth.Email;
import com.talalanguage.api.domain.auth.User;
import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.progress.ActivityType;
import com.talalanguage.api.domain.progress.SkillType;
import com.talalanguage.api.infrastructure.auth.InMemoryUserRepository;
import com.talalanguage.api.infrastructure.goals.InMemoryGoalSettingsRepository;
import com.talalanguage.api.infrastructure.progress.DefaultProgressCalculator;
import com.talalanguage.api.infrastructure.progress.InMemoryLearningActivityRepository;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import org.junit.jupiter.api.Test;

class GetDashboardSummaryUseCaseTest {

    private static final Clock CLOCK = Clock.fixed(Instant.parse("2026-07-17T12:00:00Z"), ZoneOffset.UTC);

    @Test
    void shouldBuildSummaryForUserWithActivities() {
        InMemoryUserRepository userRepository = new InMemoryUserRepository();
        InMemoryLearningActivityRepository activityRepository = new InMemoryLearningActivityRepository();
        GetDashboardSummaryUseCase useCase = new GetDashboardSummaryUseCase(
                userRepository,
                activityRepository,
                new InMemoryGoalSettingsRepository(),
                new DefaultProgressCalculator(CLOCK),
                CLOCK
        );

        User user = User.restore(
                UserId.create(),
                "Pedro",
                Email.of("pedro@example.com"),
                "hashed-password",
                "ENGLISH",
                null,
                null,
                null,
                CLOCK.instant(),
                Instant.now()
        );
        userRepository.save(user);
        activityRepository.save(com.talalanguage.api.domain.progress.LearningActivity.create(
                user.id(),
                ActivityType.SPEAKING,
                SkillType.SPEAKING,
                78,
                Instant.now(),
                "speak-1"
        ));

        var summary = useCase.execute(new GetDashboardSummaryUseCase.Command(user.id().value()));

        assertEquals("Pedro", summary.userName());
        assertEquals(1, summary.completedActivitiesToday());
        assertEquals(1, summary.weeklyActivityCount());
        assertEquals(25, summary.goalCompletionPercent());
        assertEquals(1, summary.recentActivities().size());
        assertEquals("WRITING", summary.nextSuggestedAction().type());
    }

    @Test
    void shouldBuildInitialStateForNewUser() {
        InMemoryUserRepository userRepository = new InMemoryUserRepository();
        InMemoryLearningActivityRepository activityRepository = new InMemoryLearningActivityRepository();
        GetDashboardSummaryUseCase useCase = new GetDashboardSummaryUseCase(
                userRepository,
                activityRepository,
                new InMemoryGoalSettingsRepository(),
                new DefaultProgressCalculator(CLOCK),
                CLOCK
        );

        User user = User.restore(
                UserId.create(),
                "Ana",
                Email.of("ana@example.com"),
                "hashed-password",
                "ENGLISH",
                null,
                null,
                null,
                Instant.now(),
                Instant.now()
        );
        userRepository.save(user);

        var summary = useCase.execute(new GetDashboardSummaryUseCase.Command(user.id().value()));

        assertEquals("Ana", summary.userName());
        assertEquals(0, summary.currentStreakDays());
        assertEquals(0, summary.completedActivitiesToday());
        assertEquals(0, summary.weeklyActivityCount());
        assertEquals(0, summary.goalCompletionPercent());
        assertEquals(0, summary.recentActivities().size());
        assertEquals(4, summary.dailyGoal().target());
    }
}
