package com.talalanguage.api.web.flashcards.dto;

import jakarta.validation.constraints.NotBlank;

public record ReviewFlashcardRequestDto(
        @NotBlank(message = "Rating e obrigatorio.")
        String rating
) {
}
