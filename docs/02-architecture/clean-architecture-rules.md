# Clean Architecture Rules — Talanguage

## Purpose

This document defines the dependency and responsibility rules for backend and full-stack feature implementation.

Clean Architecture in Talanguage must be practical, not ceremonial.

The goal is to prevent business rules from being scattered across controllers, frontend components, database code or provider integrations.

## Dependency Rule

Dependencies must point inward.

Preferred direction:

```txt
API → Application → Domain
Infrastructure → Application/Domain contracts
Frontend → API contracts
```

The domain must not depend on:

- API;
- frontend;
- database;
- infrastructure;
- external providers;
- framework-specific code.

## Layer Rules

### Domain Layer

Allowed:

- entities;
- value objects;
- aggregates;
- domain services when justified;
- domain events;
- business invariants.

Forbidden:

- HTTP types;
- database clients;
- frontend types;
- API DTOs;
- framework decorators when avoidable;
- external service calls.

### Application Layer

Allowed:

- use cases;
- input/output models;
- repository interfaces;
- application services;
- authorization checks related to use case execution;
- orchestration.

Forbidden:

- direct database access;
- direct HTTP controller logic;
- UI logic;
- provider-specific implementation details.

### Infrastructure Layer

Allowed:

- repository implementations;
- database mappings;
- external service clients;
- AI provider adapters;
- authentication provider adapters;
- logging and telemetry adapters.

Forbidden:

- owning business rules;
- changing domain behavior silently;
- exposing persistence models as domain models.

### API Layer

Allowed:

- routes/controllers;
- request validation;
- response mapping;
- status code handling;
- authentication middleware;
- calling use cases.

Forbidden:

- business rules;
- direct database logic;
- complex orchestration;
- calling external providers directly.

### Frontend Layer

Allowed:

- UI state;
- interaction flow;
- form state;
- API client calls;
- rendering domain results.

Forbidden:

- source of truth for business rules;
- scoring logic that belongs to backend/domain;
- authorization enforcement as the only protection;
- persistence decisions.

## Use Case Standard

Each use case must have:

- clear name;
- input contract;
- output contract;
- explicit dependencies;
- validation path;
- error path;
- tests when behavior is relevant.

Example:

```txt
StartSpeakingSessionUseCase
├── Input
├── Output
├── Handler/Executor
└── Tests
```

## Error Handling

Use cases should return or throw application-level errors that can be mapped by the API layer.

The API layer is responsible for mapping errors to HTTP status codes.

Domain errors must be meaningful and not tied to HTTP.

## Mapping Rules

Mapping is required between:

- request DTO and use case input;
- domain entity and response DTO;
- persistence model and domain entity.

Do not expose domain entities directly through the API.

Do not use database models directly in frontend contracts.

## When Not to Over-Engineer

Do not create aggregates, domain events or domain services for simple flows unless there is a real business rule.

CRUD-like features can still have use cases, but they should remain simple.

The architecture should protect the product, not slow it down.

## Forbidden Shortcuts

The AI must not:

- create controller-to-database flows;
- put validation only in the frontend;
- put business rules in React components;
- return ORM entities directly;
- bypass use cases for feature behavior;
- create abstractions before at least one concrete need exists.

## Validation Checklist

Before considering an implementation complete, verify:

- Does the controller only adapt HTTP?
- Does the use case own orchestration?
- Does the domain own business rules?
- Does infrastructure implement technical details?
- Does the frontend use API contracts instead of backend internals?
- Are mocks isolated and replaceable?
