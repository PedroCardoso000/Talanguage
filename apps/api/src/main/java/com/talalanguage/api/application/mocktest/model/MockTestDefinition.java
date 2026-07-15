package com.talalanguage.api.application.mocktest.model;

import java.util.List;

public record MockTestDefinition(
        String id,
        String title,
        List<MockTestQuestionDefinition> questions
) {
}
