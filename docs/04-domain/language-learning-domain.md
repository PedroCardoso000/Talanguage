# Language Learning Domain — Talanguage

## Purpose

This document defines the core domain language of Talanguage.

The goal is to prevent the backend from becoming a set of disconnected CRUD endpoints and to prevent the frontend from inventing business rules that belong in the application/domain layers.

## Domain Vision

Talanguage is a practice-oriented language-learning system.

The domain is not centered on courses or passive content. It is centered on learning routines, practice sessions, feedback, revision, goals, and progress.

## Core Learning Loop

The product must support this loop:

1. user chooses or receives a practice activity;
2. user practices speaking, writing, or review;
3. system captures practice result;
4. system provides feedback or summary;
5. user reviews weak points;
6. progress is updated;
7. next action becomes clearer.

## Initial Languages

The first version supports:

- English;
- Spanish.

Languages must be treated as domain values, not arbitrary strings scattered through the code.

## Core Capabilities

### Authentication

Allows a learner to access a private learning experience.

### Dashboard

Presents the learner's current routine, next actions, and progress summary.

### Speaking Practice

Allows the learner to practice conversation through a structured or AI-assisted session.

### Writing Review

Allows the learner to respond to writing challenges and receive feedback.

### Flashcards

Allows the learner to review words, expressions, and phrases.

### Community

Allows the learner to discover practice groups, partners, or spaces.

### Progress

Tracks learning activity, completion, consistency, and skill development.

## Core Domain Concepts

- Learner;
- Target Language;
- Proficiency Level;
- Practice Activity;
- Speaking Session;
- Writing Submission;
- Feedback;
- Flashcard;
- Review Session;
- Daily Goal;
- Learning Streak;
- Skill Progress;
- Community Group;
- Practice Partner.

## Domain Boundaries

The domain must distinguish:

- learning behavior;
- user authentication;
- AI assistance;
- community interaction;
- progress tracking;
- billing/subscription, when introduced later.

Do not mix these concerns in one model.

## Business Direction

The domain must optimize for:

- consistency;
- practice completion;
- measurable progress;
- actionable feedback;
- simple daily routines;
- integration between modules.

## Existing Frontend Context

The current frontend is mocked. Many domain concepts may already appear visually as fake data.

When converting mock data into real behavior:

- identify the domain concept behind each mock;
- move rules out of the frontend;
- create explicit API contracts;
- preserve useful UI states;
- avoid exposing domain internals directly to the UI.

## Allowed

- create domain models for real learning concepts;
- use value objects for constrained values;
- create use cases for meaningful operations;
- keep UI models separate from domain models;
- start with simple rules and evolve them.

## Forbidden

- model the platform as a generic course catalog;
- make all entities anemic CRUD objects;
- place learning rules in frontend components;
- expose persistence models directly through APIs;
- treat language, level, status, or skill as random strings;
- create complex AI evaluation logic without explicit product decision.

## Definition of Done

A domain addition is ready when:

- it uses shared glossary language;
- it belongs to a clear bounded context;
- it has explicit rules;
- it does not leak infrastructure concerns;
- it supports the practice-feedback-review-progress loop.
