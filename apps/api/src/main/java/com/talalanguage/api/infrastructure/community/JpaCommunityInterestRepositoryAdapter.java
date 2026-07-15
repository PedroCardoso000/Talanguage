package com.talalanguage.api.infrastructure.community;

import com.talalanguage.api.application.community.port.CommunityInterestRepository;
import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.community.CommunityInterest;
import com.talalanguage.api.domain.community.CommunityInterestTargetType;
import com.talalanguage.api.infrastructure.persistence.entity.CommunityInterestEntity;
import com.talalanguage.api.infrastructure.persistence.repository.CommunityInterestJpaRepository;
import java.util.Optional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("!test")
public class JpaCommunityInterestRepositoryAdapter implements CommunityInterestRepository {

    private final CommunityInterestJpaRepository communityInterestJpaRepository;

    public JpaCommunityInterestRepositoryAdapter(CommunityInterestJpaRepository communityInterestJpaRepository) {
        this.communityInterestJpaRepository = communityInterestJpaRepository;
    }

    @Override
    public Optional<CommunityInterest> findByUserIdAndTarget(
            UserId userId,
            CommunityInterestTargetType targetType,
            String targetId
    ) {
        return communityInterestJpaRepository
                .findByUserIdAndTargetTypeAndTargetId(userId.value(), targetType.name(), targetId)
                .map(this::toDomain);
    }

    @Override
    public CommunityInterest save(CommunityInterest interest) {
        communityInterestJpaRepository.save(new CommunityInterestEntity(
                interest.id(),
                interest.userId().value(),
                interest.targetType().name(),
                interest.targetId(),
                interest.createdAt()
        ));
        return interest;
    }

    private CommunityInterest toDomain(CommunityInterestEntity entity) {
        return new CommunityInterest(
                entity.getId(),
                UserId.from(entity.getUserId()),
                CommunityInterestTargetType.valueOf(entity.getTargetType()),
                entity.getTargetId(),
                entity.getCreatedAt()
        );
    }
}
