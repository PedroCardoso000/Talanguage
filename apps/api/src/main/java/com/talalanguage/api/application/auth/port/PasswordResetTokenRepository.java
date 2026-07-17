package com.talalanguage.api.application.auth.port;

import com.talalanguage.api.domain.auth.PasswordResetToken;
import com.talalanguage.api.domain.auth.UserId;
import java.time.Instant;
import java.util.Optional;

public interface PasswordResetTokenRepository {
    void invalidateActiveByUserId(UserId userId, Instant invalidatedAt);
    PasswordResetToken save(PasswordResetToken token);
    Optional<PasswordResetToken> findByTokenHashForUpdate(String tokenHash);
}
