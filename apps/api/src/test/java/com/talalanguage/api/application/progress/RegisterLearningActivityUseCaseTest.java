package com.talalanguage.api.application.progress;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.progress.ActivityType;
import com.talalanguage.api.domain.progress.SkillType;
import com.talalanguage.api.infrastructure.progress.InMemoryLearningActivityRepository;
import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

class RegisterLearningActivityUseCaseTest {

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
}
