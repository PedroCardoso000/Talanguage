package com.talalanguage.api.web.speaking.dto;

import com.talalanguage.api.application.speaking.model.StartedSpeakingSessionView;

public record StartSpeakingSessionResponseDto(
        String sessionId,
        String status,
        String startedAt,
        String prompt
) {
    public static StartSpeakingSessionResponseDto from(StartedSpeakingSessionView view) {
        return new StartSpeakingSessionResponseDto(view.sessionId(), view.status(), view.startedAt(), view.prompt());
    }
}
