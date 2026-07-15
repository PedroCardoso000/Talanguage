package com.talalanguage.api.domain.community;

import com.talalanguage.api.domain.auth.UserId;
import java.time.Instant;
import java.util.UUID;

public record CommunityInterest(
        String id,
        UserId userId,
        CommunityInterestTargetType targetType,
        String targetId,
        Instant createdAt
) {
    public static CommunityInterest create(
            UserId userId,
            CommunityInterestTargetType targetType,
            String targetId
    ) {
        return new CommunityInterest(
                UUID.randomUUID().toString(),
                userId,
                targetType,
                targetId,
                Instant.now()
        );
    }
}
