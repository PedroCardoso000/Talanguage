package com.talalanguage.api.application.speaking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.talalanguage.api.application.progress.RegisterLearningActivityUseCase;
import com.talalanguage.api.application.speaking.exception.SpeakingSessionAlreadyFinishedException;
import com.talalanguage.api.infrastructure.progress.InMemoryLearningActivityRepository;
import com.talalanguage.api.infrastructure.speaking.HeuristicSpeakingFeedbackProvider;
import com.talalanguage.api.infrastructure.speaking.InMemorySpeakingSessionRepository;
import com.talalanguage.api.infrastructure.speaking.InMemorySpeakingTopicRepository;
import org.junit.jupiter.api.Test;

class FinishSpeakingSessionUseCaseTest {

    @Test
    void shouldFinishSessionAndReturnSummary() {
        var topicRepository = new InMemorySpeakingTopicRepository();
        var sessionRepository = new InMemorySpeakingSessionRepository();

        var startedSession = new StartSpeakingSessionUseCase(topicRepository, sessionRepository)
                .execute(new StartSpeakingSessionUseCase.Command(
                        "user-1",
                        com.talalanguage.api.domain.speaking.SpeakingLanguage.ENGLISH,
                        com.talalanguage.api.domain.speaking.SpeakingLevel.BEGINNER,
                        "travel"
                ));

        new SubmitSpeakingResponseUseCase(sessionRepository, topicRepository)
                .execute(new SubmitSpeakingResponseUseCase.Command(
                        startedSession.sessionId(),
                        "I would like to confirm my reservation and ask about breakfast."
                ));

        var result = new FinishSpeakingSessionUseCase(
                sessionRepository,
                topicRepository,
                new HeuristicSpeakingFeedbackProvider(),
                new RegisterLearningActivityUseCase(new InMemoryLearningActivityRepository())
        ).execute(new FinishSpeakingSessionUseCase.Command(startedSession.sessionId()));

        assertEquals("FINISHED", result.status());
        assertEquals(1, result.summary().totalMessages());
        assertEquals("Check-in no hotel", result.summary().topicTitle());
    }

    @Test
    void shouldRejectFinishingSameSessionTwice() {
        var topicRepository = new InMemorySpeakingTopicRepository();
        var sessionRepository = new InMemorySpeakingSessionRepository();

        var startedSession = new StartSpeakingSessionUseCase(topicRepository, sessionRepository)
                .execute(new StartSpeakingSessionUseCase.Command(
                        "user-1",
                        com.talalanguage.api.domain.speaking.SpeakingLanguage.ENGLISH,
                        com.talalanguage.api.domain.speaking.SpeakingLevel.BEGINNER,
                        "travel"
                ));

        new SubmitSpeakingResponseUseCase(sessionRepository, topicRepository)
                .execute(new SubmitSpeakingResponseUseCase.Command(startedSession.sessionId(), "Some answer."));

        var useCase = new FinishSpeakingSessionUseCase(
                sessionRepository,
                topicRepository,
                new HeuristicSpeakingFeedbackProvider(),
                new RegisterLearningActivityUseCase(new InMemoryLearningActivityRepository())
        );

        useCase.execute(new FinishSpeakingSessionUseCase.Command(startedSession.sessionId()));

        assertThrows(SpeakingSessionAlreadyFinishedException.class, () ->
                useCase.execute(new FinishSpeakingSessionUseCase.Command(startedSession.sessionId())));
    }
}
