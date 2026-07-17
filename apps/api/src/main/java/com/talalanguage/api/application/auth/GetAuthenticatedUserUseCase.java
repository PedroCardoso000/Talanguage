package com.talalanguage.api.application.auth;

import com.talalanguage.api.application.auth.exception.AuthenticationRequiredException;
import com.talalanguage.api.application.auth.model.AuthUserView;
import com.talalanguage.api.application.auth.port.UserRepository;
import com.talalanguage.api.domain.auth.User;
import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.application.onboarding.port.LearnerOnboardingRepository;
import org.springframework.stereotype.Service;

@Service
public class GetAuthenticatedUserUseCase {

    private final UserRepository userRepository;
    private final LearnerOnboardingRepository onboardingRepository;

    public GetAuthenticatedUserUseCase(UserRepository userRepository, LearnerOnboardingRepository onboardingRepository) {
        this.userRepository = userRepository;
        this.onboardingRepository = onboardingRepository;
    }

    public AuthUserView execute(Command command) {
        User user = userRepository.findById(UserId.from(command.userId()))
                .orElseThrow(AuthenticationRequiredException::new);

        return AuthUserView.from(user, onboardingRepository.existsByUserId(user.id()));
    }

    public record Command(String userId) {
    }
}
