package com.talalanguage.api.application.writing.model;

import com.talalanguage.api.domain.writing.WritingSubmission;

public record WritingSubmissionView(
        String submissionId,
        String challengeId,
        String content,
        String createdAt,
        String status,
        WritingFeedbackView feedback
) {

    public static WritingSubmissionView from(WritingSubmission submission) {
        return new WritingSubmissionView(
                submission.id(),
                submission.challengeId(),
                submission.content(),
                submission.submittedAt().toString(),
                submission.status().name(),
                WritingFeedbackView.from(submission.feedback())
        );
    }
}
