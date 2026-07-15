package com.talalanguage.api.application.writing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.talalanguage.api.application.progress.RegisterLearningActivityUseCase;
import com.talalanguage.api.application.writing.exception.WritingChallengeNotFoundException;
import com.talalanguage.api.infrastructure.progress.InMemoryLearningActivityRepository;
import com.talalanguage.api.infrastructure.writing.HeuristicWritingFeedbackProvider;
import com.talalanguage.api.infrastructure.writing.InMemoryWritingChallengeRepository;
import com.talalanguage.api.infrastructure.writing.InMemoryWritingSubmissionRepository;
import org.junit.jupiter.api.Test;

class SubmitWritingAnswerUseCaseTest {

    @Test
    void shouldCreateSubmissionAndFeedback() {
        InMemoryLearningActivityRepository learningActivityRepository = new InMemoryLearningActivityRepository();
        SubmitWritingAnswerUseCase useCase = new SubmitWritingAnswerUseCase(
                new InMemoryWritingChallengeRepository(),
                new InMemoryWritingSubmissionRepository(),
                new HeuristicWritingFeedbackProvider(),
                new RegisterLearningActivityUseCase(learningActivityRepository)
        );

        var result = useCase.execute(new SubmitWritingAnswerUseCase.Command(
                "user-1",
                "follow-up-email",
                "My name is Pedro. I am from Brazil. I work with software. I want to learn English."
        ));

        assertEquals("REVIEWED", result.status());
        assertEquals(2, result.feedback().strengths().size());
        assertEquals(1, learningActivityRepository.findByUserId(com.talalanguage.api.domain.auth.UserId.from("user-1")).size());
    }

    @Test
    void shouldRejectMissingChallenge() {
        SubmitWritingAnswerUseCase useCase = new SubmitWritingAnswerUseCase(
                new InMemoryWritingChallengeRepository(),
                new InMemoryWritingSubmissionRepository(),
                new HeuristicWritingFeedbackProvider(),
                new RegisterLearningActivityUseCase(new InMemoryLearningActivityRepository())
        );

        assertThrows(WritingChallengeNotFoundException.class, () -> useCase.execute(new SubmitWritingAnswerUseCase.Command(
                "user-1",
                "missing-challenge",
                "Some content"
        )));
    }
}
