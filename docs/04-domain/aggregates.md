# Aggregates — Talanguage

## Purpose

This document defines the initial aggregate candidates for Talanguage.

Aggregates protect business invariants. They must not be created just because a database table exists.

## Aggregate Rules

An aggregate is justified when:

- it owns business rules;
- it controls valid state transitions;
- it protects consistency boundaries;
- it has meaningful behavior beyond simple storage.

Avoid creating aggregates for passive read models or dashboard summaries.

## Initial Aggregate Candidates

### LearnerProfile

Context: Identity

Represents the learning-facing profile of a user.

May own:

- display name;
- preferred language;
- target language preferences;
- learning level;
- onboarding status.

Must not own:

- password;
- full authentication internals;
- all learning history;
- all progress details.

### SpeakingSession

Context: Learning Practice

Represents a conversation practice session.

Owns:

- selected language;
- selected level;
- topic;
- session status;
- started time;
- finished time;
- practice summary reference or result.

Important invariants:

- a session must start before it can finish;
- a finished session cannot return to in-progress;
- a session must belong to one learner;
- language, level, and topic must be valid.

### WritingSubmission

Context: Learning Practice

Represents a learner answer to a writing challenge.

Owns:

- challenge reference;
- learner answer;
- submission status;
- submitted time;
- feedback result.

Important invariants:

- answer cannot be empty;
- submitted content cannot be modified after feedback without a new revision;
- feedback belongs to the submitted answer version.

### Flashcard

Context: Review

Represents a saved word, phrase, or expression for review.

Owns:

- front content;
- back content;
- language;
- type;
- learner owner;
- knowledge status.

Important invariants:

- front content must not be empty;
- back content must not be empty;
- language must be valid;
- card must belong to a learner.

### ReviewSession

Context: Review

Represents a set of flashcards reviewed in one practice moment.

Owns:

- learner;
- cards reviewed;
- results;
- started time;
- finished time.

Important invariants:

- session must contain at least one card;
- a finished review session cannot accept new results;
- review results must reference cards in the session.

### DailyGoal

Context: Progress

Represents the learner's current daily practice goal.

Owns:

- learner;
- target metric;
- target value;
- date or recurrence rule;
- completion status.

Important invariants:

- target must be positive;
- goal status must be derived from completion data;
- a completed goal cannot be completed twice for the same date.

### CommunityGroup

Context: Community

Represents a group or space for language practice.

Owns:

- name;
- target language;
- topic or purpose;
- visibility;
- member summary.

Important invariants:

- group must have a valid language;
- group must have a name;
- group visibility must be explicit.

## Read Models Are Not Aggregates

The following are usually read models, not aggregates:

- DashboardSummary;
- ProgressOverview;
- RecentActivity;
- SkillProgressChart;
- PracticeRecommendation;
- CommunityPreview.

These may compose data from multiple contexts.

## Existing Frontend Context

Mocked frontend cards may imply aggregates where only read models are needed.

Before creating an aggregate from UI mock data, ask:

- Does this object protect a business rule?
- Does it have behavior?
- Does it own state transitions?
- Or is it just data displayed to the user?

## Allowed

- create aggregates only for meaningful consistency boundaries;
- keep aggregates small;
- expose DTOs instead of aggregates;
- create read models for dashboard and UI composition.

## Forbidden

- create aggregate per table automatically;
- expose aggregates directly through API responses;
- put infrastructure concerns inside aggregates;
- create giant learner aggregate with all product data;
- use aggregates for purely visual cards.

## Definition of Done

An aggregate is ready when:

- it has clear ownership;
- it protects explicit invariants;
- it belongs to one bounded context;
- it does not leak persistence concerns;
- it exposes behavior, not only getters/setters.
