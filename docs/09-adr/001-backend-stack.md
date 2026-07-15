# ADR — Freeze Backend Stack for Production Version

## Status

Accepted

## Date

2026-07-09

## Context

Talanguage started with a mocked frontend and no stable production backend decision. During the first production-readiness pass, part of the documentation incorrectly froze the backend as NestJS/Prisma even though the project context, prompt pack correction and repository direction point to Spring Boot.

That created avoidable governance drift:

- the repository already contains `apps/api` prepared for a Java/Spring Boot direction;
- the corrected production prompt package explicitly states Spring Boot as the official backend;
- `allowed-stack.md` and other documents must describe a single authoritative backend direction;
- keeping NestJS and Spring Boot both "official" would guarantee contradictory implementation.

Continuing with the wrong frozen stack would create duplicated backend work, conflicting test strategies, inconsistent prompt execution and fake optionality where none should exist.

The Spring Boot correction prompt explicitly requires freezing the first-version backend stack as:

- Java;
- Spring Boot;
- Spring Data JPA;
- PostgreSQL;
- REST API;
- JUnit 5 / Mockito / MockMvc;
- Testcontainers when needed;
- Flyway when real migrations are introduced.

This ADR formalizes that correction and closes the ambiguity.

## Decision

Talanguage will use the following official backend stack for the first production version:

- Java as the backend language;
- Spring Boot as the backend application framework;
- REST as the API style;
- Spring Data JPA as the default persistence approach;
- PostgreSQL as the primary production database;
- Flyway for schema migrations when real persistence is activated;
- JUnit 5, Mockito and MockMvc as the backend testing baseline;
- Testcontainers when integration tests need real infrastructure.

Additional decision details:

- the backend architecture remains a modular monolith;
- domain, application, infrastructure, API and contracts must remain logically separated;
- existing backend work in `apps/api` is the correct baseline to review, repair and extend;
- future production prompts must build on Spring Boot rather than introducing a parallel Node/NestJS backend.

## Alternatives Considered

### Option 1 — Freeze Java + Spring Boot + PostgreSQL

Pros:

- aligns with the corrected production prompt package;
- matches the existing repository direction in `apps/api`;
- is mature for modular monolith boundaries and conventional REST APIs;
- keeps backend governance coherent with the stack the user asked to preserve.

Cons:

- keeps frontend and backend in different languages;
- requires disciplined contracts between TypeScript frontend and Java backend;
- does not provide the superficial convenience of a single-language stack.

### Option 2 — Freeze Node.js + NestJS + Prisma + PostgreSQL

Pros:

- shared language with the frontend;
- broad ecosystem familiarity for TypeScript teams.

Cons:

- contradicts the corrected production track;
- discards or sidelines the existing backend baseline in `apps/api`;
- would force a parallel backend implementation with no product benefit at this stage;
- would keep the project in documentation conflict unless the whole prompt track changed again.

### Option 3 — Keep Backend Undefined Longer

Pros:

- avoids immediate migration choice;
- preserves flexibility.

Cons:

- guarantees drift, duplicated effort and contradictory implementation;
- blocks production-grade backend prompts from moving cleanly;
- keeps architecture governance weak exactly when it needs to become strict.

## Consequences

Positive:

- backend implementation direction is now explicit;
- future prompts can continue from the corrected Spring Boot track without reopening stack debate;
- PostgreSQL remains the clear production database;
- testing direction is now aligned with JUnit 5, Mockito, MockMvc and optional Testcontainers;
- `apps/api` can be evolved instead of replaced.

Negative:

- previous NestJS/Prisma governance text becomes invalid and must not guide implementation;
- any stale prompt or plan that still names NestJS as official backend must be treated as documentation debt;
- the repository still needs verification to confirm how much usable Spring Boot code remains after earlier reversions.

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

This decision can be reversed only by creating a new ADR that:

- explains why Spring Boot no longer fits;
- evaluates migration cost honestly;
- names the replacement stack explicitly;
- defines what happens to Spring Data JPA, PostgreSQL assumptions and existing backend modules;
- updates `docs/01-ai-contract/allowed-stack.md` and all affected production prompts.

Without a superseding ADR, this decision remains authoritative.

## Done Criteria

This ADR is complete when:

- the context is clear;
- the decision is explicit;
- alternatives were considered;
- consequences are honest;
- impacted areas are listed;
- reversal strategy exists.
