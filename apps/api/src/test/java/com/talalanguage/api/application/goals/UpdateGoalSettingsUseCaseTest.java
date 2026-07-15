package com.talalanguage.api.application.goals;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.talalanguage.api.infrastructure.goals.InMemoryGoalSettingsRepository;
import org.junit.jupiter.api.Test;

class UpdateGoalSettingsUseCaseTest {

    @Test
    void shouldSaveGoalSettings() {
        UpdateGoalSettingsUseCase useCase = new UpdateGoalSettingsUseCase(new InMemoryGoalSettingsRepository());

        var result = useCase.execute(new UpdateGoalSettingsUseCase.Command(
                "user-1",
                30,
                210,
                25,
                5
        ));

        assertEquals(30, result.dailyMinutes());
        assertEquals(210, result.weeklyMinutes());
        assertEquals(25, result.wordsPerDay());
        assertEquals(5, result.challengesPerWeek());
    }

    @Test
    void shouldRejectInvalidGoalSettings() {
        UpdateGoalSettingsUseCase useCase = new UpdateGoalSettingsUseCase(new InMemoryGoalSettingsRepository());

        assertThrows(IllegalArgumentException.class, () -> useCase.execute(new UpdateGoalSettingsUseCase.Command(
                "user-1",
                0,
                210,
                25,
                5
        )));
    }
}
