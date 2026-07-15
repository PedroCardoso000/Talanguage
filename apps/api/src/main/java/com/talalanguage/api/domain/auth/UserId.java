package com.talalanguage.api.domain.auth;

import java.util.Objects;
import java.util.UUID;

public record UserId(String value) {

    public UserId {
        Objects.requireNonNull(value, "User id is required.");

        if (value.isBlank()) {
            throw new IllegalArgumentException("User id is required.");
        }
    }

    public static UserId create() {
        return new UserId(UUID.randomUUID().toString());
    }

    public static UserId from(String value) {
        return new UserId(value);
    }
}
