package com.talalanguage.api.infrastructure.auth;

import com.talalanguage.api.application.auth.port.PasswordResetNotifier;
import com.talalanguage.api.domain.auth.Email;
import java.time.Instant;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/** Production delivery adapter intentionally sends nothing until an authorized e-mail integration exists. */
@Component
@Profile("!test")
public class PendingPasswordResetNotifier implements PasswordResetNotifier {
    @Override
    public void send(Email recipient, String rawToken, Instant expiresAt) {
        // Intentionally empty: never expose sensitive reset data through a fallback channel.
    }
}
