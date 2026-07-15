package com.talalanguage.api.infrastructure.flashcards;

import com.talalanguage.api.application.flashcards.port.ReviewSchedulePolicy;
import com.talalanguage.api.domain.flashcards.ReviewRating;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.springframework.stereotype.Component;

@Component
public class SimpleReviewSchedulePolicy implements ReviewSchedulePolicy {

    @Override
    public Instant nextReviewAt(ReviewRating rating, Instant referenceTime) {
        return switch (rating) {
            case AGAIN -> referenceTime.plus(1, ChronoUnit.DAYS);
            case HARD -> referenceTime.plus(2, ChronoUnit.DAYS);
            case GOOD -> referenceTime.plus(4, ChronoUnit.DAYS);
            case EASY -> referenceTime.plus(7, ChronoUnit.DAYS);
        };
    }
}
