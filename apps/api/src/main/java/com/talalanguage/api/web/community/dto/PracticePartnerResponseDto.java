package com.talalanguage.api.web.community.dto;

import com.talalanguage.api.application.community.model.PracticePartnerView;
import java.util.List;

public record PracticePartnerResponseDto(
        String userId,
        String displayName,
        List<String> languagesPracticed,
        String level,
        String availabilityNote
) {
    public static PracticePartnerResponseDto from(PracticePartnerView view) {
        return new PracticePartnerResponseDto(
                view.userId(),
                view.displayName(),
                view.languagesPracticed(),
                view.level(),
                view.availabilityNote()
        );
    }
}
