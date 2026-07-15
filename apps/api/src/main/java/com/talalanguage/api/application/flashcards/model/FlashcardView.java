package com.talalanguage.api.application.flashcards.model;

import com.talalanguage.api.domain.flashcards.Flashcard;
import java.util.List;

public record FlashcardView(
        String id,
        String front,
        String back,
        String language,
        String nextReviewAt,
        List<String> tags
) {

    public static FlashcardView from(Flashcard flashcard) {
        return new FlashcardView(
                flashcard.id(),
                flashcard.front(),
                flashcard.back(),
                flashcard.language().name(),
                flashcard.nextReviewAt().toString(),
                flashcard.tags()
        );
    }
}
