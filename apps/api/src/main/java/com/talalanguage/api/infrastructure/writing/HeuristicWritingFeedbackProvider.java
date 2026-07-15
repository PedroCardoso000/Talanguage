package com.talalanguage.api.infrastructure.writing;

import com.talalanguage.api.application.writing.port.WritingFeedbackProvider;
import com.talalanguage.api.domain.writing.WritingChallenge;
import com.talalanguage.api.domain.writing.WritingFeedback;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class HeuristicWritingFeedbackProvider implements WritingFeedbackProvider {

    @Override
    public WritingFeedback review(WritingChallenge challenge, String content) {
        String normalized = content == null ? "" : content.trim();
        int words = normalized.isEmpty() ? 0 : normalized.split("\\s+").length;
        boolean hasPunctuation = normalized.matches(".*[.!?,;:].*");
        boolean hasConnector = normalized.matches("(?i).*(\\b(because|but|and|so|however|then|also|although)\\b).*");
        boolean hasMultipleSentences = normalized.split("[.!?]+").length >= 2;

        List<String> strengths = new ArrayList<>();
        List<String> improvements = new ArrayList<>();

        if (words >= 20) {
            strengths.add("Voce ja conseguiu desenvolver a ideia com mais de uma frase.");
        } else {
            improvements.add("Expanda a resposta. O texto ainda esta curto para soar natural e completo.");
        }

        if (hasPunctuation) {
            strengths.add("Voce usou pontuacao, o que ajuda a separar melhor as ideias.");
        } else {
            improvements.add("Adicione pontuacao basica para deixar a leitura menos confusa.");
        }

        if (hasConnector) {
            strengths.add("Ha pelo menos um conector ligando partes do texto, o que melhora a fluidez.");
        } else {
            improvements.add("Use conectores simples como because, but, so ou however para ligar melhor as frases.");
        }

        if (hasMultipleSentences) {
            strengths.add("O texto nao ficou preso em uma frase unica. Isso ajuda na clareza.");
        } else {
            improvements.add("Separe a resposta em pelo menos duas frases para organizar melhor a mensagem.");
        }

        if (strengths.isEmpty()) {
            strengths.add("Voce entregou uma tentativa valida. Isso ja e melhor do que nao escrever.");
        }

        while (improvements.size() < 2) {
            improvements.add("Revise a resposta e torne a ideia principal mais clara e especifica.");
        }

        int score = 20;
        if (words >= 12) {
            score += 25;
        }
        if (words >= 25) {
            score += 15;
        }
        if (hasPunctuation) {
            score += 15;
        }
        if (hasConnector) {
            score += 15;
        }
        if (hasMultipleSentences) {
            score += 10;
        }

        String nextAction = words < 20
                ? "Reescreva a resposta com pelo menos 4 frases curtas e uma ideia concreta de apoio."
                : hasConnector
                ? "Agora reescreva o texto deixando a abertura mais objetiva e o fechamento mais natural."
                : "Reescreva o texto adicionando um conector simples entre as ideias para ganhar fluidez.";

        return new WritingFeedback(
                Math.min(score, 100),
                strengths,
                improvements.stream().distinct().limit(3).toList(),
                nextAction
        );
    }
}
