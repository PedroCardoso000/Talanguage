package com.talalanguage.api.application.auth.port;

import com.talalanguage.api.domain.auth.AuthenticatedSession;
import com.talalanguage.api.domain.auth.UserId;
import java.util.Optional;

public interface SessionService {

    AuthenticatedSession createSession(UserId userId);

    Optional<AuthenticatedSession> findByToken(String token);

    void invalidate(String token);
}
