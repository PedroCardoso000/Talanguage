package com.talalanguage.api.web.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ForgotPasswordRequestDto(
        @NotBlank(message = "Email is required.")
        @Email(message = "Email is invalid.")
        String email
) { }
