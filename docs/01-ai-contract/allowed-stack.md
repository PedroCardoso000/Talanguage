# Allowed Stack — Talanguage

## Purpose

This document defines the authorized technologies for Talanguage.

The AI must not add or replace technologies without explicit approval.

## Frontend Stack

Authorized:

- Next.js;
- React;
- TypeScript;
- Tailwind CSS;
- App Router;
- standard browser APIs;
- local mock data when explicitly allowed by specs.

Conditionally authorized:

- Zustand, when shared client state is needed;
- TanStack Query, only when real API integration is implemented and request caching/state control is needed;
- Zod, only when validation schemas or contract validation are required;
- Testing Library, for frontend tests;
- Vitest, for frontend unit tests.

Not authorized by default:

- Redux;
- MobX;
- Angular;
- Vue;
- React Router as primary router for this app;
- Material UI;
- Chakra UI;
- Ant Design;
- heavy animation libraries;
- unnecessary state management frameworks.

## Backend Stack

Official backend stack for the first production version:

- Java 17+;
- Spring Boot;
- REST API;
- Spring Data JPA;
- PostgreSQL;
- Flyway, when real schema migrations are introduced;
- JUnit 5;
- Mockito;
- MockMvc;
- Testcontainers, when integration coverage requires real infrastructure.

This is no longer an open decision.

The AI must treat this backend stack as frozen for the first production version unless an explicit new ADR replaces it.

## Backend Architecture

Authorized architectural style:

- modular monolith for the first version;
- clear separation between domain, application, infrastructure, API, and contracts;
- use cases for relevant operations;
- repositories as interfaces where persistence is needed;
- infrastructure adapters implementing application/domain ports.

Not authorized by default:

- microservices;
- distributed event-driven architecture;
- complex CQRS;
- event sourcing;
- service mesh assumptions;
- background workers unless explicitly required.

## Database

Authorized for the first production version:

- PostgreSQL as the primary relational database.

Conditionally authorized:

- in-memory repositories for early backend scaffolding or tests only when explicitly documented;
- SQLite only for isolated prototype or local tooling cases that do not redefine production persistence;
- Redis only for explicit cache/session use cases later.

Not authorized by default:

- MongoDB;
- DynamoDB;
- Cassandra;
- Elasticsearch as primary database;
- multiple primary databases in the first version.

## Authentication

Authorized direction:

- email/password authentication;
- token-based auth appropriate to the Spring Boot backend design;
- password hashing using established backend framework practices.

Not authorized by default:

- social login;
- SSO;
- multi-factor authentication;
- enterprise identity providers;
- biometric authentication.

## AI Integration

Real AI integration is not authorized by default.

Before implementation, the following must be documented:

- AI provider;
- prompt contract;
- cost limits;
- usage limits;
- privacy rules;
- failure handling;
- observability;
- abuse prevention.

Allowed for first version:

- mock AI feedback;
- stubbed AI response services;
- interface contracts prepared for future AI integration.

## Testing Stack

Frontend:

- Vitest;
- Testing Library.

Backend:

- JUnit 5;
- Mockito;
- MockMvc;
- Testcontainers when persistence or infrastructure integration needs real runtime coverage.

## Dependency Rule

A new dependency can only be added when:

1. it solves a real current problem;
2. the native stack does not solve it well;
3. it is compatible with the project architecture;
4. it does not introduce unnecessary complexity;
5. the decision is documented.
