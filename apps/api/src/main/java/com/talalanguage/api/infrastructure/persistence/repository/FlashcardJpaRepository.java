package com.talalanguage.api.infrastructure.persistence.repository;

import com.talalanguage.api.infrastructure.persistence.entity.FlashcardEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FlashcardJpaRepository extends JpaRepository<FlashcardEntity, String> {

    List<FlashcardEntity> findByUserIdOrderByCreatedAtAsc(String userId);

    Optional<FlashcardEntity> findByIdAndUserId(String id, String userId);

    void deleteByIdAndUserId(String id, String userId);

    @Query("""
            select flashcard
            from FlashcardEntity flashcard
            where flashcard.userId = :userId
              and (
                lower(flashcard.frontText) like :containsPattern escape '\\'
                or lower(flashcard.backText) like :containsPattern escape '\\'
              )
            order by
              case
                when lower(flashcard.frontText) = :normalizedQuery then 0
                when lower(flashcard.frontText) like :prefixPattern escape '\\' then 1
                when lower(flashcard.frontText) like :containsPattern escape '\\' then 2
                else 3
              end,
              lower(flashcard.frontText),
              flashcard.id
            """)
    List<FlashcardEntity> searchByUserId(
            @Param("userId") String userId,
            @Param("normalizedQuery") String normalizedQuery,
            @Param("prefixPattern") String prefixPattern,
            @Param("containsPattern") String containsPattern,
            Pageable pageable
    );
}
