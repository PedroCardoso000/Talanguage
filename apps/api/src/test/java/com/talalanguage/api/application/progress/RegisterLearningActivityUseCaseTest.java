package com.talalanguage.api.application.progress;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.application.notifications.CreateNotificationUseCase;
import com.talalanguage.api.domain.notifications.NotificationType;
import com.talalanguage.api.infrastructure.notifications.InMemoryNotificationRepository;
import com.talalanguage.api.domain.progress.ActivityType;
import com.talalanguage.api.domain.progress.SkillType;
import com.talalanguage.api.infrastructure.progress.InMemoryLearningActivityRepository;
import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

class RegisterLearningActivityUseCaseTest {

    @Test
    void shouldGenerateOneStreakLostWhenNewActivityConfirmsUtcGap() {
        var activityRepository = new InMemoryLearningActivityRepository();
        var notificationRepository = new InMemoryNotificationRepository();
        var useCase = new RegisterLearningActivityUseCase(
                activityRepository, new CreateNotificationUseCase(notificationRepository));
        String userId = UserId.create().value();

        useCase.execute(command(userId, "day-1", "2026-07-17T23:30:00Z"));
        var resumedCommand = command(userId, "day-3", "2026-07-19T00:15:00Z");
        useCase.execute(resumedCommand);
        useCase.execute(resumedCommand);

        var streakLost = notificationRepository.findByUserId(UserId.from(userId)).stream()
                .filter(notification -> notification.type() == NotificationType.STREAK_LOST)
                .toList();
        assertEquals(1, streakLost.size());
        assertEquals("streak-lost:2026-07-19", streakLost.get(0).deduplicationKey());
    }

    @Test
    void shouldNotGenerateStreakLostAcrossAdjacentUtcDates() {
        var activityRepository = new InMemoryLearningActivityRepository();
        var notificationRepository = new InMemoryNotificationRepository();
        var useCase = new RegisterLearningActivityUseCase(
                activityRepository, new CreateNotificationUseCase(notificationRepository));
        String userId = UserId.create().value();

        useCase.execute(command(userId, "before-midnight", "2026-07-17T23:59:00Z"));
        useCase.execute(command(userId, "after-midnight", "2026-07-18T00:01:00Z"));

        assertEquals(0, notificationRepository.findByUserId(UserId.from(userId)).size());
    }

    @Test
    void shouldRejectActivityWithoutACompletedSource() {
        var repository = new InMemoryLearningActivityRepository();
        var useCase = new RegisterLearningActivityUseCase(repository);
        String userId = UserId.create().value();

        assertThrows(NullPointerException.class, () -> useCase.execute(new RegisterLearningActivityUseCase.Command(
                userId,
                ActivityType.SPEAKING,
                SkillType.SPEAKING,
                80,
                Instant.parse("2026-07-17T12:00:00Z"),
                null
        )));
        assertEquals(0, repository.findByUserId(UserId.from(userId)).size());
    }

    @Test
    void shouldRegisterSameCompletedSourceOnlyOnceUnderConcurrency() throws Exception {
        var repository = new InMemoryLearningActivityRepository();
        var useCase = new RegisterLearningActivityUseCase(repository);
        String userId = UserId.create().value();
        var command = new RegisterLearningActivityUseCase.Command(
                userId,
                ActivityType.SPEAKING,
                SkillType.SPEAKING,
                80,
                Instant.parse("2026-07-17T12:00:00Z"),
                "session-1"
        );
        var executor = Executors.newFixedThreadPool(8);

        for (int index = 0; index < 32; index++) {
            executor.submit(() -> useCase.execute(command));
        }
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        assertEquals(1, repository.findByUserId(UserId.from(userId)).size());
    }

    private RegisterLearningActivityUseCase.Command command(String userId, String sourceId, String completedAt) {
        return new RegisterLearningActivityUseCase.Command(
                userId, ActivityType.SPEAKING, SkillType.SPEAKING, 80, Instant.parse(completedAt), sourceId);
    }
}
