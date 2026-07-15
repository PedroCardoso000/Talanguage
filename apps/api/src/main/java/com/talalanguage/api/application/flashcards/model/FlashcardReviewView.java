package com.talalanguage.api.application.flashcards.model;

import com.talalanguage.api.domain.flashcards.Flashcard;

public record FlashcardReviewView(
        String id,
        String nextReviewAt,
        int reviewCount
) {

    public static FlashcardReviewView from(Flashcard flashcard) {
        return new FlashcardReviewView(
                flashcard.id(),
                flashcard.nextReviewAt().toString(),
                flashcard.reviewCount()
        );
    }
}
