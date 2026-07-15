package com.talalanguage.api.web.mocktest.dto;

import com.talalanguage.api.application.mocktest.model.CurrentMockTestQuestionView;
import java.util.List;

public record CurrentMockTestQuestionResponseDto(
        String id,
        String question,
        List<String> options
) {

    public static CurrentMockTestQuestionResponseDto from(CurrentMockTestQuestionView view) {
        return new CurrentMockTestQuestionResponseDto(view.id(), view.question(), view.options());
    }
}
