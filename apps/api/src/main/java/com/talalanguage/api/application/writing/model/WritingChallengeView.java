package com.talalanguage.api.application.writing.model;

import com.talalanguage.api.domain.writing.WritingChallenge;

public record WritingChallengeView(
        String id,
        String language,
        String level,
        String prompt,
        String focus
) {

    public static WritingChallengeView from(WritingChallenge challenge) {
        return new WritingChallengeView(
                challenge.id(),
                challenge.language().name(),
                challenge.level().name(),
                challenge.prompt(),
                challenge.expectedSkill()
        );
    }
}
