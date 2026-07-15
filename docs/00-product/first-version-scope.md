# First Version Scope — Talanguage

## Objective

The first version of Talanguage must deliver a coherent integrated language-practice experience covering authentication, dashboard, speaking practice, writing review, flashcards, community, and progress tracking.

The goal is not to deliver the final product vision. The goal is to create a functional, extensible, and well-structured first version that proves the integrated learning loop.

## Functional Scope

### Authentication

Users must be able to:

- create an account;
- log in;
- access private application areas;
- log out.

### Dashboard

Users must be able to see:

- daily learning summary;
- current goals;
- progress overview;
- suggested next actions;
- shortcuts to core modules.

### Speaking Practice

Users must be able to:

- choose a target language;
- choose a proficiency level;
- choose a conversation topic;
- start a speaking practice session;
- view a simulated or assisted conversation flow;
- finish the session;
- receive a simple practice summary.

### Writing Review

Users must be able to:

- receive a writing challenge;
- write a short answer;
- submit the answer;
- receive feedback;
- review improvement points.

### Flashcards

Users must be able to:

- view saved words, phrases, and expressions;
- review cards;
- mark knowledge status;
- see basic review progress.

### Community

Users must be able to:

- view practice groups;
- view potential practice partners;
- see community spaces by language or topic.

The first version does not need to implement real-time chat or calls unless explicitly approved later.

### Progress

Users must be able to see:

- learning streak;
- completed practices;
- progress by skill;
- daily goal completion;
- simple performance indicators.

## Technical Scope

The first version must include:

- structured frontend;
- structured backend;
- authentication flow;
- explicit API contracts;
- initial domain model;
- core use cases;
- basic persistence strategy;
- relevant unit tests;
- API tests for core flows;
- frontend-backend integration;
- documentation for AI-assisted implementation;
- clear separation between domain, application, infrastructure, API, and frontend.

## AI Scope

The first version may contain simulated AI behavior or prepared integration points.

Real AI integration must only be implemented after explicit decisions about:

- provider;
- cost control;
- prompt strategy;
- safety rules;
- data privacy;
- rate limits;
- observability;
- fallback behavior.

## Data Scope

The first version may use:

- real persistence where necessary;
- in-memory or mock data for non-critical flows;
- explicit contracts that allow later replacement with real integrations.

Mocked behavior must be visible, isolated, and easy to replace.

## Quality Scope

The first version must prioritize:

- clear architecture;
- stable feature boundaries;
- consistent UI;
- simple flows;
- testable use cases;
- low ambiguity for AI-generated code.

## First Version Success Criteria

The first version is coherent when a user can:

1. create an account;
2. log in;
3. access the dashboard;
4. start a speaking practice;
5. complete a writing challenge;
6. review flashcards;
7. open the community area;
8. see progress indicators;
9. understand what to do next.

## Product Constraint

The first version must not try to implement the entire long-term vision. It must deliver a complete learning loop with a controlled scope.
