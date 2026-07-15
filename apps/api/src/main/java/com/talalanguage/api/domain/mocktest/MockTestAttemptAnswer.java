package com.talalanguage.api.domain.mocktest;

public record MockTestAttemptAnswer(
        int orderIndex,
        String questionId,
        String selectedOption,
        String correctOption,
        String explanation,
        boolean correct
) {
}
