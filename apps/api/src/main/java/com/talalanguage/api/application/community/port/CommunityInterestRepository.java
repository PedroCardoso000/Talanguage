package com.talalanguage.api.application.community.port;

import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.community.CommunityInterest;
import com.talalanguage.api.domain.community.CommunityInterestTargetType;
import java.util.Optional;

public interface CommunityInterestRepository {

    Optional<CommunityInterest> findByUserIdAndTarget(UserId userId, CommunityInterestTargetType targetType, String targetId);

    CommunityInterest save(CommunityInterest interest);
}
