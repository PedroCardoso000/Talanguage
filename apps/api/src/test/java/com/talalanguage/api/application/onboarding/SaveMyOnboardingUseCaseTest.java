package com.talalanguage.api.application.onboarding;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.application.notifications.CreateNotificationUseCase;
import com.talalanguage.api.domain.notifications.NotificationType;
import com.talalanguage.api.infrastructure.notifications.InMemoryNotificationRepository;
import com.talalanguage.api.infrastructure.onboarding.InMemoryLearnerOnboardingRepository;
import java.util.List;
import org.junit.jupiter.api.Test;

class SaveMyOnboardingUseCaseTest {

    @Test
    void shouldCreateThenUpdateSameUserWithoutDuplicatingRecord() {
        var repository = new InMemoryLearnerOnboardingRepository();
        var notificationRepository = new InMemoryNotificationRepository();
        var useCase = new SaveMyOnboardingUseCase(
                repository, new CreateNotificationUseCase(notificationRepository));

        var created = useCase.execute(command("user-1", "CAREER", "First goal"));
        var updated = useCase.execute(command("user-1", "TRAVEL", "Updated goal"));

        assertTrue(created.completed());
        assertEquals(created.completedAt(), updated.completedAt());
        assertEquals("Updated goal", updated.primaryGoal());
        assertEquals(1, repository.size());
        assertTrue(repository.existsByUserId(UserId.from("user-1")));
        var notifications = notificationRepository.findByUserId(UserId.from("user-1"));
        assertEquals(1, notifications.size());
        assertEquals(NotificationType.ONBOARDING_COMPLETED, notifications.get(0).type());
    }

    private SaveMyOnboardingUseCase.Command command(String userId, String motivation, String goal) {
        return new SaveMyOnboardingUseCase.Command(userId, "25_34", "EMPLOYED", null,
                List.of(motivation), goal, List.of("SPEAKING"), "INTERMEDIATE", 180);
    }
}
