package com.talalanguage.api.infrastructure.auth;

import com.talalanguage.api.application.auth.port.PasswordResetTokenRepository;
import com.talalanguage.api.domain.auth.PasswordResetToken;
import com.talalanguage.api.domain.auth.UserId;
import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("test")
public class InMemoryPasswordResetTokenRepository implements PasswordResetTokenRepository {
    private final Map<String, PasswordResetToken> tokensByHash = new ConcurrentHashMap<>();

    @Override
    public void invalidateActiveByUserId(UserId userId, Instant invalidatedAt) {
        tokensByHash.replaceAll((hash, token) -> token.userId().equals(userId) && token.usedAt() == null
                ? token.invalidate(invalidatedAt) : token);
    }

    @Override
    public PasswordResetToken save(PasswordResetToken token) {
        tokensByHash.put(token.tokenHash(), token);
        return token;
    }

    @Override
    public Optional<PasswordResetToken> findByTokenHashForUpdate(String tokenHash) {
        return Optional.ofNullable(tokensByHash.get(tokenHash));
    }

    public boolean containsRawToken(String rawToken) { return tokensByHash.containsKey(rawToken); }
    public void clear() { tokensByHash.clear(); }
}
