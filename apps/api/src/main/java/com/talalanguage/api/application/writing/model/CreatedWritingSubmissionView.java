package com.talalanguage.api.application.writing.model;

public record CreatedWritingSubmissionView(
        String submissionId,
        String status,
        WritingFeedbackView feedback
) {
}
