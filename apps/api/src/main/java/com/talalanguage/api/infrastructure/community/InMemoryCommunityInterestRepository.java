package com.talalanguage.api.infrastructure.community;

import com.talalanguage.api.application.community.port.CommunityInterestRepository;
import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.community.CommunityInterest;
import com.talalanguage.api.domain.community.CommunityInterestTargetType;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("test")
public class InMemoryCommunityInterestRepository implements CommunityInterestRepository {

    private final CopyOnWriteArrayList<CommunityInterest> interests = new CopyOnWriteArrayList<>();

    @Override
    public Optional<CommunityInterest> findByUserIdAndTarget(
            UserId userId,
            CommunityInterestTargetType targetType,
            String targetId
    ) {
        return interests.stream()
                .filter(interest -> interest.userId().equals(userId))
                .filter(interest -> interest.targetType() == targetType)
                .filter(interest -> interest.targetId().equals(targetId))
                .findFirst();
    }

    @Override
    public CommunityInterest save(CommunityInterest interest) {
        interests.add(interest);
        return interest;
    }

    public void clear() {
        interests.clear();
    }
}
