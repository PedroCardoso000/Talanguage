# Value Objects — Talanguage

## Purpose

This document defines value object candidates and controlled values for Talanguage.

Value objects reduce invalid states and prevent random strings from spreading across the codebase.

## Value Object Rules

Use value objects when a value:

- has validation rules;
- has business meaning;
- should be immutable;
- does not need identity;
- appears in multiple parts of the system.

## Initial Value Objects and Controlled Values

### EmailAddress

Represents a valid email address.

Rules:

- cannot be empty;
- must follow email format;
- should be normalized for comparison.

### DisplayName

Represents a learner display name.

Rules:

- cannot be empty;
- must have reasonable length;
- must be safe for display.

### Language

Represents supported target languages.

Initial allowed values:

- ENGLISH;
- SPANISH.

Rules:

- must not be arbitrary text;
- API values must be consistent;
- frontend labels can be localized separately.

### NativeLanguage

Represents the user's native or preferred interface/help language when needed.

This must not be confused with target learning language.

### ProficiencyLevel

Represents learning level.

Initial allowed values:

- BEGINNER;
- INTERMEDIATE;
- ADVANCED.

Alternative CEFR mapping may be added later through explicit decision.

### SkillType

Represents learning skill.

Allowed values:

- SPEAKING;
- WRITING;
- READING;
- LISTENING;
- VOCABULARY;
- GRAMMAR.

First version may focus mainly on speaking, writing, vocabulary, and progress summaries.

### PracticeStatus

Represents status of a practice session or activity.

Allowed values:

- NOT_STARTED;
- IN_PROGRESS;
- COMPLETED;
- CANCELLED;
- FAILED.

### FeedbackScore

Represents an approximate score for feedback when scoring exists.

Rules:

- must define range explicitly;
- must not imply certification;
- may be optional;
- should be presented as approximate unless validated by product rules.

Recommended range:

- 0 to 100.

### TopicCategory

Represents topic grouping.

Possible values:

- TRAVEL;
- WORK;
- DAILY_LIFE;
- INTERVIEWS;
- STUDY;
- SOCIAL;
- CUSTOM, if explicitly supported.

### FlashcardType

Represents the type of card.

Allowed values:

- WORD;
- PHRASE;
- EXPRESSION;
- SENTENCE.

### KnowledgeStatus

Represents learner confidence with a flashcard.

Allowed values:

- NEW;
- LEARNING;
- REVIEWING;
- KNOWN;
- DIFFICULT.

### ReviewResultType

Represents review outcome.

Allowed values:

- AGAIN;
- HARD;
- GOOD;
- EASY.

This does not require advanced spaced repetition in the first version.

### GoalMetric

Represents what a goal measures.

Allowed values:

- PRACTICE_COUNT;
- MINUTES_PRACTICED;
- CARDS_REVIEWED;
- WRITING_SUBMISSIONS;
- SPEAKING_SESSIONS.

### DateRange

Represents a period used in progress summaries.

Rules:

- start date must be before or equal to end date;
- timezone handling must be explicit at application/API boundaries.

## API Representation Rules

- Use stable uppercase enum values in API contracts unless a different convention is explicitly chosen.
- Do not expose localized labels as domain values.
- Frontend should map domain values to display labels.

## Existing Frontend Context

Mocked frontend may already use friendly labels such as “Beginner” or “English”.

When integrating backend:

- API may use `BEGINNER` and `ENGLISH`;
- UI may display “Beginner” and “English”;
- mapping must be explicit and centralized.

## Allowed

- create value objects for constrained values;
- centralize mappings for UI labels;
- validate input at API/application boundaries;
- keep value objects immutable.

## Forbidden

- use arbitrary strings for language, level, status, skill, or card type;
- duplicate enum values across frontend and backend without contract;
- localize domain values directly in persistence;
- allow unsupported languages silently.

## Definition of Done

A value object is ready when:

- its allowed values or validation rules are explicit;
- it has no identity;
- it prevents invalid states;
- API representation is clear;
- frontend display mapping is separated from domain value.
