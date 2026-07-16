package com.talalanguage.api.domain.auth;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import org.junit.jupiter.api.Test;

class PasswordResetTokenTest {

    private static final Instant CREATED_AT = Instant.parse("2026-07-16T12:00:00Z");

    @Test
    void shouldBeUsableOnlyBeforeExpirationAndBeforeConsumption() {
        var token = tokenExpiringAt(CREATED_AT.plusSeconds(900));
        assertTrue(token.isUsableAt(CREATED_AT.plusSeconds(899)));

        var consumed = token.consume(CREATED_AT.plusSeconds(60));
        assertFalse(consumed.isUsableAt(CREATED_AT.plusSeconds(61)));
        assertThrows(IllegalStateException.class, () -> consumed.consume(CREATED_AT.plusSeconds(62)));
    }

    @Test
    void shouldRejectExpiredToken() {
        var token = tokenExpiringAt(CREATED_AT.plusSeconds(900));
        assertFalse(token.isUsableAt(CREATED_AT.plusSeconds(900)));
        assertThrows(IllegalStateException.class, () -> token.consume(CREATED_AT.plusSeconds(901)));
    }

    private PasswordResetToken tokenExpiringAt(Instant expiresAt) {
        return new PasswordResetToken("id", UserId.from("user-id"), "hash", expiresAt, null, CREATED_AT);
    }
}
