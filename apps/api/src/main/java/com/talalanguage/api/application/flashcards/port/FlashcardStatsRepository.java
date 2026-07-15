package com.talalanguage.api.application.flashcards.port;

import com.talalanguage.api.application.flashcards.model.FlashcardStatsView;
import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.flashcards.ReviewRating;

public interface FlashcardStatsRepository {

    FlashcardStatsView getByUserId(UserId userId);

    void recordReview(UserId userId, ReviewRating rating);
}
