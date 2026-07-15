package com.talalanguage.api.infrastructure.auth;

import com.talalanguage.api.application.auth.port.SessionService;
import com.talalanguage.api.domain.auth.AuthenticatedSession;
import com.talalanguage.api.domain.auth.UserId;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("test")
public class InMemorySessionService implements SessionService {

    private final SecureRandom secureRandom = new SecureRandom();
    private final Map<String, AuthenticatedSession> sessionsByToken = new ConcurrentHashMap<>();

    @Override
    public AuthenticatedSession createSession(UserId userId) {
        String token = generateToken();
        AuthenticatedSession session = new AuthenticatedSession(token, userId, Instant.now());
        sessionsByToken.put(token, session);
        return session;
    }

    @Override
    public Optional<AuthenticatedSession> findByToken(String token) {
        if (token == null || token.isBlank()) {
            return Optional.empty();
        }

        return Optional.ofNullable(sessionsByToken.get(token));
    }

    @Override
    public void invalidate(String token) {
        if (token == null || token.isBlank()) {
            return;
        }

        sessionsByToken.remove(token);
    }

    public void clear() {
        sessionsByToken.clear();
    }

    private String generateToken() {
        byte[] bytes = new byte[32];
        secureRandom.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
}
