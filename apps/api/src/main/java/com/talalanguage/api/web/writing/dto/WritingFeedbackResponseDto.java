package com.talalanguage.api.web.writing.dto;

import com.talalanguage.api.application.writing.model.WritingFeedbackView;
import java.util.List;

public record WritingFeedbackResponseDto(
        List<String> strengths,
        List<String> improvements,
        String nextAction
) {

    public static WritingFeedbackResponseDto from(WritingFeedbackView view) {
        return new WritingFeedbackResponseDto(view.strengths(), view.improvements(), view.nextAction());
    }
}
