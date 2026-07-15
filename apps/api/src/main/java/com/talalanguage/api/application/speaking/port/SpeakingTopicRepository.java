package com.talalanguage.api.application.speaking.port;

import com.talalanguage.api.domain.speaking.SpeakingTopic;
import java.util.List;
import java.util.Optional;

public interface SpeakingTopicRepository {

    List<SpeakingTopic> findAll();

    Optional<SpeakingTopic> findById(String id);
}
