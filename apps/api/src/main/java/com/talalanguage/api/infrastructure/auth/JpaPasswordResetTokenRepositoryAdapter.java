package com.talalanguage.api.infrastructure.auth;

import com.talalanguage.api.application.auth.port.PasswordResetTokenRepository;
import com.talalanguage.api.domain.auth.PasswordResetToken;
import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.infrastructure.persistence.entity.PasswordResetTokenEntity;
import com.talalanguage.api.infrastructure.persistence.repository.PasswordResetTokenJpaRepository;
import java.time.Instant;
import java.util.Optional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("!test")
public class JpaPasswordResetTokenRepositoryAdapter implements PasswordResetTokenRepository {
    private final PasswordResetTokenJpaRepository repository;

    public JpaPasswordResetTokenRepositoryAdapter(PasswordResetTokenJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void invalidateActiveByUserId(UserId userId, Instant invalidatedAt) {
        repository.invalidateActiveByUserId(userId.value(), invalidatedAt);
    }

    @Override
    public PasswordResetToken save(PasswordResetToken token) {
        repository.save(toEntity(token));
        return token;
    }

    @Override
    public Optional<PasswordResetToken> findByTokenHashForUpdate(String tokenHash) {
        return repository.findByTokenHash(tokenHash).map(this::toDomain);
    }

    private PasswordResetTokenEntity toEntity(PasswordResetToken token) {
        return new PasswordResetTokenEntity(token.id(), token.userId().value(), token.tokenHash(),
                token.expiresAt(), token.usedAt(), token.createdAt());
    }

    private PasswordResetToken toDomain(PasswordResetTokenEntity entity) {
        return new PasswordResetToken(entity.getId(), UserId.from(entity.getUserId()), entity.getTokenHash(),
                entity.getExpiresAt(), entity.getUsedAt(), entity.getCreatedAt());
    }
}
