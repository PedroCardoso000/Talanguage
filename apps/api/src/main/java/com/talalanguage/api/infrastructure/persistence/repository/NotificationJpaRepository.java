package com.talalanguage.api.infrastructure.persistence.repository;

import com.talalanguage.api.infrastructure.persistence.entity.NotificationEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationJpaRepository extends JpaRepository<NotificationEntity, String> {

    List<NotificationEntity> findByUserIdOrderByCreatedAtDesc(String userId);

    Optional<NotificationEntity> findByIdAndUserId(String id, String userId);
}
