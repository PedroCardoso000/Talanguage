package com.talalanguage.api.application.mocktest.model;

import com.talalanguage.api.domain.mocktest.MockTestAttempt;
import java.util.List;

public record MockTestAttemptView(
        String attemptId,
        String mockTestId,
        int score,
        int totalQuestions,
        String recommendation,
        String completedAt,
        List<MockTestAttemptQuestionView> questions
) {

    public static MockTestAttemptView from(MockTestAttempt attempt) {
        return new MockTestAttemptView(
                attempt.id(),
                attempt.mockTestId(),
                attempt.score(),
                attempt.totalQuestions(),
                attempt.recommendation(),
                attempt.completedAt().toString(),
                attempt.answers().stream().map(MockTestAttemptQuestionView::from).toList()
        );
    }
}
