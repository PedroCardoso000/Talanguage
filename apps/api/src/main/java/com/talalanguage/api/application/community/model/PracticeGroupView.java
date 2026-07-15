package com.talalanguage.api.application.community.model;

import com.talalanguage.api.domain.community.PracticeGroup;

public record PracticeGroupView(
        String id,
        String title,
        String language,
        String level,
        String description,
        int memberCount
) {
    public static PracticeGroupView from(PracticeGroup group) {
        return new PracticeGroupView(
                group.id(),
                group.title(),
                group.language().name(),
                group.level().name(),
                group.description(),
                group.memberCount()
        );
    }
}
