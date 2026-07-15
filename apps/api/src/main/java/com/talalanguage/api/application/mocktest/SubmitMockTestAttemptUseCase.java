package com.talalanguage.api.application.mocktest;

import com.talalanguage.api.application.mocktest.exception.MockTestNotFoundException;
import com.talalanguage.api.application.mocktest.model.MockTestAttemptView;
import com.talalanguage.api.application.mocktest.port.MockTestAttemptRepository;
import com.talalanguage.api.application.mocktest.port.MockTestCatalog;
import com.talalanguage.api.application.progress.RegisterLearningActivityUseCase;
import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.mocktest.MockTestAttempt;
import com.talalanguage.api.domain.mocktest.MockTestAttemptAnswer;
import com.talalanguage.api.domain.progress.ActivityType;
import com.talalanguage.api.domain.progress.SkillType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class SubmitMockTestAttemptUseCase {

    private final MockTestCatalog mockTestCatalog;
    private final MockTestAttemptRepository mockTestAttemptRepository;
    private final RegisterLearningActivityUseCase registerLearningActivityUseCase;

    public SubmitMockTestAttemptUseCase(
            MockTestCatalog mockTestCatalog,
            MockTestAttemptRepository mockTestAttemptRepository,
            RegisterLearningActivityUseCase registerLearningActivityUseCase
    ) {
        this.mockTestCatalog = mockTestCatalog;
        this.mockTestAttemptRepository = mockTestAttemptRepository;
        this.registerLearningActivityUseCase = registerLearningActivityUseCase;
    }

    public MockTestAttemptView execute(Command command) {
        var mockTest = mockTestCatalog.findById(command.mockTestId()).orElseThrow(MockTestNotFoundException::new);
        if (command.answers() == null || command.answers().isEmpty()) {
            throw new IllegalArgumentException("Responda todas as perguntas antes de finalizar.");
        }

        Map<String, String> answersByQuestionId = new HashMap<>();
        for (AnswerCommand answer : command.answers()) {
            String questionId = answer.questionId().trim();
            if (answersByQuestionId.putIfAbsent(questionId, answer.selectedOption()) != null) {
                throw new IllegalArgumentException("Cada pergunta deve ter apenas uma resposta.");
            }
        }

        if (answersByQuestionId.size() != mockTest.questions().size()) {
            throw new IllegalArgumentException("Responda todas as perguntas antes de finalizar.");
        }

        List<MockTestAttemptAnswer> evaluatedAnswers = mockTest.questions().stream()
                .map(question -> {
                    String selectedOption = answersByQuestionId.get(question.id());
                    if (selectedOption == null) {
                        throw new IllegalArgumentException("Responda todas as perguntas antes de finalizar.");
                    }
                    if (!question.options().contains(selectedOption)) {
                        throw new IllegalArgumentException("Opcao selecionada invalida para a pergunta " + question.id() + ".");
                    }

                    return new MockTestAttemptAnswer(
                            mockTest.questions().indexOf(question),
                            question.id(),
                            selectedOption,
                            question.correctOption(),
                            question.explanation(),
                            selectedOption.equals(question.correctOption())
                    );
                })
                .toList();

        int score = (int) evaluatedAnswers.stream().filter(MockTestAttemptAnswer::correct).count();
        MockTestAttempt attempt = mockTestAttemptRepository.save(MockTestAttempt.create(
                UserId.from(command.userId()),
                mockTest.id(),
                score,
                mockTest.questions().size(),
                buildRecommendation(score, mockTest.questions().size()),
                evaluatedAnswers
        ));

        registerLearningActivityUseCase.execute(new RegisterLearningActivityUseCase.Command(
                command.userId(),
                ActivityType.MOCK_TEST,
                SkillType.CONSISTENCY,
                calculateProgressScore(score, mockTest.questions().size()),
                attempt.completedAt(),
                attempt.id()
        ));

        return MockTestAttemptView.from(attempt);
    }

    private int calculateProgressScore(int score, int totalQuestions) {
        if (totalQuestions <= 0) {
            return 0;
        }

        return (int) Math.round((score * 100.0) / totalQuestions);
    }

    private String buildRecommendation(int score, int totalQuestions) {
        double ratio = totalQuestions <= 0 ? 0 : score / (double) totalQuestions;
        if (ratio >= 0.8) {
            return "Voce ja tem base consistente. Priorize conversacao e escrita guiada para ganhar naturalidade.";
        }
        if (ratio >= 0.4) {
            return "Sua base existe, mas ainda esta instavel. Reforce revisao diaria e exercicios curtos de escrita.";
        }
        return "Voce esta tentando avancar sem consolidar fundamento. Foque em vocabulario essencial, presente simples e leitura curta diaria.";
    }

    public record Command(
            String userId,
            String mockTestId,
            List<AnswerCommand> answers
    ) {
    }

    public record AnswerCommand(
            String questionId,
            String selectedOption
    ) {
    }
}
