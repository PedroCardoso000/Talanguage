package com.talalanguage.api.web.onboarding.dto;

import com.talalanguage.api.application.onboarding.model.OnboardingView;
import java.time.Instant;
import java.util.List;

public record OnboardingDataResponseDto(String ageRange, String occupation, String occupationDescription,
        List<String> learningMotivations, String primaryGoal, List<String> difficultySkills,
        String currentLevel, Integer weeklyAvailabilityMinutes, Instant completedAt) {
    public static OnboardingDataResponseDto from(OnboardingView value) {
        return new OnboardingDataResponseDto(value.ageRange(), value.occupation(), value.occupationDescription(),
                value.learningMotivations(), value.primaryGoal(), value.difficultySkills(), value.currentLevel(),
                value.weeklyAvailabilityMinutes(), value.completedAt());
    }
}
