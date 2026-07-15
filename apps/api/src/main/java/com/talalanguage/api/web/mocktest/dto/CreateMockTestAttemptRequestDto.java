package com.talalanguage.api.web.mocktest.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public record CreateMockTestAttemptRequestDto(
        @NotBlank(message = "Mock test id is required.")
        String mockTestId,
        @NotEmpty(message = "At least one answer is required.")
        List<@Valid MockTestAnswerRequestDto> answers
) {
}
