package com.talalanguage.api.domain.auth;

import java.time.Instant;
import java.util.Objects;

public record AuthenticatedSession(
        String token,
        UserId userId,
        Instant createdAt
) {

    public AuthenticatedSession {
        Objects.requireNonNull(token, "Token is required.");
        Objects.requireNonNull(userId, "User id is required.");
        Objects.requireNonNull(createdAt, "Created at is required.");

        if (token.isBlank()) {
            throw new IllegalArgumentException("Token is required.");
        }
    }
}
