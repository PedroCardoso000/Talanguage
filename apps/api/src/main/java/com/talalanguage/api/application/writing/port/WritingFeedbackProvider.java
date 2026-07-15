package com.talalanguage.api.application.writing.port;

import com.talalanguage.api.domain.writing.WritingChallenge;
import com.talalanguage.api.domain.writing.WritingFeedback;

public interface WritingFeedbackProvider {

    WritingFeedback review(WritingChallenge challenge, String content);
}
