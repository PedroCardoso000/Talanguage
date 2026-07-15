package com.talalanguage.api.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "speaking_session_metric")
public class SpeakingSessionMetricEntity {

    @Id
    @Column(nullable = false, length = 64)
    private String id;

    @Column(name = "user_id", nullable = false, length = 64)
    private String userId;

    @Column(nullable = false, length = 32)
    private String language;

    @Column(nullable = false, length = 32)
    private String level;

    @Column(name = "topic_id", nullable = false, length = 128)
    private String topicId;

    @Column(nullable = false, length = 32)
    private String status;

    @Column(name = "current_prompt", nullable = false, columnDefinition = "text")
    private String currentPrompt;

    @Column(name = "response_count", nullable = false)
    private int responseCount;

    @Column(name = "total_word_count", nullable = false)
    private int totalWordCount;

    @Column(name = "total_sentence_count", nullable = false)
    private int totalSentenceCount;

    @Column(name = "started_at", nullable = false)
    private Instant startedAt;

    @Column(name = "finished_at")
    private Instant finishedAt;

    @Column(name = "duration_seconds")
    private Long durationSeconds;

    @Column
    private Integer score;

    @Column
    private Integer pronunciation;

    @Column
    private Integer fluency;

    @Column
    private Integer clarity;

    @Column(name = "feedback_summary", columnDefinition = "text")
    private String feedbackSummary;

    @Column(name = "next_action", columnDefinition = "text")
    private String nextAction;

    protected SpeakingSessionMetricEntity() {
    }

    public SpeakingSessionMetricEntity(
            String id,
            String userId,
            String language,
            String level,
            String topicId,
            String status,
            String currentPrompt,
            int responseCount,
            int totalWordCount,
            int totalSentenceCount,
            Instant startedAt,
            Instant finishedAt,
            Long durationSeconds,
            Integer score,
            Integer pronunciation,
            Integer fluency,
            Integer clarity,
            String feedbackSummary,
            String nextAction
    ) {
        this.id = id;
        this.userId = userId;
        this.language = language;
        this.level = level;
        this.topicId = topicId;
        this.status = status;
        this.currentPrompt = currentPrompt;
        this.responseCount = responseCount;
        this.totalWordCount = totalWordCount;
        this.totalSentenceCount = totalSentenceCount;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
        this.durationSeconds = durationSeconds;
        this.score = score;
        this.pronunciation = pronunciation;
        this.fluency = fluency;
        this.clarity = clarity;
        this.feedbackSummary = feedbackSummary;
        this.nextAction = nextAction;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getLanguage() {
        return language;
    }

    public String getLevel() {
        return level;
    }

    public String getTopicId() {
        return topicId;
    }

    public String getStatus() {
        return status;
    }

    public String getCurrentPrompt() {
        return currentPrompt;
    }

    public int getResponseCount() {
        return responseCount;
    }

    public int getTotalWordCount() {
        return totalWordCount;
    }

    public int getTotalSentenceCount() {
        return totalSentenceCount;
    }

    public Instant getStartedAt() {
        return startedAt;
    }

    public Instant getFinishedAt() {
        return finishedAt;
    }

    public Long getDurationSeconds() {
        return durationSeconds;
    }

    public Integer getScore() {
        return score;
    }

    public Integer getPronunciation() {
        return pronunciation;
    }

    public Integer getFluency() {
        return fluency;
    }

    public Integer getClarity() {
        return clarity;
    }

    public String getFeedbackSummary() {
        return feedbackSummary;
    }

    public String getNextAction() {
        return nextAction;
    }
}
