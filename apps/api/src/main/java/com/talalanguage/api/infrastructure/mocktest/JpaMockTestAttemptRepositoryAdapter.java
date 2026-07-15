package com.talalanguage.api.infrastructure.mocktest;

import com.talalanguage.api.application.mocktest.port.MockTestAttemptRepository;
import com.talalanguage.api.domain.auth.UserId;
import com.talalanguage.api.domain.mocktest.MockTestAttempt;
import com.talalanguage.api.domain.mocktest.MockTestAttemptAnswer;
import com.talalanguage.api.infrastructure.persistence.entity.MockTestAttemptAnswerEntity;
import com.talalanguage.api.infrastructure.persistence.entity.MockTestAttemptEntity;
import com.talalanguage.api.infrastructure.persistence.repository.MockTestAttemptAnswerJpaRepository;
import com.talalanguage.api.infrastructure.persistence.repository.MockTestAttemptJpaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("!test")
public class JpaMockTestAttemptRepositoryAdapter implements MockTestAttemptRepository {

    private final MockTestAttemptJpaRepository mockTestAttemptJpaRepository;
    private final MockTestAttemptAnswerJpaRepository mockTestAttemptAnswerJpaRepository;

    public JpaMockTestAttemptRepositoryAdapter(
            MockTestAttemptJpaRepository mockTestAttemptJpaRepository,
            MockTestAttemptAnswerJpaRepository mockTestAttemptAnswerJpaRepository
    ) {
        this.mockTestAttemptJpaRepository = mockTestAttemptJpaRepository;
        this.mockTestAttemptAnswerJpaRepository = mockTestAttemptAnswerJpaRepository;
    }

    @Override
    public MockTestAttempt save(MockTestAttempt attempt) {
        mockTestAttemptJpaRepository.save(new MockTestAttemptEntity(
                attempt.id(),
                attempt.userId().value(),
                attempt.mockTestId(),
                attempt.score(),
                attempt.totalQuestions(),
                attempt.recommendation(),
                attempt.completedAt()
        ));

        mockTestAttemptAnswerJpaRepository.deleteByAttemptId(attempt.id());
        mockTestAttemptAnswerJpaRepository.saveAll(
                attempt.answers().stream().map(answer -> new MockTestAttemptAnswerEntity(
                        attempt.id() + ":" + answer.questionId(),
                        attempt.id(),
                        answer.orderIndex(),
                        answer.questionId(),
                        answer.selectedOption(),
                        answer.correctOption(),
                        answer.explanation(),
                        answer.correct()
                )).toList()
        );
        return attempt;
    }

    @Override
    public Optional<MockTestAttempt> findByIdAndUserId(String attemptId, UserId userId) {
        return mockTestAttemptJpaRepository.findByIdAndUserId(attemptId, userId.value())
                .map(entity -> toDomain(
                        entity,
                        mockTestAttemptAnswerJpaRepository.findByAttemptIdOrderByOrderIndexAsc(entity.getId())
                ));
    }

    private MockTestAttempt toDomain(
            MockTestAttemptEntity attemptEntity,
            List<MockTestAttemptAnswerEntity> answerEntities
    ) {
        return MockTestAttempt.restore(
                attemptEntity.getId(),
                UserId.from(attemptEntity.getUserId()),
                attemptEntity.getMockTestId(),
                attemptEntity.getScore(),
                attemptEntity.getTotalQuestions(),
                attemptEntity.getRecommendation(),
                attemptEntity.getCompletedAt(),
                answerEntities.stream().map(answer -> new MockTestAttemptAnswer(
                        answer.getOrderIndex(),
                        answer.getQuestionId(),
                        answer.getSelectedOption(),
                        answer.getCorrectOption(),
                        answer.getExplanation(),
                        answer.isCorrect()
                )).toList()
        );
    }
}
