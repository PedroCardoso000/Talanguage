package com.talalanguage.api.application.progress;

import com.talalanguage.api.application.progress.model.ProgressActivityView;
import com.talalanguage.api.application.progress.port.LearningActivityRepository;
import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.progress.ActivityType;
import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class GetLearningActivitiesUseCase {

    private final LearningActivityRepository learningActivityRepository;

    public GetLearningActivitiesUseCase(LearningActivityRepository learningActivityRepository) {
        this.learningActivityRepository = learningActivityRepository;
    }

    public List<ProgressActivityView> execute(Command command) {
        return learningActivityRepository.findByUserId(UserId.from(command.userId()))
                .stream()
                .sorted(Comparator.comparing(activity -> activity.completedAt(), Comparator.reverseOrder()))
                .limit(20)
                .map(activity -> new ProgressActivityView(
                        activity.id(),
                        activity.type().name(),
                        titleFor(activity.type()),
                        activity.completedAt().toString()
                ))
                .toList();
    }

    private String titleFor(ActivityType type) {
        return switch (type) {
            case SPEAKING -> "Treino de fala concluido";
            case WRITING -> "Desafio de escrita enviado";
            case FLASHCARDS -> "Revisao de flashcards realizada";
            case GOALS -> "Meta atualizada";
            case MOCK_TEST -> "Simulado finalizado";
        };
    }

    public record Command(String userId) {
    }
}
