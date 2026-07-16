package com.talalanguage.api.application.onboarding;

import com.talalanguage.api.application.onboarding.model.OnboardingView;
import com.talalanguage.api.application.onboarding.port.LearnerOnboardingRepository;
import com.talalanguage.api.domain.auth.UserId;
import org.springframework.stereotype.Service;

@Service
public class GetMyOnboardingUseCase {
    private final LearnerOnboardingRepository repository;

    public GetMyOnboardingUseCase(LearnerOnboardingRepository repository) { this.repository = repository; }

    public OnboardingView execute(Command command) {
        return repository.findByUserId(UserId.from(command.authenticatedUserId()))
                .map(OnboardingView::from).orElseGet(OnboardingView::incomplete);
    }

    public record Command(String authenticatedUserId) { }
}
