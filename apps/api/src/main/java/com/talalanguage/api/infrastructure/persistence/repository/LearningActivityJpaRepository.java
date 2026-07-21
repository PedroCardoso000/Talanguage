package com.talalanguage.api.infrastructure.persistence.repository;

import com.talalanguage.api.infrastructure.persistence.entity.LearningActivityEntity;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LearningActivityJpaRepository extends JpaRepository<LearningActivityEntity, String> {

    List<LearningActivityEntity> findByUserIdOrderByCompletedAtAsc(String userId);

    Optional<LearningActivityEntity> findByUserIdAndTypeAndSourceId(String userId, String type, String sourceId);

    List<LearningActivityEntity> findByUserIdAndCompletedAtBetweenOrderByCompletedAtAsc(
            String userId,
            Instant startInclusive,
            Instant endInclusive
    );
}
