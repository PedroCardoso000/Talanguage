package com.talalanguage.api.application.mocktest.model;

import java.util.List;

public record CurrentMockTestQuestionView(
        String id,
        String question,
        List<String> options
) {
}
