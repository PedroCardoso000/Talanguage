package com.talalanguage.api.application.speaking.port;

import com.talalanguage.api.domain.speaking.SpeakingFeedback;
import com.talalanguage.api.domain.speaking.SpeakingSession;
import com.talalanguage.api.domain.speaking.SpeakingTopic;

public interface SpeakingFeedbackProvider {

    SpeakingFeedback evaluate(SpeakingSession session, SpeakingTopic topic);
}
