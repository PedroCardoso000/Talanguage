package com.talalanguage.api.infrastructure.flashcards;

import com.talalanguage.api.application.flashcards.port.FlashcardRepository;
import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.flashcards.Flashcard;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("test")
public class InMemoryFlashcardRepository implements FlashcardRepository {

    private final Map<String, Flashcard> flashcardsById = new ConcurrentHashMap<>();

    @Override
    public List<Flashcard> listByUserId(UserId userId) {
        return flashcardsById.values().stream()
                .filter(flashcard -> flashcard.userId().equals(userId))
                .sorted((left, right) -> left.createdAt().compareTo(right.createdAt()))
                .toList();
    }

    @Override
    public java.util.Optional<Flashcard> findByIdAndUserId(String flashcardId, UserId userId) {
        Flashcard flashcard = flashcardsById.get(flashcardId);
        if (flashcard == null || !flashcard.userId().equals(userId)) {
            return java.util.Optional.empty();
        }
        return java.util.Optional.of(flashcard);
    }

    @Override
    public Flashcard save(Flashcard flashcard) {
        flashcardsById.put(flashcard.id(), flashcard);
        return flashcard;
    }

    @Override
    public void deleteByIdAndUserId(String flashcardId, UserId userId) {
        Flashcard flashcard = flashcardsById.get(flashcardId);
        if (flashcard != null && flashcard.userId().equals(userId)) {
            flashcardsById.remove(flashcardId);
        }
    }

    public void clear() {
        flashcardsById.clear();
    }
}
