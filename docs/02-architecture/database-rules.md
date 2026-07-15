# Database Rules — Talanguage

## Purpose

This document defines persistence rules for Talanguage.

The backend has not been created yet, so database decisions must remain disciplined and aligned with the first version.

## Persistence Direction

The first version should use a relational database when real persistence is required.

Preferred default:

- PostgreSQL.

Temporary in-memory storage may be used only for early backend scaffolding or local development flows when explicitly documented.

## Database Ownership

The database is owned by the backend.

Frontend code must never depend on database models, table names or persistence details.

## Modeling Rules

Database models must support domain behavior but must not replace domain modeling.

Do not shape the domain only around tables.

Do not expose persistence models directly through API responses.

## Migration Rules

Every structural database change must be represented by a migration when real persistence exists.

Migrations must be:

- deterministic;
- reviewable;
- linked to a feature or architecture decision;
- safe to run in development environments.

## Repository Rules

Application/domain logic must depend on repository interfaces, not database clients.

Infrastructure implements repositories.

## Initial Data Areas

The first version may require persistence for:

- users;
- user preferences;
- learning languages;
- speaking sessions;
- writing submissions;
- flashcards;
- daily goals;
- progress snapshots;
- community profile data.

## Data That Can Start Mocked

The following may start mocked or static if explicitly documented:

- available speaking topics;
- example prompts;
- community groups;
- sample dashboard insights;
- suggested flashcards;
- simulated AI feedback.

## ID Rules

Use stable unique identifiers for persisted entities.

Avoid frontend-generated permanent IDs unless explicitly required for offline behavior.

## Audit Fields

Persisted records should include timestamps when useful:

- createdAt;
- updatedAt;
- deletedAt when soft delete is required.

Do not add audit complexity where it does not help the first version.

## Sensitive Data

Avoid storing unnecessary sensitive data.

Do not store raw AI prompts, user conversations or writing submissions without explicit product and privacy decision.

If stored, document:

- why it is stored;
- retention policy;
- access rules;
- deletion behavior.

## Soft Delete

Soft delete should be used only when recovery, audit or history matters.

Do not use soft delete by default for every table.

## Forbidden

Do not:

- let frontend depend on database schema;
- return database records directly as API responses;
- create tables before feature/domain need is clear;
- create NoSQL storage without justification;
- add caching before there is a performance need;
- store unnecessary personal or sensitive data;
- create migrations without linking to feature behavior.

## Definition of Done

A persistence change is done when:

- domain need is clear;
- schema supports the use case;
- migration exists when required;
- repository implementation is updated;
- tests cover relevant behavior;
- API response does not leak persistence model.
