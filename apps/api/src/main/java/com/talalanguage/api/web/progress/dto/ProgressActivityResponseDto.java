package com.talalanguage.api.web.progress.dto;

import com.talalanguage.api.application.progress.model.ProgressActivityView;

public record ProgressActivityResponseDto(
        String id,
        String type,
        String title,
        String occurredAt
) {

    public static ProgressActivityResponseDto from(ProgressActivityView view) {
        return new ProgressActivityResponseDto(view.id(), view.type(), view.title(), view.occurredAt());
    }
}
