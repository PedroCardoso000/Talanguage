package com.talalanguage.api.web.community.dto;

import com.talalanguage.api.application.community.model.PracticeGroupView;

public record PracticeGroupResponseDto(
        String id,
        String title,
        String language,
        String level,
        String description,
        int memberCount
) {
    public static PracticeGroupResponseDto from(PracticeGroupView view) {
        return new PracticeGroupResponseDto(
                view.id(),
                view.title(),
                view.language(),
                view.level(),
                view.description(),
                view.memberCount()
        );
    }
}
