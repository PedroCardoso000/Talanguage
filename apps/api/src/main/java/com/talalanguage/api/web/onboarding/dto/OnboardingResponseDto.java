package com.talalanguage.api.web.onboarding.dto;

import com.talalanguage.api.application.onboarding.model.OnboardingView;

public record OnboardingResponseDto(boolean completed, OnboardingDataResponseDto onboarding) {
    public static OnboardingResponseDto from(OnboardingView value) {
        return new OnboardingResponseDto(value.completed(), value.completed() ? OnboardingDataResponseDto.from(value) : null);
    }
}
