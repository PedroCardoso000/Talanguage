package com.talalanguage.api.infrastructure.persistence.repository;

import com.talalanguage.api.infrastructure.persistence.entity.CommunityInterestEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityInterestJpaRepository extends JpaRepository<CommunityInterestEntity, String> {

    Optional<CommunityInterestEntity> findByUserIdAndTargetTypeAndTargetId(String userId, String targetType, String targetId);
}
