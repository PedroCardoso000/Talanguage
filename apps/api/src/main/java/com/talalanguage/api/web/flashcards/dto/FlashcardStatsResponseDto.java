package com.talalanguage.api.web.flashcards.dto;

import com.talalanguage.api.application.flashcards.model.FlashcardStatsView;

public record FlashcardStatsResponseDto(
        int reviewedCount,
        int correctCount,
        int wrongCount,
        int overallProgress
) {

    public static FlashcardStatsResponseDto from(FlashcardStatsView view) {
        return new FlashcardStatsResponseDto(
                view.reviewedCount(),
                view.correctCount(),
                view.wrongCount(),
                view.overallProgress()
        );
    }
}
