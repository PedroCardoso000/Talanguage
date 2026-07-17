package com.talalanguage.api.application.onboarding.port;

import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.onboarding.LearnerOnboarding;
import java.util.Optional;

public interface LearnerOnboardingRepository {
    Optional<LearnerOnboarding> findByUserId(UserId userId);
    LearnerOnboarding save(LearnerOnboarding onboarding);
    boolean existsByUserId(UserId userId);
}
