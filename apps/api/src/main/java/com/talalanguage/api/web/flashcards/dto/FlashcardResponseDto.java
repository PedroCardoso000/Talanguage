package com.talalanguage.api.web.flashcards.dto;

import com.talalanguage.api.application.flashcards.model.FlashcardView;
import java.util.List;

public record FlashcardResponseDto(
        String id,
        String front,
        String back,
        String language,
        String nextReviewAt,
        List<String> tags
) {

    public static FlashcardResponseDto from(FlashcardView view) {
        return new FlashcardResponseDto(
                view.id(),
                view.front(),
                view.back(),
                view.language(),
                view.nextReviewAt(),
                view.tags()
        );
    }
}
