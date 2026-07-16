package com.talalanguage.api.web.onboarding;

import com.talalanguage.api.application.auth.exception.AuthenticationRequiredException;
import com.talalanguage.api.application.onboarding.GetMyOnboardingUseCase;
import com.talalanguage.api.application.onboarding.SaveMyOnboardingUseCase;
import com.talalanguage.api.web.onboarding.dto.OnboardingResponseDto;
import com.talalanguage.api.web.onboarding.dto.SaveOnboardingRequestDto;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/onboarding")
public class OnboardingController {
    private final GetMyOnboardingUseCase getUseCase;
    private final SaveMyOnboardingUseCase saveUseCase;

    public OnboardingController(GetMyOnboardingUseCase getUseCase, SaveMyOnboardingUseCase saveUseCase) {
        this.getUseCase = getUseCase; this.saveUseCase = saveUseCase;
    }

    @GetMapping("/me")
    public OnboardingResponseDto me(Authentication authentication) {
        return OnboardingResponseDto.from(getUseCase.execute(new GetMyOnboardingUseCase.Command(requireUserId(authentication))));
    }

    @PutMapping("/me")
    public OnboardingResponseDto save(Authentication authentication, @Valid @RequestBody SaveOnboardingRequestDto request) {
        return OnboardingResponseDto.from(saveUseCase.execute(new SaveMyOnboardingUseCase.Command(
                requireUserId(authentication), request.ageRange(), request.occupation(), request.occupationDescription(),
                request.learningMotivations(), request.primaryGoal(), request.difficultySkills(), request.currentLevel(),
                request.weeklyAvailabilityMinutes())));
    }

    private String requireUserId(Authentication authentication) {
        if (authentication == null || authentication.getName() == null || authentication.getName().isBlank()) {
            throw new AuthenticationRequiredException();
        }
        return authentication.getName();
    }
}
