# Backend Architecture — Talanguage

## Purpose

This document defines the backend architecture for Talanguage.

The backend foundation in `apps/api` may already exist, but the production backend still needs to be implemented and aligned to the documented architecture.

## Current State

At this stage:

- the frontend exists with mocked data;
- backend is not production-ready yet;
- API contracts need to be defined;
- frontend mocks must guide initial backend capabilities, but must not dictate domain design blindly.

## Backend Goal

The backend must provide a stable application foundation for:

- authentication;
- user profile and preferences;
- speaking practice sessions;
- writing review flows;
- flashcards;
- community discovery;
- progress tracking;
- future AI integrations.

## Architectural Style

The backend should follow a modular layered architecture inspired by Clean Architecture.

The goal is not ceremony. The goal is separation of responsibility.

Recommended layers:

```txt
apps/api/src/
├── Api/
├── Application/
├── Domain/
├── Infrastructure/
└── Contracts/
```

In Spring Boot, package names and folders may differ, but the same responsibilities must remain explicit.

## Layer Responsibilities

### API Layer

Responsible for:

- HTTP endpoints;
- request parsing;
- response formatting;
- authentication middleware integration;
- calling application use cases.

The API layer must not contain business rules.

### Application Layer

Responsible for:

- use cases;
- orchestration;
- transaction boundaries;
- authorization checks related to application behavior;
- calling repositories and services through interfaces.

### Domain Layer

Responsible for:

- entities;
- value objects;
- aggregates;
- domain rules;
- domain events;
- business invariants.

The domain layer must not depend on frameworks, databases or HTTP.

### Infrastructure Layer

Responsible for:

- database repositories;
- external providers;
- AI provider adapters;
- email provider adapters;
- authentication provider adapters;
- observability implementation;
- persistence configuration.

### Contracts Layer

Responsible for shared request and response shapes used by API boundaries.

Contracts must not expose internal domain entities directly.

## Backend Module Structure

Each feature should have an organized backend implementation.

Example:

```txt
Application/
└── Speaking/
    ├── StartSpeakingSession/
    ├── FinishSpeakingSession/
    └── GetRecentSpeakingSessions/

Domain/
└── Speaking/
    ├── SpeakingSession
    ├── SpeakingTopic
    └── SpeakingFeedback

Infrastructure/
└── Persistence/
    └── SpeakingSessionRepository

Api/
└── Endpoints/
    └── SpeakingEndpoints
```

## Use Case Rules

A relevant user action must be represented by a use case.

Examples:

- RegisterUserUseCase;
- LoginUserUseCase;
- StartSpeakingSessionUseCase;
- FinishSpeakingSessionUseCase;
- SubmitWritingAnswerUseCase;
- ReviewFlashcardUseCase;
- UpdateDailyGoalUseCase.

Use cases must:

- receive explicit input;
- validate application-level constraints;
- coordinate domain objects;
- call repository interfaces;
- return explicit output;
- avoid HTTP-specific types.

## Repository Rules

Repositories must be interfaces from the perspective of application/domain logic.

Infrastructure implements repositories.

Application code must not depend directly on database clients.

## DTO and Contract Rules

DTOs are allowed at boundaries.

Domain entities must not be returned directly from API endpoints.

Request and response contracts must be documented in the feature `api-spec.md`.

## Mock Backend Strategy

Because the frontend currently uses mocks, the backend can start with in-memory repositories only when explicitly allowed by the feature plan.

In-memory implementations are temporary and must:

- implement the same repository interface expected by persistent implementations;
- not leak into domain logic;
- be easy to replace with database-backed repositories.

## AI Integration Boundary

Future AI integrations must be isolated behind application or infrastructure services.

The backend must not call AI providers directly from controllers.

AI integration requires explicit decisions for:

- provider;
- cost control;
- rate limits;
- prompt storage;
- data privacy;
- fallback behavior;
- observability.

## Forbidden

The backend must not:

- expose domain entities directly in API responses;
- put business rules inside controllers;
- put SQL or database logic inside use cases;
- couple frontend component needs directly to domain models;
- implement microservices in the first version;
- create messaging infrastructure without explicit need;
- create AI provider calls without documented decision;
- introduce generic abstractions without real use.

## Definition of Done

A backend feature is done when:

- domain concepts are modeled when needed;
- use cases are created for relevant actions;
- repository interfaces are explicit;
- infrastructure implementation exists or is intentionally mocked;
- endpoints call use cases;
- DTOs/contracts are explicit;
- tests cover main business behavior;
- API behavior matches the feature API spec.
