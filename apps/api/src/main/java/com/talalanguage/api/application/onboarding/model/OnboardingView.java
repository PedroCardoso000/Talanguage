package com.talalanguage.api.application.onboarding.model;

import com.talalanguage.api.domain.onboarding.LearnerOnboarding;
import java.time.Instant;
import java.util.List;

public record OnboardingView(
        boolean completed,
        String ageRange,
        String occupation,
        String occupationDescription,
        List<String> learningMotivations,
        String primaryGoal,
        List<String> difficultySkills,
        String currentLevel,
        Integer weeklyAvailabilityMinutes,
        Instant completedAt
) {
    public static OnboardingView incomplete() {
        return new OnboardingView(false, null, null, null, List.of(), null, List.of(), null, null, null);
    }

    public static OnboardingView from(LearnerOnboarding value) {
        return new OnboardingView(true,
                value.ageRange() == null ? null : value.ageRange().apiValue(), value.occupation().name(),
                value.occupationDescription(), value.learningMotivations().stream().map(Enum::name).toList(),
                value.primaryGoal(), value.difficultySkills().stream().map(Enum::name).toList(),
                value.currentLevel().name(), value.weeklyAvailabilityMinutes(), value.completedAt());
    }
}
