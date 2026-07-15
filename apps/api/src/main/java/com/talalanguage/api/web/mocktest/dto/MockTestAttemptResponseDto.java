package com.talalanguage.api.web.mocktest.dto;

import com.talalanguage.api.application.mocktest.model.MockTestAttemptView;
import java.util.List;

public record MockTestAttemptResponseDto(
        String attemptId,
        String mockTestId,
        int score,
        int totalQuestions,
        String recommendation,
        String completedAt,
        List<MockTestAttemptQuestionResponseDto> questions
) {

    public static MockTestAttemptResponseDto from(MockTestAttemptView view) {
        return new MockTestAttemptResponseDto(
                view.attemptId(),
                view.mockTestId(),
                view.score(),
                view.totalQuestions(),
                view.recommendation(),
                view.completedAt(),
                view.questions().stream().map(MockTestAttemptQuestionResponseDto::from).toList()
        );
    }
}
