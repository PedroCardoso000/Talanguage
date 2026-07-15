package com.talalanguage.api.application.flashcards.port;

import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.flashcards.Flashcard;
import java.util.List;
import java.util.Optional;

public interface FlashcardRepository {

    List<Flashcard> listByUserId(UserId userId);

    Optional<Flashcard> findByIdAndUserId(String flashcardId, UserId userId);

    Flashcard save(Flashcard flashcard);

    void deleteByIdAndUserId(String flashcardId, UserId userId);
}
