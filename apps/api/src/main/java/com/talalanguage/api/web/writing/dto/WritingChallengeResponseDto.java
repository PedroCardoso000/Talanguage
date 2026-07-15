package com.talalanguage.api.web.writing.dto;

import com.talalanguage.api.application.writing.model.WritingChallengeView;

public record WritingChallengeResponseDto(
        String id,
        String language,
        String level,
        String prompt,
        String focus
) {

    public static WritingChallengeResponseDto from(WritingChallengeView view) {
        return new WritingChallengeResponseDto(view.id(), view.language(), view.level(), view.prompt(), view.focus());
    }
}
