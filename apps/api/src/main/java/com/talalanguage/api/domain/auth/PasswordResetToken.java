package com.talalanguage.api.domain.auth;

import java.time.Instant;
import java.util.Objects;

public final class PasswordResetToken {

    private final String id;
    private final UserId userId;
    private final String tokenHash;
    private final Instant expiresAt;
    private final Instant usedAt;
    private final Instant createdAt;

    public PasswordResetToken(String id, UserId userId, String tokenHash, Instant expiresAt, Instant usedAt, Instant createdAt) {
        this.id = requireText(id, "Token id is required.");
        this.userId = Objects.requireNonNull(userId, "User id is required.");
        this.tokenHash = requireText(tokenHash, "Token hash is required.");
        this.expiresAt = Objects.requireNonNull(expiresAt, "Expiration is required.");
        this.usedAt = usedAt;
        this.createdAt = Objects.requireNonNull(createdAt, "Creation time is required.");
        if (!expiresAt.isAfter(createdAt)) {
            throw new IllegalArgumentException("Expiration must be after creation.");
        }
    }

    public boolean isUsableAt(Instant now) {
        return usedAt == null && now.isBefore(expiresAt);
    }

    public PasswordResetToken consume(Instant now) {
        if (!isUsableAt(now)) {
            throw new IllegalStateException("Password reset token is not usable.");
        }
        return new PasswordResetToken(id, userId, tokenHash, expiresAt, now, createdAt);
    }

    public PasswordResetToken invalidate(Instant now) {
        if (usedAt != null) {
            return this;
        }
        return new PasswordResetToken(id, userId, tokenHash, expiresAt, now, createdAt);
    }

    private static String requireText(String value, String message) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(message);
        }
        return value;
    }

    public String id() { return id; }
    public UserId userId() { return userId; }
    public String tokenHash() { return tokenHash; }
    public Instant expiresAt() { return expiresAt; }
    public Instant usedAt() { return usedAt; }
    public Instant createdAt() { return createdAt; }
}
