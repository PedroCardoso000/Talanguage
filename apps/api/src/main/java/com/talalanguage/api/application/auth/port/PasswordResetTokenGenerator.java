package com.talalanguage.api.application.auth.port;

public interface PasswordResetTokenGenerator {
    GeneratedToken generate();
    String hash(String rawToken);

    record GeneratedToken(String rawToken, String tokenHash) { }
}
