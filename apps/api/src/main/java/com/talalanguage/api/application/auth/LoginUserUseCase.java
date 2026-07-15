package com.talalanguage.api.application.auth;

import com.talalanguage.api.application.auth.exception.InvalidCredentialsException;
import com.talalanguage.api.application.auth.model.AuthSessionResult;
import com.talalanguage.api.application.auth.port.PasswordHasher;
import com.talalanguage.api.application.auth.port.SessionService;
import com.talalanguage.api.application.auth.port.UserRepository;
import com.talalanguage.api.domain.auth.Email;
import com.talalanguage.api.domain.auth.User;
import org.springframework.stereotype.Service;

@Service
public class LoginUserUseCase {

    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;
    private final SessionService sessionService;

    public LoginUserUseCase(
            UserRepository userRepository,
            PasswordHasher passwordHasher,
            SessionService sessionService
    ) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
        this.sessionService = sessionService;
    }

    public AuthSessionResult execute(Command command) {
        User user = userRepository.findByEmail(Email.of(command.email()))
                .orElseThrow(InvalidCredentialsException::new);

        if (!passwordHasher.matches(command.password(), user.passwordHash())) {
            throw new InvalidCredentialsException();
        }

        return AuthSessionResult.from(user, sessionService.createSession(user.id()));
    }

    public record Command(
            String email,
            String password
    ) {
    }
}
