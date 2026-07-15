package com.talalanguage.api.infrastructure.auth;

import com.talalanguage.api.application.auth.port.SessionService;
import com.talalanguage.api.domain.auth.AuthenticatedSession;
import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.infrastructure.persistence.entity.UserSessionEntity;
import com.talalanguage.api.infrastructure.persistence.repository.UserSessionJpaRepository;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Base64;
import java.util.Optional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("!test")
public class JpaSessionService implements SessionService {

    private final SecureRandom secureRandom = new SecureRandom();
    private final UserSessionJpaRepository userSessionJpaRepository;

    public JpaSessionService(UserSessionJpaRepository userSessionJpaRepository) {
        this.userSessionJpaRepository = userSessionJpaRepository;
    }

    @Override
    public AuthenticatedSession createSession(UserId userId) {
        AuthenticatedSession session = new AuthenticatedSession(generateToken(), userId, Instant.now());
        userSessionJpaRepository.save(new UserSessionEntity(
                session.token(),
                session.userId().value(),
                session.createdAt()
        ));
        return session;
    }

    @Override
    public Optional<AuthenticatedSession> findByToken(String token) {
        if (token == null || token.isBlank()) {
            return Optional.empty();
        }

        return userSessionJpaRepository.findById(token)
                .map(entity -> new AuthenticatedSession(
                        entity.getToken(),
                        UserId.from(entity.getUserId()),
                        entity.getCreatedAt()
                ));
    }

    @Override
    public void invalidate(String token) {
        if (token == null || token.isBlank()) {
            return;
        }

        userSessionJpaRepository.deleteById(token);
    }

    private String generateToken() {
        byte[] bytes = new byte[32];
        secureRandom.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
}
