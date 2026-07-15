package com.talalanguage.api.application.mocktest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.talalanguage.api.application.progress.RegisterLearningActivityUseCase;
import com.talalanguage.api.infrastructure.mocktest.InMemoryMockTestAttemptRepository;
import com.talalanguage.api.infrastructure.mocktest.StaticMockTestCatalog;
import com.talalanguage.api.infrastructure.progress.InMemoryLearningActivityRepository;
import java.util.List;
import org.junit.jupiter.api.Test;

class SubmitMockTestAttemptUseCaseTest {

    @Test
    void shouldSubmitAttemptAndCalculateScore() {
        SubmitMockTestAttemptUseCase useCase = new SubmitMockTestAttemptUseCase(
                new StaticMockTestCatalog(),
                new InMemoryMockTestAttemptRepository(),
                new RegisterLearningActivityUseCase(new InMemoryLearningActivityRepository())
        );

        var result = useCase.execute(new SubmitMockTestAttemptUseCase.Command(
                "user-1",
                "baseline-english-v1",
                List.of(
                        new SubmitMockTestAttemptUseCase.AnswerCommand("q1", "She goes to work every day."),
                        new SubmitMockTestAttemptUseCase.AnswerCommand("q2", "Let me walk you through the main points."),
                        new SubmitMockTestAttemptUseCase.AnswerCommand("q3", "finish"),
                        new SubmitMockTestAttemptUseCase.AnswerCommand("q4", "I totally agree with you."),
                        new SubmitMockTestAttemptUseCase.AnswerCommand("q5", "However")
                )
        ));

        assertEquals(5, result.score());
        assertEquals(5, result.totalQuestions());
        assertEquals(5, result.questions().size());
    }

    @Test
    void shouldRejectIncompleteAttempt() {
        SubmitMockTestAttemptUseCase useCase = new SubmitMockTestAttemptUseCase(
                new StaticMockTestCatalog(),
                new InMemoryMockTestAttemptRepository(),
                new RegisterLearningActivityUseCase(new InMemoryLearningActivityRepository())
        );

        assertThrows(IllegalArgumentException.class, () -> useCase.execute(new SubmitMockTestAttemptUseCase.Command(
                "user-1",
                "baseline-english-v1",
                List.of(new SubmitMockTestAttemptUseCase.AnswerCommand("q1", "She goes to work every day."))
        )));
    }
}
