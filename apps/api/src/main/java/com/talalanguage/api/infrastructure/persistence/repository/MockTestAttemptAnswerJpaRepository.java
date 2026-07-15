package com.talalanguage.api.infrastructure.persistence.repository;

import com.talalanguage.api.infrastructure.persistence.entity.MockTestAttemptAnswerEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MockTestAttemptAnswerJpaRepository extends JpaRepository<MockTestAttemptAnswerEntity, String> {

    List<MockTestAttemptAnswerEntity> findByAttemptIdOrderByOrderIndexAsc(String attemptId);

    void deleteByAttemptId(String attemptId);
}
