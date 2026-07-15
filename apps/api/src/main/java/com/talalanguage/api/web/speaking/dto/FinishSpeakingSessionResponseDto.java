package com.talalanguage.api.web.speaking.dto;

import com.talalanguage.api.application.speaking.model.FinishedSpeakingSessionView;

public record FinishSpeakingSessionResponseDto(
        String sessionId,
        String status,
        SpeakingSummaryResponseDto summary
) {
    public static FinishSpeakingSessionResponseDto from(FinishedSpeakingSessionView view) {
        return new FinishSpeakingSessionResponseDto(
                view.sessionId(),
                view.status(),
                SpeakingSummaryResponseDto.from(view.summary())
        );
    }
}
