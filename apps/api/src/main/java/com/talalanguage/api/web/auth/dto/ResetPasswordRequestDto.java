package com.talalanguage.api.web.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record ResetPasswordRequestDto(
        @NotBlank(message = "Token is required.") String token,
        @NotBlank(message = "New password is required.") String newPassword
) { }
