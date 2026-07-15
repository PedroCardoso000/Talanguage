# First Version Backlog — Talanguage

## Purpose

Define the execution backlog for the first version of Talanguage.

This is not a product wishlist. It is a controlled implementation sequence for an AI-assisted full-stack build.

## Current Context

The frontend exists with structured UI and mocked data.

The backend does not exist yet.

The first version must progressively connect the existing UI to real backend features.

## Priority Order

### P0 — Foundation

- Confirm stack and repository structure.
- Confirm frontend folder structure.
- Create backend base structure.
- Define API conventions.
- Define auth/security baseline.
- Define testing baseline.
- Define observability baseline.

### P1 — Auth

Goal: enable user identity and protected areas.

Deliverables:

- register;
- login;
- authenticated session;
- protected routes;
- user profile basics;
- backend auth contracts;
- frontend integration.

### P2 — Dashboard

Goal: replace dashboard mocks with real aggregated data where possible.

Deliverables:

- daily activity summary;
- progress summary;
- recommended next action;
- practice shortcuts;
- empty state for new users.

### P3 — Speaking

Goal: implement speaking practice session lifecycle.

Deliverables:

- list topics;
- start session;
- simulate or prepare conversation flow;
- finish session;
- store session summary;
- integrate existing speaking UI.

### P4 — Writing Review

Goal: implement writing practice flow.

Deliverables:

- list writing prompts;
- submit answer;
- generate mock or provider-backed feedback;
- store review result;
- integrate existing writing UI.

### P5 — Flashcards

Goal: implement vocabulary and review basics.

Deliverables:

- create card;
- list cards;
- review card;
- mark difficulty;
- connect to practice feedback later.

### P6 — Progress

Goal: derive visible learning progress from activities.

Deliverables:

- streak;
- activity history;
- skill breakdown;
- daily goal completion;
- progress dashboard.

### P7 — Community

Goal: provide initial community surface without building a full social network.

Deliverables:

- list groups;
- list practice spaces;
- view partner suggestions;
- avoid real-time chat unless explicitly approved.

### P8 — Hardening

Goal: stabilize the first version.

Deliverables:

- test gaps;
- API consistency;
- auth edge cases;
- UI loading/error states;
- basic observability;
- documentation sync.

## Explicit Non-Priorities

The first version must not prioritize:

- marketplace of teachers;
- full real-time chat;
- WebRTC calls;
- advanced gamification;
- complex admin dashboard;
- microservices;
- AI provider integration without cost/security decision.

## Backlog Rule

A feature must not be implemented before its feature docs exist.

A feature must not be promoted to done while still depending on undocumented mock behavior.

## Done Criteria

The backlog is valid when each priority maps to:

- feature docs;
- implementation plan;
- required skills;
- route/API/component matrix;
- test expectations.
