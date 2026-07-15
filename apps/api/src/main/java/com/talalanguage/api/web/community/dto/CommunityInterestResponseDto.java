package com.talalanguage.api.web.community.dto;

import com.talalanguage.api.application.community.model.CommunityInterestView;

public record CommunityInterestResponseDto(
        String id,
        String targetType,
        String targetId,
        String createdAt
) {
    public static CommunityInterestResponseDto from(CommunityInterestView view) {
        return new CommunityInterestResponseDto(
                view.id(),
                view.targetType(),
                view.targetId(),
                view.createdAt()
        );
    }
}
