package com.talalanguage.api.application.speaking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.talalanguage.api.application.progress.RegisterLearningActivityUseCase;
import com.talalanguage.api.application.speaking.exception.SpeakingSessionAlreadyFinishedException;
import com.talalanguage.api.domain.speaking.SpeakingLanguage;
import com.talalanguage.api.domain.speaking.SpeakingLevel;
import com.talalanguage.api.infrastructure.progress.InMemoryLearningActivityRepository;
import com.talalanguage.api.infrastructure.speaking.HeuristicSpeakingFeedbackProvider;
import com.talalanguage.api.infrastructure.speaking.InMemorySpeakingSessionRepository;
import com.talalanguage.api.infrastructure.speaking.InMemorySpeakingTopicRepository;
import org.junit.jupiter.api.Test;

class SubmitSpeakingResponseUseCaseTest {

    @Test
    void shouldSubmitResponseForActiveSession() {
        var topicRepository = new InMemorySpeakingTopicRepository();
        var sessionRepository = new InMemorySpeakingSessionRepository();

        var startedSession = new StartSpeakingSessionUseCase(topicRepository, sessionRepository)
                .execute(new StartSpeakingSessionUseCase.Command(
                        "user-1",
                        SpeakingLanguage.ENGLISH,
                        SpeakingLevel.BEGINNER,
                        "travel"
                ));

        var result = new SubmitSpeakingResponseUseCase(sessionRepository, topicRepository)
                .execute(new SubmitSpeakingResponseUseCase.Command(
                        startedSession.sessionId(),
                        "I would like to confirm my reservation."
                ));

        assertEquals("Now ask one extra question about breakfast, Wi-Fi, or check-out time.", result.nextPrompt());
    }

    @Test
    void shouldRejectResponseForFinishedSession() {
        var topicRepository = new InMemorySpeakingTopicRepository();
        var sessionRepository = new InMemorySpeakingSessionRepository();

        var startedSession = new StartSpeakingSessionUseCase(topicRepository, sessionRepository)
                .execute(new StartSpeakingSessionUseCase.Command(
                        "user-1",
                        SpeakingLanguage.ENGLISH,
                        SpeakingLevel.BEGINNER,
                        "travel"
                ));

        new SubmitSpeakingResponseUseCase(sessionRepository, topicRepository)
                .execute(new SubmitSpeakingResponseUseCase.Command(startedSession.sessionId(), "Some answer."));

        new FinishSpeakingSessionUseCase(
                sessionRepository,
                topicRepository,
                new HeuristicSpeakingFeedbackProvider(),
                new RegisterLearningActivityUseCase(new InMemoryLearningActivityRepository())
        ).execute(new FinishSpeakingSessionUseCase.Command(startedSession.sessionId()));

        assertThrows(SpeakingSessionAlreadyFinishedException.class, () ->
                new SubmitSpeakingResponseUseCase(sessionRepository, topicRepository)
                        .execute(new SubmitSpeakingResponseUseCase.Command(startedSession.sessionId(), "Another answer."))
        );
    }
}
