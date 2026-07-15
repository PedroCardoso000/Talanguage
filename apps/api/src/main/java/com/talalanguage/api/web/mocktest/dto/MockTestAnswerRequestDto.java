package com.talalanguage.api.web.mocktest.dto;

import jakarta.validation.constraints.NotBlank;

public record MockTestAnswerRequestDto(
        @NotBlank(message = "Question id is required.")
        String questionId,
        @NotBlank(message = "Selected option is required.")
        String selectedOption
) {
}
