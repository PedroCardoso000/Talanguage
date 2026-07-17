package com.talalanguage.api.web.onboarding.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.List;

public record SaveOnboardingRequestDto(
        String ageRange,
        @NotBlank(message = "Occupation is required.") String occupation,
        @Size(max = 120, message = "Occupation description must have at most 120 characters.") String occupationDescription,
        @NotEmpty(message = "Learning motivations must not be empty.") List<@NotBlank String> learningMotivations,
        @NotBlank(message = "Primary goal is required.")
        @Size(max = 240, message = "Primary goal must have at most 240 characters.") String primaryGoal,
        @NotEmpty(message = "Difficulty skills must not be empty.") List<@NotBlank String> difficultySkills,
        @NotBlank(message = "Current level is required.") String currentLevel,
        @Min(value = 1, message = "Weekly availability minutes must be positive.")
        @Max(value = 10080, message = "Weekly availability minutes must be at most 10080.") int weeklyAvailabilityMinutes
) { }
