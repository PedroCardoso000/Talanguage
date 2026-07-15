package com.talalanguage.api.application.writing.model;

import com.talalanguage.api.domain.writing.WritingFeedback;
import java.util.List;

public record WritingFeedbackView(
        List<String> strengths,
        List<String> improvements,
        String nextAction
) {

    public static WritingFeedbackView from(WritingFeedback feedback) {
        return new WritingFeedbackView(
                feedback.strengths(),
                feedback.improvements(),
                feedback.nextAction()
        );
    }
}
