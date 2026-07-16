package com.talalanguage.api.application.auth;

import com.talalanguage.api.application.auth.port.ApplicationClock;
import com.talalanguage.api.application.auth.port.PasswordResetNotifier;
import com.talalanguage.api.application.auth.port.PasswordResetRequestLimiter;
import com.talalanguage.api.application.auth.port.PasswordResetTokenGenerator;
import com.talalanguage.api.application.auth.port.PasswordResetTokenRepository;
import com.talalanguage.api.application.auth.port.UserRepository;
import com.talalanguage.api.domain.auth.Email;
import com.talalanguage.api.domain.auth.PasswordResetToken;
import java.time.Duration;
import java.time.Instant;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RequestPasswordResetUseCase {

    static final Duration TOKEN_TTL = Duration.ofMinutes(15);

    private final UserRepository userRepository;
    private final PasswordResetTokenRepository tokenRepository;
    private final PasswordResetTokenGenerator tokenGenerator;
    private final PasswordResetNotifier notifier;
    private final PasswordResetRequestLimiter requestLimiter;
    private final ApplicationClock clock;

    public RequestPasswordResetUseCase(UserRepository userRepository, PasswordResetTokenRepository tokenRepository,
            PasswordResetTokenGenerator tokenGenerator, PasswordResetNotifier notifier,
            PasswordResetRequestLimiter requestLimiter, ApplicationClock clock) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.tokenGenerator = tokenGenerator;
        this.notifier = notifier;
        this.requestLimiter = requestLimiter;
        this.clock = clock;
    }

    @Transactional
    public void execute(Command command) {
        Email email = Email.of(command.email());
        Instant now = clock.now();
        if (!requestLimiter.allow(email, now)) {
            return;
        }

        userRepository.findByEmail(email).ifPresent(user -> {
            tokenRepository.invalidateActiveByUserId(user.id(), now);
            var generated = tokenGenerator.generate();
            Instant expiresAt = now.plus(TOKEN_TTL);
            tokenRepository.save(new PasswordResetToken(
                    UUID.randomUUID().toString(), user.id(), generated.tokenHash(), expiresAt, null, now));
            notifier.send(email, generated.rawToken(), expiresAt);
        });
    }

    public record Command(String email) { }
}
