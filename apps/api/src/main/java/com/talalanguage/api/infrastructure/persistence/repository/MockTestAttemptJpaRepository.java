package com.talalanguage.api.infrastructure.persistence.repository;

import com.talalanguage.api.infrastructure.persistence.entity.MockTestAttemptEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MockTestAttemptJpaRepository extends JpaRepository<MockTestAttemptEntity, String> {

    Optional<MockTestAttemptEntity> findByIdAndUserId(String id, String userId);
}
