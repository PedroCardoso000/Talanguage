# AI Execution Contract — Talanguage

## Purpose

This document defines how AI agents must create, change, or review code and documentation in the Talanguage project.

The AI must follow the official project documentation and must not create patterns, libraries, flows, layers, or decisions outside the documented scope.

## Central Rule

The AI must execute tasks using only the necessary documentation for the current task, respecting product scope, architecture, domain rules, API contracts, design system, code standards, tests, and definition of done.

## General Execution Order

For any task, the AI must:

1. identify the task type;
2. consult the required reading order;
3. read only the required documents;
4. validate product scope;
5. validate architectural constraints;
6. implement the requested change;
7. create or update tests when applicable;
8. validate layer dependencies;
9. validate frontend-backend contracts when applicable;
10. deliver an objective summary.

## Required Behavior

The AI must:

- follow the authorized stack;
- respect the separation between domain, application, infrastructure, API, and frontend;
- keep business rules out of controllers;
- keep business rules out of frontend components;
- create use cases for relevant application operations;
- create DTOs for API input and output;
- use explicit contracts between frontend and backend;
- create tests for relevant domain and application behavior;
- avoid premature abstractions;
- prefer simple and reversible solutions;
- update documentation when a documented decision changes;
- request explicit approval for architectural, security, data, cost, or scope decisions not covered by documentation.

## Prohibited Behavior

The AI must not:

- add dependencies without authorization;
- change the stack without authorization;
- create features outside the specs;
- create routes outside the route matrix;
- create endpoints outside the API spec;
- create entities without domain specification;
- implement real AI integration without explicit decision;
- implement microservices;
- implement distributed messaging;
- implement WebRTC;
- create enterprise architecture without actual need;
- duplicate existing UI components;
- hide mocks inside components;
- mix infrastructure with domain;
- create business rules in the frontend;
- create business rules in HTTP controllers.

## Documentation Gap Rule

If a decision is not documented, the AI must choose the simplest, most local, reversible solution aligned with the first version.

If the gap affects architecture, security, data privacy, cost, product scope, authentication, payment, AI usage, or persistence strategy, the AI must stop and request an explicit decision.

## Feature Execution Rule

For a full-stack feature, the AI must implement in this order:

1. validate feature scope;
2. identify domain concepts;
3. create or update domain models;
4. create application use cases;
5. define or update API contracts;
6. create DTOs;
7. implement infrastructure adapters;
8. expose endpoints;
9. create backend tests;
10. create frontend API client;
11. create frontend page and components;
12. integrate frontend with backend;
13. create frontend tests when applicable;
14. validate final definition of done.

## Delivery Summary Rule

At the end of a task, the AI must report:

- files created;
- files changed;
- tests created or updated;
- assumptions made;
- pending decisions;
- validations performed.

## Tone of Implementation

The AI must optimize for clarity, maintainability, and low ambiguity. Clever code is not preferred. Predictable code is preferred.
