package com.talalanguage.api.web.goals.dto;

import jakarta.validation.constraints.Min;

public record UpdateGoalSettingsRequestDto(
        @Min(value = 1, message = "Daily minutes must be greater than zero.")
        int dailyMinutes,
        @Min(value = 1, message = "Weekly minutes must be greater than zero.")
        int weeklyMinutes,
        @Min(value = 1, message = "Words per day must be greater than zero.")
        int wordsPerDay,
        @Min(value = 1, message = "Challenges per week must be greater than zero.")
        int challengesPerWeek
) {
}
