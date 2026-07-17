package com.talalanguage.api.infrastructure.auth;

import com.talalanguage.api.application.auth.port.PasswordResetNotifier;
import com.talalanguage.api.domain.auth.Email;
import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class InMemoryPasswordResetNotifier implements PasswordResetNotifier {
    private final Map<String, String> tokensByEmail = new ConcurrentHashMap<>();

    @Override
    public void send(Email recipient, String rawToken, Instant expiresAt) {
        tokensByEmail.put(recipient.value(), rawToken);
    }

    public Optional<String> tokenFor(String email) { return Optional.ofNullable(tokensByEmail.get(email)); }
    public void clear() { tokensByEmail.clear(); }
}
