# Business Rules — Talanguage

## Purpose

This document defines initial business rules for Talanguage.

Business rules must guide future backend implementation and prevent the frontend from becoming the place where learning logic is invented.

## General Rules

### Supported Languages

The first version supports:

- English;
- Spanish.

No other target language may be added without product decision and documentation update.

### Learning Is Practice-Oriented

Every core feature should support at least one of these actions:

- practice;
- feedback;
- review;
- progress tracking;
- community discovery.

Features that do not support the learning loop must be challenged.

### User Ownership

Learner data must belong to a learner/user.

This includes:

- sessions;
- submissions;
- flashcards;
- review results;
- progress;
- goals.

A learner must not access another learner's private learning data.

## Speaking Rules

- A speaking session must have a learner.
- A speaking session must have a target language.
- A speaking session must have a proficiency level.
- A speaking session must have a topic.
- A speaking session must be started before it can be completed.
- A completed speaking session cannot return to in-progress.
- Audio, transcription, pronunciation scoring, and WebRTC are not part of the default first version unless explicitly approved.
- Feedback may be simulated or AI-assisted, but the UI must not present it as certified evaluation.

Text-based speaking history may retain learner messages, system or AI replies, feedback and metrics so the learner can revisit the session. Persisted speaking content is private and access must be restricted to its learner owner.

## Writing Rules

- A writing challenge must have a language and level.
- A writing submission must not be empty.
- A writing submission must belong to one learner.
- Feedback must reference the exact submitted answer.
- If the learner revises an answer, the revision must be treated explicitly, not silently overwrite evaluated content.
- Feedback must be constructive and should include summary and improvement points.

Writing history may retain the submitted text, corrected response, feedback, score and activity date so the learner can revisit previous work. Persisted writing content is private and access must be restricted to its learner owner.

## Flashcard Rules

- A flashcard must belong to one learner.
- A flashcard must have front and back content.
- A flashcard must have a language.
- A flashcard must have a type: word, phrase, expression, or sentence.
- Knowledge status must use controlled values.
- Advanced spaced repetition is not required unless documented later.

## Review Rules

- A review session must include at least one flashcard.
- Review results must reference cards in the session.
- A finished review session cannot accept new review results.
- Review outcomes may update knowledge status.

## Goal Rules

- A daily goal must belong to one learner.
- A daily goal must have a metric and target value.
- Target value must be positive.
- Completion must be derived from actual activity records, not only frontend state.
- A goal should not be marked complete twice for the same day.

## Progress Rules

- Progress must be based on recorded activity.
- Progress summaries may be approximate but must not fabricate learning results.
- Dashboard progress should be simple and actionable.
- Progress by skill should use controlled skill types.
- Streak calculation must be centralized in backend/application logic.

## Community Rules

- Community groups must have a language or clear purpose.
- Practice partner data shown to users must be safe and limited.
- Real-time chat, direct messaging, calls, and moderation tooling are not default first-version features.
- Community must support practice, not become a generic social feed.

## AI Assistance Rules

- AI integration must be explicitly approved before real implementation.
- AI usage must have cost control.
- AI outputs must be treated as assistance, not absolute truth.
- Feedback must avoid overclaiming precision.
- Prompts/templates must be centralized when real AI integration exists.

## Frontend Mock Transition Rules

Current mocked data must be replaced progressively.

When replacing a mock:

1. identify the domain concept;
2. define API contract;
3. implement backend use case;
4. connect frontend through API client;
5. preserve loading/empty/error states;
6. remove local mock only after real flow works.

## Allowed

- keep first version rules simple;
- simulate advanced behavior when documented;
- centralize business decisions in backend/application layers;
- expose UI-friendly read models;
- evolve rules through specs and ADRs.

## Forbidden

- calculate critical progress only in frontend;
- mark activity complete without backend record;
- use arbitrary strings for controlled domain values;
- allow one learner to access another learner's private data;
- implement real AI, audio, calls, or chat without explicit approval;
- overpromise fluency outcomes.

## Definition of Done

A business rule is implemented correctly when:

- it is enforced in the backend/application/domain layer;
- it is covered by relevant tests;
- frontend uses the result instead of duplicating the rule;
- API contract represents the rule clearly;
- user-facing copy does not exaggerate what the rule provides.
