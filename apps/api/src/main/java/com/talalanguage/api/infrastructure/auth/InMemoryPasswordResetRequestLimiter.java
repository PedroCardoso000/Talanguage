package com.talalanguage.api.infrastructure.auth;

import com.talalanguage.api.application.auth.port.PasswordResetRequestLimiter;
import com.talalanguage.api.domain.auth.Email;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.springframework.stereotype.Component;

@Component
public class InMemoryPasswordResetRequestLimiter implements PasswordResetRequestLimiter {
    private static final Duration COOLDOWN = Duration.ofMinutes(1);
    private final Map<String, Instant> nextAllowedByEmail = new ConcurrentHashMap<>();

    @Override
    public boolean allow(Email email, Instant now) {
        AtomicBoolean allowed = new AtomicBoolean(false);
        nextAllowedByEmail.compute(email.value(), (key, nextAllowed) -> {
            if (nextAllowed == null || !now.isBefore(nextAllowed)) {
                allowed.set(true);
                return now.plus(COOLDOWN);
            }
            return nextAllowed;
        });
        return allowed.get();
    }
}
