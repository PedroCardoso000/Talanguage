# Testing Strategy — Talanguage

## Purpose

This document defines the testing strategy for Talanguage.

Testing must support fast feature implementation by IA without creating a fragile or over-engineered test suite.

## Testing Principle

Test behavior that protects product value, architecture and integration.

Do not test implementation details that make refactoring painful.

## Current State

The frontend currently has mocked UI flows and no backend.

As the backend is created and mocks are replaced, tests must move from purely visual assumptions to behavior and integration coverage.

## Test Levels

### Domain Tests

Use for:

- entities;
- value objects;
- business rules;
- state transitions;
- domain invariants.

Examples:

- a finished speaking session cannot return to in-progress;
- a flashcard review updates review state;
- a daily goal cannot have invalid target values.

### Application / Use Case Tests

Use for:

- use case orchestration;
- validation;
- ownership checks;
- repository interaction through interfaces;
- success and failure paths.

These are the most important backend tests for the first version.

### API Tests

Use for:

- endpoint status codes;
- request validation;
- response shape;
- authentication requirement;
- error mapping.

### Frontend Component Tests

Use for:

- important interaction flows;
- form behavior;
- conditional rendering;
- loading/error/empty states;
- integration with mocked API clients.

### End-to-End Tests

Use sparingly.

Only add E2E tests for critical flows such as:

- register/login;
- starting a speaking practice;
- submitting writing practice;
- reviewing flashcards;
- viewing progress.

Do not create broad E2E coverage too early.

## Test Priority

Priority order:

1. use case tests;
2. domain rule tests;
3. API contract tests;
4. frontend behavior tests;
5. minimal E2E tests for critical flows.

## Mocking in Tests

Mocks are allowed when isolating behavior.

Repository interfaces can be mocked for use case tests.

External providers must be mocked in tests unless the test is explicitly an integration test.

Do not mock the unit being tested.

## Frontend Mock Rules

Frontend tests should mock API clients, not internal implementation details.

When a screen still uses mock data because backend is missing, tests should document the current behavior but avoid locking the exact mock content unless necessary.

## Backend Test Rules

Backend tests must cover:

- happy path;
- validation failure;
- not found;
- unauthorized when applicable;
- forbidden/resource ownership when applicable;
- invalid state transitions.

## Test Naming

Test names should describe behavior.

Good:

```txt
Should finish speaking session when session belongs to authenticated user
```

Bad:

```txt
Should call repository save
```

## CI Expectation

When CI exists, it should run:

- lint/type checks;
- unit tests;
- API tests when available;
- frontend tests when available;
- build verification.

## Forbidden

Do not:

- write tests only to increase coverage numbers;
- test private implementation details unnecessarily;
- skip use case tests for core behavior;
- rely only on frontend tests for business rules;
- create expensive E2E suites before the product stabilizes;
- leave failing tests ignored without explicit reason.

## Definition of Done

A feature is test-ready when:

- relevant use cases have tests;
- domain rules have tests when they exist;
- API contract behavior is covered;
- frontend critical interactions are covered when applicable;
- tests can run locally without external paid services.
