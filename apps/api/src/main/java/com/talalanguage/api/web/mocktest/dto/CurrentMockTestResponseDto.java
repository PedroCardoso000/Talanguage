package com.talalanguage.api.web.mocktest.dto;

import com.talalanguage.api.application.mocktest.model.CurrentMockTestView;
import java.util.List;

public record CurrentMockTestResponseDto(
        String id,
        String title,
        List<CurrentMockTestQuestionResponseDto> questions
) {

    public static CurrentMockTestResponseDto from(CurrentMockTestView view) {
        return new CurrentMockTestResponseDto(
                view.id(),
                view.title(),
                view.questions().stream().map(CurrentMockTestQuestionResponseDto::from).toList()
        );
    }
}
