package com.talalanguage.api.web.writing.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateWritingSubmissionRequestDto(
        @NotBlank(message = "Challenge id is required.")
        String challengeId,
        @NotBlank(message = "Content is required.")
        String content
) {
}
