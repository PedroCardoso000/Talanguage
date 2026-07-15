package com.talalanguage.api.application.community.model;

public record CommunityInterestView(
        String id,
        String targetType,
        String targetId,
        String createdAt,
        boolean alreadyRegistered
) {
}
