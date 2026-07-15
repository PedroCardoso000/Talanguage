package com.talalanguage.api.application.mocktest.model;

import java.util.List;

public record MockTestQuestionDefinition(
        String id,
        String question,
        List<String> options,
        String correctOption,
        String explanation
) {
}
