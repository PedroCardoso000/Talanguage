package com.talalanguage.api.application.community.model;

import com.talalanguage.api.domain.community.PracticePartnerProfile;
import java.util.List;

public record PracticePartnerView(
        String userId,
        String displayName,
        List<String> languagesPracticed,
        String level,
        String availabilityNote
) {
    public static PracticePartnerView from(PracticePartnerProfile partner) {
        return new PracticePartnerView(
                partner.userId(),
                partner.displayName(),
                partner.languagesPracticed().stream().map(Enum::name).toList(),
                partner.level().name(),
                partner.availabilityNote()
        );
    }
}
