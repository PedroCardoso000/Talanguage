package com.talalanguage.api.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "mock_test_attempt_answer")
public class MockTestAttemptAnswerEntity {

    @Id
    @Column(nullable = false, length = 128)
    private String id;

    @Column(name = "attempt_id", nullable = false, length = 64)
    private String attemptId;

    @Column(name = "order_index", nullable = false)
    private int orderIndex;

    @Column(name = "question_id", nullable = false, length = 128)
    private String questionId;

    @Column(name = "selected_option", nullable = false, columnDefinition = "text")
    private String selectedOption;

    @Column(name = "correct_option", nullable = false, columnDefinition = "text")
    private String correctOption;

    @Column(nullable = false, columnDefinition = "text")
    private String explanation;

    @Column(name = "is_correct", nullable = false)
    private boolean correct;

    protected MockTestAttemptAnswerEntity() {
    }

    public MockTestAttemptAnswerEntity(
            String id,
            String attemptId,
            int orderIndex,
            String questionId,
            String selectedOption,
            String correctOption,
            String explanation,
            boolean correct
    ) {
        this.id = id;
        this.attemptId = attemptId;
        this.orderIndex = orderIndex;
        this.questionId = questionId;
        this.selectedOption = selectedOption;
        this.correctOption = correctOption;
        this.explanation = explanation;
        this.correct = correct;
    }

    public String getId() {
        return id;
    }

    public String getAttemptId() {
        return attemptId;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public String getQuestionId() {
        return questionId;
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    public String getCorrectOption() {
        return correctOption;
    }

    public String getExplanation() {
        return explanation;
    }

    public boolean isCorrect() {
        return correct;
    }
}
