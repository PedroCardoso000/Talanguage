package com.talalanguage.api.application.speaking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.talalanguage.api.application.speaking.exception.SpeakingTopicNotFoundException;
import com.talalanguage.api.domain.speaking.SpeakingLanguage;
import com.talalanguage.api.domain.speaking.SpeakingLevel;
import com.talalanguage.api.infrastructure.speaking.InMemorySpeakingSessionRepository;
import com.talalanguage.api.infrastructure.speaking.InMemorySpeakingTopicRepository;
import org.junit.jupiter.api.Test;

class StartSpeakingSessionUseCaseTest {

    @Test
    void shouldStartSessionForExistingTopic() {
        StartSpeakingSessionUseCase useCase = new StartSpeakingSessionUseCase(
                new InMemorySpeakingTopicRepository(),
                new InMemorySpeakingSessionRepository()
        );

        var result = useCase.execute(new StartSpeakingSessionUseCase.Command(
                "user-1",
                SpeakingLanguage.ENGLISH,
                SpeakingLevel.BEGINNER,
                "travel"
        ));

        assertEquals("IN_PROGRESS", result.status());
    }

    @Test
    void shouldRejectMissingTopic() {
        StartSpeakingSessionUseCase useCase = new StartSpeakingSessionUseCase(
                new InMemorySpeakingTopicRepository(),
                new InMemorySpeakingSessionRepository()
        );

        assertThrows(SpeakingTopicNotFoundException.class, () -> useCase.execute(new StartSpeakingSessionUseCase.Command(
                "user-1",
                SpeakingLanguage.ENGLISH,
                SpeakingLevel.BEGINNER,
                "missing-topic"
        )));
    }
}
