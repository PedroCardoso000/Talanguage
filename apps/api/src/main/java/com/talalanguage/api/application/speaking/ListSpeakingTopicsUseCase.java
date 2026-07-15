package com.talalanguage.api.application.speaking;

import com.talalanguage.api.application.speaking.model.SpeakingTopicView;
import com.talalanguage.api.application.speaking.port.SpeakingTopicRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ListSpeakingTopicsUseCase {

    private final SpeakingTopicRepository speakingTopicRepository;

    public ListSpeakingTopicsUseCase(SpeakingTopicRepository speakingTopicRepository) {
        this.speakingTopicRepository = speakingTopicRepository;
    }

    public List<SpeakingTopicView> execute() {
        return speakingTopicRepository.findAll().stream()
                .map(SpeakingTopicView::from)
                .toList();
    }
}
