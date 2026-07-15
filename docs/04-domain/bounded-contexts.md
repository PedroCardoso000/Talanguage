# Bounded Contexts — Talanguage

## Purpose

This document defines the initial bounded contexts for Talanguage.

Bounded contexts prevent the system from becoming a single ambiguous domain model where authentication, practice, feedback, progress, and community are mixed together.

## Initial Contexts

### Identity Context

Responsible for authentication and learner access.

Main concepts:

- User Account;
- Learner Profile;
- Auth Session;
- Credentials;
- Role, if needed later.

Not responsible for:

- learning progress;
- practice sessions;
- writing feedback;
- flashcard review.

### Learning Practice Context

Responsible for practice activities and sessions.

Main concepts:

- Practice Activity;
- Speaking Session;
- Writing Challenge;
- Writing Submission;
- Practice Result;
- Practice Feedback.

Not responsible for:

- authentication internals;
- billing;
- community moderation;
- long-term analytics storage details.

### Review Context

Responsible for flashcards and revision behavior.

Main concepts:

- Flashcard;
- Review Session;
- Review Result;
- Knowledge Status;
- Saved Expression.

Not responsible for:

- full writing correction;
- speaking session orchestration;
- community interactions.

### Progress Context

Responsible for learning metrics, goals, streaks, and skill progress.

Main concepts:

- Daily Goal;
- Goal Completion;
- Learning Streak;
- Skill Progress;
- Progress Snapshot;
- Activity Summary.

Not responsible for:

- executing practice sessions;
- generating AI feedback;
- storing raw conversation messages unless needed for summaries.

### Community Context

Responsible for practice groups and partner discovery.

Main concepts:

- Community Group;
- Practice Partner;
- Group Membership;
- Community Space.

Not responsible for:

- real-time chat unless explicitly approved;
- audio/video calls;
- moderation tools in the first version.

### AI Assistance Context

Responsible for AI-assisted prompts, feedback, suggestions, and correction flows when real AI integration is approved.

Main concepts:

- AI Prompt Request;
- AI Feedback Result;
- AI Usage Record;
- Prompt Template;
- Cost Guardrail.

Not responsible for:

- user authentication;
- direct UI rendering;
- unrestricted model usage;
- billing logic unless integrated later.

## Context Relationships

### Identity → Other Contexts

Other contexts reference the learner through a stable learner/user identifier.

They must not depend on authentication internals.

### Learning Practice → Progress

Completed practice activities produce progress updates.

### Review → Progress

Completed review sessions produce progress updates.

### Learning Practice → AI Assistance

Practice contexts may request AI assistance for feedback or prompts, but AI logic must remain isolated.

### Community → Learning Practice

Community may help users discover practice opportunities, but practice execution remains in the Learning Practice context.

## Integration Rules

- Contexts communicate through application services, APIs, events, or explicit interfaces.
- Do not share persistence models across contexts.
- Do not create one giant `User` object with all learning data attached.
- Do not make frontend components infer context boundaries.

## Existing Frontend Context

Mocked UI sections may visually combine data from multiple contexts, especially dashboard and progress.

When implementing backend integration:

- dashboard may compose summaries from multiple contexts;
- frontend must consume composed read models, not raw domain aggregates;
- backend must own cross-context composition.

## Allowed

- start with modular monolith boundaries;
- keep contexts in one backend application initially;
- create clear folders/modules by context;
- expose read models for UI needs.

## Forbidden

- create microservices for each context in the first version;
- mix all models in a shared generic folder;
- share database entities directly across contexts;
- let frontend assemble critical business rules from many raw endpoints.

## Definition of Done

A context is correctly used when:

- its responsibility is clear;
- its models use local language;
- it does not depend on another context's internals;
- cross-context data is composed through explicit contracts;
- it supports the first version without premature distribution.
