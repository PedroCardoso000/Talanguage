package com.talalanguage.api.application.mocktest.model;

import com.talalanguage.api.domain.mocktest.MockTestAttemptAnswer;

public record MockTestAttemptQuestionView(
        String questionId,
        String selectedOption,
        String correctOption,
        String explanation,
        boolean correct
) {

    public static MockTestAttemptQuestionView from(MockTestAttemptAnswer answer) {
        return new MockTestAttemptQuestionView(
                answer.questionId(),
                answer.selectedOption(),
                answer.correctOption(),
                answer.explanation(),
                answer.correct()
        );
    }
}
