package com.talalanguage.api.application.profile;

import com.talalanguage.api.application.auth.exception.AuthenticationRequiredException;
import com.talalanguage.api.application.auth.port.UserRepository;
import com.talalanguage.api.application.profile.model.ProfileView;
import com.talalanguage.api.domain.auth.UserId;
import org.springframework.stereotype.Service;

@Service
public class GetProfileUseCase {

    private final UserRepository userRepository;

    public GetProfileUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ProfileView execute(Command command) {
        var user = userRepository.findById(UserId.from(command.userId()))
                .orElseThrow(AuthenticationRequiredException::new);

        return ProfileView.from(user);
    }

    public record Command(String userId) {
    }
}
