package com.talalanguage.api.application.notifications;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.notifications.NotificationType;
import com.talalanguage.api.infrastructure.notifications.InMemoryNotificationRepository;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

class CreateNotificationUseCaseTest {

    @Test
    void shouldDeduplicateSameNotificationUnderConcurrency() throws Exception {
        var repository = new InMemoryNotificationRepository();
        var useCase = new CreateNotificationUseCase(repository);
        String userId = UserId.create().value();
        var command = new CreateNotificationUseCase.Command(
                userId, NotificationType.WELCOME, "welcome", "Welcome", "Message", "/profile");
        var executor = Executors.newFixedThreadPool(8);

        for (int index = 0; index < 32; index++) {
            executor.submit(() -> useCase.execute(command));
        }
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        assertEquals(1, repository.findByUserId(UserId.from(userId)).size());
    }
}
