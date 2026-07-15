package com.talalanguage.api.web.flashcards.dto;

import com.talalanguage.api.application.flashcards.model.FlashcardReviewView;

public record FlashcardReviewResponseDto(
        String id,
        String nextReviewAt,
        int reviewCount
) {

    public static FlashcardReviewResponseDto from(FlashcardReviewView view) {
        return new FlashcardReviewResponseDto(view.id(), view.nextReviewAt(), view.reviewCount());
    }
}
