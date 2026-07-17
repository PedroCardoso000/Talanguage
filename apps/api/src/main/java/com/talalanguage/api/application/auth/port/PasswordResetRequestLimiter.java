package com.talalanguage.api.application.auth.port;

import com.talalanguage.api.domain.auth.Email;
import java.time.Instant;

public interface PasswordResetRequestLimiter {
    boolean allow(Email email, Instant now);
}
