# Persistence Baseline

## Scope

This note records the persistence decision applied during stabilization prompt `036`.

## Decision

The backend persistence baseline now targets only safe data for first-version storage:

- users
- sessions
- goals
- progress activities
- flashcards
- flashcard review stats
- community interests
- writing metrics
- speaking metrics

## Explicitly Not Persisted In First Version

Until there is an explicit product, privacy and LGPD decision, the application must not durably persist:

- raw writing text
- full speaking answers
- complete conversation transcripts
- user-authored prompts

## Writing Retention Rule

For writing, the backend may persist only aggregate metadata such as:

- userId
- challengeId
- language
- level
- score
- feedbackSummary
- improvement hints
- createdAt
- reviewedAt
- status

Recent submission responses may expose a redacted placeholder instead of original content.

## Speaking Retention Rule

For speaking, the backend may persist only aggregate metadata such as:

- userId
- language
- level
- topicId
- status
- score
- pronunciation
- fluency
- clarity
- feedbackSummary
- createdAt
- finishedAt
- duration
- aggregate counters needed for scoring continuity

The backend must not durably retain full user responses or transcript history in this phase.

## Operational Note

The persistence baseline now uses Spring Boot + JPA + Flyway + PostgreSQL configuration.

At audit time:

- backend build and tests passed
- Docker Compose was updated to include PostgreSQL
- direct runtime validation of the local profile still depends on a PostgreSQL instance or a working Docker daemon in the execution environment
