package com.talalanguage.api.web.speaking.dto;

import com.talalanguage.api.application.speaking.model.SubmittedSpeakingResponseView;

public record SubmitSpeakingResponseResponseDto(String nextPrompt) {
    public static SubmitSpeakingResponseResponseDto from(SubmittedSpeakingResponseView view) {
        return new SubmitSpeakingResponseResponseDto(view.nextPrompt());
    }
}
