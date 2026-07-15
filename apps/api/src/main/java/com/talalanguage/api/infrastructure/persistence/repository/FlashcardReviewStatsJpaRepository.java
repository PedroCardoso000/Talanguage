package com.talalanguage.api.infrastructure.persistence.repository;

import com.talalanguage.api.infrastructure.persistence.entity.FlashcardReviewStatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlashcardReviewStatsJpaRepository extends JpaRepository<FlashcardReviewStatsEntity, String> {
}
