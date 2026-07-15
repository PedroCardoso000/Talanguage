package com.talalanguage.api.web.writing.dto;

import com.talalanguage.api.application.writing.model.CreatedWritingSubmissionView;

public record CreateWritingSubmissionResponseDto(
        String submissionId,
        String status,
        WritingFeedbackResponseDto feedback
) {

    public static CreateWritingSubmissionResponseDto from(CreatedWritingSubmissionView view) {
        return new CreateWritingSubmissionResponseDto(
                view.submissionId(),
                view.status(),
                WritingFeedbackResponseDto.from(view.feedback())
        );
    }
}
