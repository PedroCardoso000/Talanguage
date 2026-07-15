package com.talalanguage.api.web.auth.dto;

import com.talalanguage.api.application.auth.model.AuthSessionResult;

public record AuthSessionResponseDto(
        AuthUserResponseDto user,
        String accessToken
) {

    public static AuthSessionResponseDto from(AuthSessionResult result) {
        return new AuthSessionResponseDto(AuthUserResponseDto.from(result.user()), result.accessToken());
    }
}
