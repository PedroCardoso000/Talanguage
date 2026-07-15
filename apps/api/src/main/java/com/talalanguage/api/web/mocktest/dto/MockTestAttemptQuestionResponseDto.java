package com.talalanguage.api.web.mocktest.dto;

import com.talalanguage.api.application.mocktest.model.MockTestAttemptQuestionView;

public record MockTestAttemptQuestionResponseDto(
        String questionId,
        String selectedOption,
        String correctOption,
        String explanation,
        boolean isCorrect
) {

    public static MockTestAttemptQuestionResponseDto from(MockTestAttemptQuestionView view) {
        return new MockTestAttemptQuestionResponseDto(
                view.questionId(),
                view.selectedOption(),
                view.correctOption(),
                view.explanation(),
                view.correct()
        );
    }
}
