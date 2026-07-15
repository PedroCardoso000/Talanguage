# Entities — Talanguage

## Purpose

This document defines initial entity candidates for Talanguage.

Entities have identity and lifecycle. Do not create entities for values that can be represented as value objects or enums.

## Entity Rules

An entity must have:

- stable identity;
- lifecycle;
- clear ownership;
- business meaning.

Entities must not be created only to mirror frontend cards or database tables.

## Initial Entity Candidates

### UserAccount

Context: Identity

Represents an authenticated account.

Fields may include:

- id;
- email;
- password hash or external provider reference;
- status;
- createdAt;
- updatedAt.

Rules:

- email must be unique;
- account status must be explicit;
- authentication details must not leak to other contexts.

### LearnerProfile

Context: Identity

Represents the learner-facing profile.

Fields may include:

- id;
- userId;
- displayName;
- nativeLanguage;
- targetLanguages;
- currentLevel;
- onboardingCompleted.

Rules:

- profile must belong to one account;
- target languages must be supported languages;
- level must use a controlled value.

### PracticeTopic

Context: Learning Practice

Represents a topic available for speaking or writing practice.

Fields may include:

- id;
- title;
- language;
- level;
- category;
- active.

Rules:

- topic must have valid language;
- topic must have valid level;
- inactive topics must not appear as new practice options.

### SpeakingSession

Context: Learning Practice

Represents a speaking practice session.

Fields may include:

- id;
- learnerId;
- language;
- level;
- topicId;
- status;
- startedAt;
- finishedAt.

### ConversationMessage

Context: Learning Practice

Represents a message inside a speaking session when message persistence is needed.

Fields may include:

- id;
- sessionId;
- sender;
- content;
- createdAt.

Rules:

- message must belong to a session;
- sender must be controlled;
- content cannot be empty.

### WritingChallenge

Context: Learning Practice

Represents a writing exercise prompt.

Fields may include:

- id;
- language;
- level;
- prompt;
- category;
- active.

### WritingSubmission

Context: Learning Practice

Represents a learner response to a writing challenge.

Fields may include:

- id;
- learnerId;
- challengeId;
- answer;
- status;
- submittedAt;
- feedbackId.

### PracticeFeedback

Context: Learning Practice or AI Assistance

Represents feedback generated for a practice activity.

Fields may include:

- id;
- targetType;
- targetId;
- score;
- summary;
- improvementPoints;
- createdAt.

Rules:

- feedback must reference a practice result;
- score must be optional or approximate unless evaluation rules are explicit;
- feedback must not claim precision beyond the system capability.

### Flashcard

Context: Review

Represents a saved review item.

Fields may include:

- id;
- learnerId;
- language;
- front;
- back;
- type;
- knowledgeStatus;
- createdAt;
- updatedAt.

### ReviewResult

Context: Review

Represents the result of reviewing a card.

Fields may include:

- id;
- reviewSessionId;
- flashcardId;
- result;
- reviewedAt.

### DailyGoal

Context: Progress

Represents a learner's daily goal.

### ProgressSnapshot

Context: Progress

Represents a calculated summary of progress for display or reporting.

This is usually a read model, not a rich entity.

### CommunityGroup

Context: Community

Represents a group for language practice.

### PracticePartnerProfile

Context: Community

Represents another learner visible as a potential practice partner.

## Existing Frontend Context

Mocked UI entities must be reviewed before becoming backend entities.

Do not create backend entities only because the frontend has a card with similar data.

## Allowed

- create entities with identity and lifecycle;
- keep entities inside clear contexts;
- use value objects for constrained fields;
- expose DTOs to frontend instead of entities.

## Forbidden

- create entity classes with only public setters and no rules;
- treat all JSON responses as entities;
- leak password/auth internals;
- create one universal `Activity` entity for everything without clear meaning;
- expose persistence entities directly to frontend.

## Definition of Done

An entity is ready when:

- it has a stable identity;
- it belongs to one context;
- it has clear lifecycle rules;
- it uses controlled value objects/enums;
- it is not merely a UI card or database row.
