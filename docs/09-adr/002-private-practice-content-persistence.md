# ADR — Persist Private Practice Content for Functional History

## Status

Accepted

## Date

2026-07-17

## Context

Talanguage needs a functional learning history in which a learner can revisit previous writing and text-based speaking practice. The previous persistence baseline retained only aggregate metrics and redacted writing content. That minimizes stored data, but it cannot support rereading, contextual corrections, feedback tied to the original answer, or visible evolution over time.

The documentation was contradictory: the persistence baseline prohibited durable storage of raw writing and speaking answers, while the Speaking stabilization prompt required sessions and messages to persist.

Practice content is private learner data. Persisting it creates explicit obligations for authorization, retention, deletion, logging, AI-provider sharing, security and future portability. Those policies must be decided before implementation.

## Decision

Talanguage will persist the content necessary to provide functional history for Write and text-based Speak.

Write history may store:

- the exact text submitted by the learner;
- a corrected or suggested response when one is produced;
- contextual feedback;
- the score produced by an approved, explainable evaluation policy;
- the activity timestamps and identifiers needed to reconstruct history.

Text-based Speak history may store:

- learner messages;
- system or AI-generated replies shown to the learner;
- session feedback;
- session metrics and timestamps.

All stored practice content:

- belongs to one learner;
- is private by default;
- may be read only by its owner and narrowly authorized backend processes;
- must never be exposed through another learner's history;
- must not be written to application, access, error or observability logs.

The previous `metrics-only` approach is no longer the target persistence policy for Write or text-based Speak. Existing code remains unchanged until the remaining policy decisions in this ADR are resolved.

Audio retention is not decided by this ADR yet. The product still needs to decide whether a future audio flow retains original audio, transcription, processed text only, or a combination.

## Required Follow-up Decisions Before Implementation

- retention period for raw and derived practice content;
- user-initiated deletion semantics;
- account-deletion behavior and deletion deadline;
- whether Talanguage may use practice content for model training;
- rules and consent for sharing content with external AI providers;
- encryption, access control and operational security measures;
- future export scope and format;
- audio and transcription retention policy.

## Alternatives Considered

### Option 1 — Persist only aggregate metrics

Pros:

- minimizes sensitive data;
- reduces privacy and deletion complexity;
- keeps the persistence model small.

Cons:

- prevents learners from rereading previous work;
- prevents feedback from remaining visibly tied to the original answer;
- cannot provide a credible functional history or evolution view.

### Option 2 — Persist private practice content required for history

Pros:

- supports rereading, contextual feedback and corrections;
- enables a real history instead of a metrics façade;
- creates a foundation for future export and longitudinal learning features.

Cons:

- stores sensitive learner-authored content;
- requires explicit retention, deletion, authorization and provider-sharing policies;
- increases security and compliance responsibility.

## Consequences

Positive:

- Write and Speak can offer functional, contextual history;
- feedback can remain linked to the exact content evaluated;
- the product can show evolution based on evidence rather than aggregate scores alone.

Negative:

- the current metric entities and redacted placeholders are insufficient;
- content lifecycle and privacy controls become implementation prerequisites;
- accidental logging, cross-user access and uncontrolled provider sharing become material risks.

## Impacted Areas

- Product
- Frontend
- Backend
- Database
- Security
- Testing
- Observability
- Documentation

## Reversal Strategy

Talanguage may return to metrics-only storage by creating a superseding ADR, stopping new content collection, deleting or exporting retained content according to the active lifecycle policy, and changing history contracts to make the loss of raw content explicit.

## Done Criteria

This ADR is implementation-ready only when every required follow-up decision is explicit in canonical documentation and the Write and Speak specs describe matching ownership, retention, deletion, security and API behavior.
