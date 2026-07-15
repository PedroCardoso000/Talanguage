package com.talalanguage.api.infrastructure.persistence.repository;

import com.talalanguage.api.infrastructure.persistence.entity.WritingSubmissionMetricEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WritingSubmissionMetricJpaRepository extends JpaRepository<WritingSubmissionMetricEntity, String> {

    List<WritingSubmissionMetricEntity> findTop5ByUserIdOrderByReviewedAtDesc(String userId);
}
