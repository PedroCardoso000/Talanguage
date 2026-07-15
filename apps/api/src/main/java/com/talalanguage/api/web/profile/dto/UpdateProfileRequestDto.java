package com.talalanguage.api.web.profile.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateProfileRequestDto(
        @NotBlank(message = "Name is required.")
        String name,
        String targetLanguage,
        String currentLevel,
        @Size(max = 240, message = "Study goal must have at most 240 characters.")
        String studyGoal,
        String avatarUrl
) {
}
