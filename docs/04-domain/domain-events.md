# Domain Events — Talanguage

## Purpose

This document defines initial domain event candidates for Talanguage.

Domain events represent meaningful business facts that already happened. They must not be used to introduce messaging complexity prematurely.

## Event Rules

A domain event must:

- represent something that happened;
- use past tense;
- belong to a bounded context;
- carry only necessary data;
- avoid infrastructure details.

The first version may handle events in-process. Do not introduce external messaging unless explicitly approved.

## Initial Domain Event Candidates

### LearnerRegistered

Context: Identity

Emitted when a learner account/profile is created.

Possible use:

- initialize default goals;
- start onboarding state;
- create welcome activity.

### LearnerOnboardingCompleted

Context: Identity

Emitted when the learner completes initial setup.

Possible use:

- personalize dashboard;
- set preferred target language;
- initialize first practice recommendations.

### SpeakingSessionStarted

Context: Learning Practice

Emitted when a speaking session starts.

Possible use:

- record activity;
- update current practice state;
- trigger progress tracking later.

### SpeakingSessionCompleted

Context: Learning Practice

Emitted when a speaking session is completed.

Possible use:

- update progress;
- update streak;
- generate practice summary;
- show recent activity.

### WritingSubmissionCreated

Context: Learning Practice

Emitted when a learner submits a writing answer.

Possible use:

- request feedback;
- record practice activity;
- update progress.

### WritingFeedbackGenerated

Context: Learning Practice or AI Assistance

Emitted when feedback is generated for a writing submission.

Possible use:

- notify UI through refresh/polling later;
- update feedback status;
- update progress summary.

### FlashcardCreated

Context: Review

Emitted when a learner saves a new review item.

Possible use:

- update review count;
- include in dashboard summary.

### ReviewSessionCompleted

Context: Review

Emitted when a review session ends.

Possible use:

- update progress;
- update daily goal;
- update card knowledge statuses.

### DailyGoalCompleted

Context: Progress

Emitted when a learner completes a daily goal.

Possible use:

- update streak;
- show completion state;
- record achievement.

### LearningStreakUpdated

Context: Progress

Emitted when streak changes.

Possible use:

- update dashboard;
- show progress summary.

### CommunityGroupJoined

Context: Community

Emitted when a learner joins a practice group.

Possible use:

- update community summary;
- show group membership.

## Event Handling in First Version

The first version should prefer:

- direct application service orchestration;
- in-process event handling;
- transactional consistency where needed;
- simple logs for traceability.

Do not introduce:

- Kafka;
- RabbitMQ;
- event sourcing;
- distributed sagas;
- complex async workflows.

## Existing Frontend Context

The frontend may show mocked “recent activity” items.

When implementing real behavior, recent activity may be derived from completed practice/review events or activity read models.

Do not make the frontend invent activity history from local actions alone.

## Allowed

- define domain events as internal facts;
- use events to update progress/read models;
- keep event payloads small;
- handle events in process for first version.

## Forbidden

- create external event bus without approval;
- use events for simple method calls with no business meaning;
- put API request payloads directly as domain events;
- include infrastructure data in domain events;
- create event sourcing by default.

## Definition of Done

A domain event is ready when:

- it has a past-tense name;
- it represents a meaningful business fact;
- it belongs to one context;
- it contains minimal data;
- its handlers are explicit and simple.
