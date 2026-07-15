package com.talalanguage.api.web.speaking.dto;

import jakarta.validation.constraints.NotBlank;

public record SubmitSpeakingResponseRequestDto(
        @NotBlank(message = "Content is required.") String content
) {
}
