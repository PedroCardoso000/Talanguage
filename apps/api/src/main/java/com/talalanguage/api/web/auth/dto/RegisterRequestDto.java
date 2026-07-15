package com.talalanguage.api.web.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequestDto(
        @NotBlank(message = "Name is required.")
        String name,
        @NotBlank(message = "Email is required.")
        @Email(message = "Email must be valid.")
        String email,
        @NotBlank(message = "Password is required.")
        String password,
        String targetLanguage
) {
}
