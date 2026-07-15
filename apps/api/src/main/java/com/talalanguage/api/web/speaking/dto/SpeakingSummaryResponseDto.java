package com.talalanguage.api.web.speaking.dto;

import com.talalanguage.api.application.speaking.model.SpeakingSummaryView;

public record SpeakingSummaryResponseDto(
        int totalMessages,
        long approximateDurationMinutes,
        String topicTitle,
        String feedback,
        String nextAction
) {
    public static SpeakingSummaryResponseDto from(SpeakingSummaryView view) {
        return new SpeakingSummaryResponseDto(
                view.totalMessages(),
                view.approximateDurationMinutes(),
                view.topicTitle(),
                view.feedback(),
                view.nextAction()
        );
    }
}
