package com.talalanguage.api.application.mocktest.model;

import java.util.List;

public record CurrentMockTestView(
        String id,
        String title,
        List<CurrentMockTestQuestionView> questions
) {
}
