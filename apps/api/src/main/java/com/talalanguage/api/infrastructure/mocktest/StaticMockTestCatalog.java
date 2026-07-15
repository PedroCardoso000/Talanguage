package com.talalanguage.api.infrastructure.mocktest;

import com.talalanguage.api.application.mocktest.model.MockTestDefinition;
import com.talalanguage.api.application.mocktest.model.MockTestQuestionDefinition;
import com.talalanguage.api.application.mocktest.port.MockTestCatalog;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class StaticMockTestCatalog implements MockTestCatalog {

    private static final MockTestDefinition CURRENT = new MockTestDefinition(
            "baseline-english-v1",
            "Diagnostico rapido de ingles",
            List.of(
                    new MockTestQuestionDefinition(
                            "q1",
                            "Qual frase esta gramaticalmente correta?",
                            List.of(
                                    "She go to work every day.",
                                    "She goes to work every day.",
                                    "She going to work every day.",
                                    "She gone to work every day."
                            ),
                            "She goes to work every day.",
                            "No presente simples, he/she/it pede verbo com s."
                    ),
                    new MockTestQuestionDefinition(
                            "q2",
                            "Qual expressao e mais apropriada para uma apresentacao formal?",
                            List.of(
                                    "What's up, everyone?",
                                    "Let me walk you through the main points.",
                                    "This stuff is super cool.",
                                    "You guys should look at this."
                            ),
                            "Let me walk you through the main points.",
                            "E a opcao mais profissional e comum em contexto formal."
                    ),
                    new MockTestQuestionDefinition(
                            "q3",
                            "Qual palavra completa melhor a frase: I need to ___ this report by 5 PM.",
                            List.of("finish", "finishing", "finished", "finishes"),
                            "finish",
                            "Apos 'need to', usamos o verbo na forma base."
                    ),
                    new MockTestQuestionDefinition(
                            "q4",
                            "Qual resposta mostra mais naturalidade em uma conversa?",
                            List.of(
                                    "I am agree with you.",
                                    "I very like this idea.",
                                    "I totally agree with you.",
                                    "I agree you very much."
                            ),
                            "I totally agree with you.",
                            "A estrutura esta correta e soa natural."
                    ),
                    new MockTestQuestionDefinition(
                            "q5",
                            "Qual conectivo ajuda a contrastar ideias em um texto?",
                            List.of("Because", "However", "Therefore", "Also"),
                            "However",
                            "However e usado para introduzir contraste."
                    )
            )
    );

    @Override
    public MockTestDefinition current() {
        return CURRENT;
    }

    @Override
    public Optional<MockTestDefinition> findById(String id) {
        if (CURRENT.id().equals(id)) {
            return Optional.of(CURRENT);
        }

        return Optional.empty();
    }
}
