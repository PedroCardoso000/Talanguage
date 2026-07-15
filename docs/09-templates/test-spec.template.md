# Test Spec — {Feature Name}

## Purpose

Define required tests for this feature.

## Backend Unit Tests

Test:

- {use case / rule}
- {use case / rule}
- {use case / rule}

## Backend Integration/API Tests

Test:

- `{METHOD} {path}` returns expected success status;
- validation errors return 400;
- unauthorized access returns 401 when applicable;
- forbidden access returns 403 when applicable;
- missing resource returns 404 when applicable;
- business conflict returns 409 when applicable.

## Frontend Tests

Test:

- page renders;
- main action works;
- loading state appears;
- empty state appears when applicable;
- error state appears when applicable;
- API client is called with correct payload;
- user sees expected result.

## Test Data

Required fixtures:

- {fixture}

## Mocking Rules

Allowed:

- mock API client in frontend tests;
- in-memory repository in backend unit tests;
- test database or test container in integration tests when needed.

Prohibited:

- test only implementation details;
- skip use case tests for business logic;
- depend on frontend mock data as backend truth.

## Done Criteria

Testing is sufficient when:

- relevant domain/use case rules are covered;
- API success and failure paths are covered;
- core frontend interaction is covered;
- tests are deterministic;
- tests do not require external providers unless explicitly configured.
