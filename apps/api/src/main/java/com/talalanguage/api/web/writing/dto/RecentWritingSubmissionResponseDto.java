package com.talalanguage.api.web.writing.dto;

import com.talalanguage.api.application.writing.model.WritingSubmissionView;

public record RecentWritingSubmissionResponseDto(
        String submissionId,
        String challengeId,
        String content,
        String createdAt,
        String status,
        WritingFeedbackResponseDto feedback
) {

    public static RecentWritingSubmissionResponseDto from(WritingSubmissionView view) {
        return new RecentWritingSubmissionResponseDto(
                view.submissionId(),
                view.challengeId(),
                view.content(),
                view.createdAt(),
                view.status(),
                WritingFeedbackResponseDto.from(view.feedback())
        );
    }
}
