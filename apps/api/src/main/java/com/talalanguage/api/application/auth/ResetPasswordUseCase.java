package com.talalanguage.api.application.auth;

import com.talalanguage.api.application.auth.exception.InvalidPasswordResetTokenException;
import com.talalanguage.api.application.auth.port.ApplicationClock;
import com.talalanguage.api.application.auth.port.PasswordHasher;
import com.talalanguage.api.application.auth.port.PasswordResetTokenGenerator;
import com.talalanguage.api.application.auth.port.PasswordResetTokenRepository;
import com.talalanguage.api.application.auth.port.SessionService;
import com.talalanguage.api.application.auth.port.UserRepository;
import java.time.Instant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ResetPasswordUseCase {

    private final PasswordResetTokenRepository tokenRepository;
    private final PasswordResetTokenGenerator tokenGenerator;
    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;
    private final SessionService sessionService;
    private final ApplicationClock clock;

    public ResetPasswordUseCase(PasswordResetTokenRepository tokenRepository,
            PasswordResetTokenGenerator tokenGenerator, UserRepository userRepository,
            PasswordHasher passwordHasher, SessionService sessionService, ApplicationClock clock) {
        this.tokenRepository = tokenRepository;
        this.tokenGenerator = tokenGenerator;
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
        this.sessionService = sessionService;
        this.clock = clock;
    }

    @Transactional
    public void execute(Command command) {
        RegisterUserUseCase.validatePasswordStrength(command.newPassword());
        if (command.token() == null || command.token().isBlank()) {
            throw new InvalidPasswordResetTokenException();
        }

        Instant now = clock.now();
        var resetToken = tokenRepository.findByTokenHashForUpdate(tokenGenerator.hash(command.token()))
                .filter(token -> token.isUsableAt(now))
                .orElseThrow(InvalidPasswordResetTokenException::new);
        var user = userRepository.findById(resetToken.userId())
                .orElseThrow(InvalidPasswordResetTokenException::new);

        userRepository.save(user.changePassword(passwordHasher.hash(command.newPassword()), now));
        tokenRepository.save(resetToken.consume(now));
        sessionService.invalidateAll(user.id());
    }

    public record Command(String token, String newPassword) { }
}
