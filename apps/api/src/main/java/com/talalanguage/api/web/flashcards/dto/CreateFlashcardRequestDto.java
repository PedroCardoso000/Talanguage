package com.talalanguage.api.web.flashcards.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public record CreateFlashcardRequestDto(
        @NotBlank(message = "Frente do flashcard e obrigatoria.")
        String front,
        @NotBlank(message = "Verso do flashcard e obrigatorio.")
        String back,
        @NotBlank(message = "Idioma e obrigatorio.")
        String language,
        @NotEmpty(message = "Informe ao menos uma tag.")
        List<String> tags
) {
}
