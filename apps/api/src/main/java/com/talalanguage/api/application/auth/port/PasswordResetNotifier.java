package com.talalanguage.api.application.auth.port;

import com.talalanguage.api.domain.auth.Email;
import java.time.Instant;

public interface PasswordResetNotifier {
    void send(Email recipient, String rawToken, Instant expiresAt);
}
