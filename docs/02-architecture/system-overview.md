# System Overview — Talanguage

## Purpose

This document defines the high-level technical architecture of Talanguage.

Talanguage is a full-stack language learning platform focused on practical learning routines, including speaking practice, writing review, flashcards, community practice, goals and progress tracking.

## Current Project State

The project already has a frontend structure with mocked data and an initial UI.

The current frontend is considered a visual and interaction foundation, not a complete functional product.

At this stage:

- the frontend exists;
- the UI is partially structured;
- data is mocked;
- user flows may not be fully functional;
- no production backend exists yet;
- API contracts still need to be defined;
- mock data must progressively be replaced by backend integrations.

## Target Architecture

The target architecture is a modular full-stack application composed of:

- frontend web application;
- backend API;
- application layer with use cases;
- domain layer with business rules;
- infrastructure layer for persistence, integrations and external services;
- API contracts shared conceptually between frontend and backend;
- automated tests for relevant flows.

## Architectural Direction

The system must evolve from a mocked frontend into a functional full-stack product without rewriting the frontend unnecessarily.

The preferred direction is incremental integration:

1. preserve existing frontend structure when reasonable;
2. identify mocked flows;
3. define API contracts for each feature;
4. create backend use cases and endpoints;
5. replace local mocks with API clients;
6. validate behavior with tests;
7. remove obsolete mocks only after integration is stable.

## Main Applications

The repository may contain two main applications:

```txt
apps/
├── web/
└── api/
```

### Web

The web application is responsible for:

- rendering the product UI;
- handling user interaction;
- calling backend APIs;
- managing client-side state;
- presenting loading, empty, success and error states;
- preserving design consistency.

### API

The API application is responsible for:

- exposing HTTP endpoints;
- validating input contracts;
- executing application use cases;
- enforcing authorization boundaries;
- coordinating persistence;
- returning stable response contracts.

## Core Product Modules

The first version of Talanguage is organized around these modules:

1. Auth;
2. Dashboard;
3. Speaking Practice;
4. Writing Review;
5. Flashcards;
6. Community;
7. Progress.

Each module must be treated as a vertical feature that may include:

- frontend screen;
- UI components;
- API client;
- backend endpoint;
- application use case;
- domain rules;
- persistence model;
- tests.

## Layer Responsibilities

### Frontend

Responsible for experience, presentation and interaction.

The frontend must not own business rules that belong to the domain or backend.

### API Layer

Responsible for HTTP adaptation.

Controllers or route handlers must not contain business rules.

### Application Layer

Responsible for use cases and orchestration.

Use cases coordinate domain objects, repositories and external services.

### Domain Layer

Responsible for core business concepts and rules.

Domain code must not depend on infrastructure, HTTP, databases or frontend concerns.

### Infrastructure Layer

Responsible for technical implementations:

- database access;
- repositories;
- authentication provider integration;
- email provider integration;
- AI provider integration;
- logging and telemetry adapters.

## Integration Principle

Frontend and backend must communicate through explicit API contracts.

The frontend must not depend on backend internal models.

The backend must not shape domain entities around frontend component needs.

## Mocking Strategy

Mocks are allowed while a feature is not integrated with the backend.

Mocks must be:

- explicit;
- isolated;
- easy to remove;
- not hidden inside UI components;
- located in a predictable mock/data layer.

Mocked flows must be tracked so they can be replaced by real API calls.

## Allowed Evolution

The system may evolve gradually.

A feature can move through these stages:

1. UI mocked;
2. API contract defined;
3. backend use case created;
4. endpoint exposed;
5. frontend API client created;
6. mock replaced by API call;
7. tests added or updated;
8. obsolete mock removed.

## Forbidden Direction

The system must not evolve into:

- disconnected frontend and backend implementations;
- duplicated business rules in frontend and backend;
- backend shaped only as CRUD endpoints without use cases;
- frontend components with hidden mock data;
- uncontrolled API contracts;
- premature microservices;
- over-engineered enterprise architecture without current need.

## Definition of Architectural Readiness

A feature is architecturally ready when:

- its frontend behavior is documented;
- its backend behavior is documented;
- its API contract is documented;
- domain rules are clear;
- mocks are identified;
- integration path is defined;
- tests are planned.
