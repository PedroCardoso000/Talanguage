package com.talalanguage.api.domain.community;

import java.util.List;

public record PracticePartnerProfile(
        String userId,
        String displayName,
        List<CommunityLanguage> languagesPracticed,
        CommunityLevel level,
        String availabilityNote
) {
}
