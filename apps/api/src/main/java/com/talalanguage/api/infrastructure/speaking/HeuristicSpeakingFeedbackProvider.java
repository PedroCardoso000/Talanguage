package com.talalanguage.api.infrastructure.speaking;

import com.talalanguage.api.application.speaking.port.SpeakingFeedbackProvider;
import com.talalanguage.api.domain.speaking.SpeakingFeedback;
import com.talalanguage.api.domain.speaking.SpeakingSession;
import com.talalanguage.api.domain.speaking.SpeakingTopic;
import org.springframework.stereotype.Component;

@Component
public class HeuristicSpeakingFeedbackProvider implements SpeakingFeedbackProvider {

    @Override
    public SpeakingFeedback evaluate(SpeakingSession session, SpeakingTopic topic) {
        int words = session.totalWordCount();
        int sentences = session.totalSentenceCount();
        int responseCount = session.responseCount();

        int score = 20;
        if (responseCount >= 1) {
            score += 20;
        }
        if (responseCount >= 2) {
            score += 15;
        }
        if (words >= 20) {
            score += 20;
        }
        if (words >= 40) {
            score += 15;
        }
        if (sentences >= 2) {
            score += 10;
        }

        String feedback;
        String nextAction;
        if (responseCount >= 2 && words >= 35 && sentences >= 3) {
            feedback = "Boa pratica. Voce sustentou a conversa por mais de uma troca e trouxe detalhes suficientes para o tema.";
            nextAction = "Repita o mesmo tema tentando variar mais o vocabulario e fechar cada resposta com um exemplo concreto.";
        } else if (responseCount >= 1 && words >= 15) {
            feedback = "Sessao valida. A ideia principal apareceu, mas ainda faltou expandir melhor as respostas.";
            nextAction = "Na proxima pratica, responda cada pergunta com pelo menos duas frases curtas e uma informacao de apoio.";
        } else {
            feedback = "Tentativa inicial registrada. A resposta ficou curta demais para parecer conversa natural.";
            nextAction = "Na proxima pratica, escreva respostas mais completas antes de finalizar a sessao.";
        }

        return new SpeakingFeedback(Math.min(score, 100), feedback, nextAction);
    }
}
