package com.talalanguage.api.web.speaking.dto;

import jakarta.validation.constraints.NotBlank;

public record StartSpeakingSessionRequestDto(
        @NotBlank(message = "Language is required.") String language,
        @NotBlank(message = "Level is required.") String level,
        @NotBlank(message = "Topic ID is required.") String topicId
) {
}
