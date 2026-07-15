package com.talalanguage.api.infrastructure.flashcards;

import com.talalanguage.api.application.flashcards.port.FlashcardRepository;
import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.flashcards.Flashcard;
import com.talalanguage.api.domain.flashcards.FlashcardLanguage;
import com.talalanguage.api.infrastructure.persistence.PersistenceJsonCodec;
import com.talalanguage.api.infrastructure.persistence.entity.FlashcardEntity;
import com.talalanguage.api.infrastructure.persistence.repository.FlashcardJpaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("!test")
public class JpaFlashcardRepositoryAdapter implements FlashcardRepository {

    private final FlashcardJpaRepository flashcardJpaRepository;
    private final PersistenceJsonCodec persistenceJsonCodec;

    public JpaFlashcardRepositoryAdapter(
            FlashcardJpaRepository flashcardJpaRepository,
            PersistenceJsonCodec persistenceJsonCodec
    ) {
        this.flashcardJpaRepository = flashcardJpaRepository;
        this.persistenceJsonCodec = persistenceJsonCodec;
    }

    @Override
    public List<Flashcard> listByUserId(UserId userId) {
        return flashcardJpaRepository.findByUserIdOrderByCreatedAtAsc(userId.value())
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public Optional<Flashcard> findByIdAndUserId(String flashcardId, UserId userId) {
        return flashcardJpaRepository.findByIdAndUserId(flashcardId, userId.value()).map(this::toDomain);
    }

    @Override
    public Flashcard save(Flashcard flashcard) {
        flashcardJpaRepository.save(toEntity(flashcard));
        return flashcard;
    }

    @Override
    public void deleteByIdAndUserId(String flashcardId, UserId userId) {
        flashcardJpaRepository.deleteByIdAndUserId(flashcardId, userId.value());
    }

    private FlashcardEntity toEntity(Flashcard flashcard) {
        return new FlashcardEntity(
                flashcard.id(),
                flashcard.userId().value(),
                flashcard.front(),
                flashcard.back(),
                flashcard.language().name(),
                persistenceJsonCodec.writeStringList(flashcard.tags()),
                flashcard.difficulty(),
                flashcard.reviewCount(),
                flashcard.createdAt(),
                flashcard.nextReviewAt()
        );
    }

    private Flashcard toDomain(FlashcardEntity entity) {
        return Flashcard.restore(
                entity.getId(),
                UserId.from(entity.getUserId()),
                entity.getFrontText(),
                entity.getBackText(),
                FlashcardLanguage.valueOf(entity.getLanguage()),
                persistenceJsonCodec.readStringList(entity.getTagsJson()),
                entity.getDifficulty(),
                entity.getReviewCount(),
                entity.getCreatedAt(),
                entity.getNextReviewAt()
        );
    }
}
