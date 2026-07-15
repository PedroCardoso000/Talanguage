package com.talalanguage.api.application.progress.model;

public record ProgressActivityView(
        String id,
        String type,
        String title,
        String occurredAt
) {
}
