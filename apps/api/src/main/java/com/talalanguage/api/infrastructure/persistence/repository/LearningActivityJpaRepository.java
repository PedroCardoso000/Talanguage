package com.talalanguage.api.infrastructure.persistence.repository;

import com.talalanguage.api.infrastructure.persistence.entity.LearningActivityEntity;
import java.time.Instant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LearningActivityJpaRepository extends JpaRepository<LearningActivityEntity, String> {

    List<LearningActivityEntity> findByUserIdOrderByCompletedAtAsc(String userId);

    List<LearningActivityEntity> findByUserIdAndCompletedAtBetweenOrderByCompletedAtAsc(
            String userId,
            Instant startInclusive,
            Instant endInclusive
    );
}
