package com.talalanguage.api.application.progress.model;

public record SkillProgressView(
        int speaking,
        int writing,
        int vocabulary,
        int consistency
) {
}
