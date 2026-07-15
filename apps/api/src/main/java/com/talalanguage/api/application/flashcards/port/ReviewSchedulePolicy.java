package com.talalanguage.api.application.flashcards.port;

import com.talalanguage.api.domain.flashcards.ReviewRating;
import java.time.Instant;

public interface ReviewSchedulePolicy {

    Instant nextReviewAt(ReviewRating rating, Instant referenceTime);
}
