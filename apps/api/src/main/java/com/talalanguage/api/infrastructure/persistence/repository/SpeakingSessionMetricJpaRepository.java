package com.talalanguage.api.infrastructure.persistence.repository;

import com.talalanguage.api.infrastructure.persistence.entity.SpeakingSessionMetricEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeakingSessionMetricJpaRepository extends JpaRepository<SpeakingSessionMetricEntity, String> {

    List<SpeakingSessionMetricEntity> findTop5ByUserIdAndFinishedAtIsNotNullOrderByFinishedAtDesc(String userId);
}
