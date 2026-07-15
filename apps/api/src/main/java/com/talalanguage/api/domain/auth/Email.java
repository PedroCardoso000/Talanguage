package com.talalanguage.api.domain.auth;

import java.util.Objects;
import java.util.regex.Pattern;

public record Email(String value) {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$", Pattern.CASE_INSENSITIVE);

    public Email {
        Objects.requireNonNull(value, "Email is required.");
        String normalized = value.trim().toLowerCase();

        if (!EMAIL_PATTERN.matcher(normalized).matches()) {
            throw new IllegalArgumentException("Invalid email.");
        }

        value = normalized;
    }

    public static Email of(String value) {
        return new Email(value);
    }
}
