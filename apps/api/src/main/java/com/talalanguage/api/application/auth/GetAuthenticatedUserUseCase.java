package com.talalanguage.api.application.auth;

import com.talalanguage.api.application.auth.exception.AuthenticationRequiredException;
import com.talalanguage.api.application.auth.model.AuthUserView;
import com.talalanguage.api.application.auth.port.UserRepository;
import com.talalanguage.api.domain.auth.User;
import com.talalanguage.api.domain.auth.UserId;
import org.springframework.stereotype.Service;

@Service
public class GetAuthenticatedUserUseCase {

    private final UserRepository userRepository;

    public GetAuthenticatedUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AuthUserView execute(Command command) {
        User user = userRepository.findById(UserId.from(command.userId()))
                .orElseThrow(AuthenticationRequiredException::new);

        return AuthUserView.from(user);
    }

    public record Command(String userId) {
    }
}
