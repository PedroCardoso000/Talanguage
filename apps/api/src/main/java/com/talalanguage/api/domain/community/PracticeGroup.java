package com.talalanguage.api.domain.community;

public record PracticeGroup(
        String id,
        String title,
        CommunityLanguage language,
        CommunityLevel level,
        String description,
        int memberCount,
        boolean active
) {
}
