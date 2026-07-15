# Definition of Done — Talanguage

## Purpose

This document defines when a task, component, API endpoint, backend use case, or full-stack feature can be considered complete.

The AI must use this definition before declaring work finished.

## General Definition of Done

A task is done only when:

- it follows the documented scope;
- it follows the authorized stack;
- it respects the project architecture;
- it does not introduce forbidden decisions;
- it keeps code readable and maintainable;
- it updates or creates tests when applicable;
- it does not leave hidden mocks or temporary hacks;
- it does not create unused abstractions;
- it integrates with existing structure;
- it includes a short final summary.

## Product Definition of Done

A product change is done only when:

- it supports the first-version scope;
- it does not add out-of-scope behavior;
- the user value is clear;
- the flow has a clear start and end;
- empty, loading, success, and error states are considered when applicable.

## Backend Definition of Done

A backend change is done only when:

- business rules are not placed in controllers;
- use cases contain application orchestration;
- domain rules stay in domain models or domain services when applicable;
- DTOs are separated from domain entities;
- repositories are accessed through interfaces when persistence is involved;
- validation and error handling are explicit;
- relevant unit tests exist;
- API behavior matches the documented contract.

## Frontend Definition of Done

A frontend change is done only when:

- the UI follows the design system;
- the page or component has a single clear responsibility;
- business rules are not implemented in visual components;
- API access is isolated in a client/service layer;
- loading, empty, success, and error states are handled when applicable;
- mock data is isolated outside components;
- the interface is responsive according to documented rules;
- relevant tests exist when the component or flow contains behavior.

## API Definition of Done

An API endpoint is done only when:

- request and response DTOs are explicit;
- status codes are defined;
- validation errors are handled consistently;
- authentication requirements are clear;
- the endpoint calls application use cases;
- the endpoint does not contain business logic;
- frontend integration follows the same contract;
- API tests cover relevant success and failure cases.

## Test Definition of Done

Testing is sufficient when:

- important domain rules are tested;
- use cases are tested;
- API success and relevant failure cases are tested;
- frontend behavior is tested when user interaction or state transitions exist;
- tests are meaningful and not only implementation snapshots;
- tests do not require external services unless integration testing explicitly requires them.

## Documentation Definition of Done

Documentation is done only when:

- it is prescriptive;
- it reduces ambiguity;
- it helps AI execution;
- it defines allowed and forbidden behavior where relevant;
- it avoids generic filler text;
- it stays aligned with product scope and architecture.

## Final Response Requirement

At the end of implementation, the AI must provide:

- summary of changes;
- files created;
- files changed;
- tests added or updated;
- assumptions made;
- pending decisions;
- validation result.
