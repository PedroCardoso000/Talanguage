package com.talalanguage.api.infrastructure.speaking;

import com.talalanguage.api.application.speaking.port.SpeakingTopicRepository;
import com.talalanguage.api.domain.speaking.SpeakingLanguage;
import com.talalanguage.api.domain.speaking.SpeakingLevel;
import com.talalanguage.api.domain.speaking.SpeakingTopic;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class InMemorySpeakingTopicRepository implements SpeakingTopicRepository {

    private static final List<SpeakingTopic> TOPICS = List.of(
            new SpeakingTopic(
                    "presentation",
                    "Apresentacao profissional",
                    SpeakingLanguage.ENGLISH,
                    SpeakingLevel.INTERMEDIATE,
                    "Trabalho",
                    "Treinar apresentacao pessoal e contexto profissional.",
                    "Imagine que voce esta se apresentando em uma reuniao internacional. Fale com clareza e mostre confianca.",
                    "Tell me about your current role and the kind of projects you usually work on.",
                    "What result from your work are you most proud of, and why?"
            ),
            new SpeakingTopic(
                    "travel",
                    "Check-in no hotel",
                    SpeakingLanguage.ENGLISH,
                    SpeakingLevel.BEGINNER,
                    "Viagem",
                    "Treinar vocabulario util para viagens.",
                    "Voce chegou ao hotel e precisa confirmar reserva, horarios e pedir ajuda com o quarto.",
                    "You have just arrived at the hotel. How would you confirm your reservation at the front desk?",
                    "Now ask one extra question about breakfast, Wi-Fi, or check-out time."
            ),
            new SpeakingTopic(
                    "interview",
                    "Entrevista de emprego",
                    SpeakingLanguage.ENGLISH,
                    SpeakingLevel.ADVANCED,
                    "Carreira",
                    "Ganhar confianca em respostas de entrevista.",
                    "Explique sua experiencia, destaque resultados e responda com objetividade.",
                    "Why are you interested in this role, and how does your background prepare you for it?",
                    "Give one concrete example that proves your impact in a previous position."
            )
    );

    @Override
    public List<SpeakingTopic> findAll() {
        return TOPICS;
    }

    @Override
    public Optional<SpeakingTopic> findById(String id) {
        return TOPICS.stream()
                .filter(topic -> topic.id().equals(id))
                .findFirst();
    }
}
