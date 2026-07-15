package com.talalanguage.api.application.auth.model;

import com.talalanguage.api.domain.auth.AuthenticatedSession;
import com.talalanguage.api.domain.auth.User;

public record AuthSessionResult(
        AuthUserView user,
        String accessToken
) {

    public static AuthSessionResult from(User user, AuthenticatedSession session) {
        return new AuthSessionResult(AuthUserView.from(user), session.token());
    }
}
