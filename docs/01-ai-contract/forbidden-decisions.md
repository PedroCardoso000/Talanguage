# Forbidden Decisions — Talanguage

## Purpose

This document lists decisions that AI agents must not make without explicit approval.

The goal is to prevent architectural drift, scope creep, uncontrolled cost, and inconsistent implementation patterns.

## Product Decisions Forbidden Without Approval

The AI must not decide to add:

- paid plans;
- subscriptions;
- payment gateways;
- teacher marketplace;
- social network feed;
- direct messaging;
- real-time chat;
- audio calls;
- video calls;
- certification flows;
- school/enterprise admin panels;
- public user profiles;
- rankings or leaderboards;
- children-focused experience.

## AI Decisions Forbidden Without Approval

The AI must not decide to add:

- real LLM provider integration;
- OpenAI, Anthropic, Google, or other AI provider SDKs;
- prompt chains;
- autonomous agents;
- speech-to-text;
- text-to-speech;
- pronunciation scoring;
- voice processing;
- vector database;
- embeddings;
- RAG architecture;
- unlimited AI feedback generation.

## Architecture Decisions Forbidden Without Approval

The AI must not decide to add:

- microservices;
- event sourcing;
- distributed messaging;
- Kafka;
- RabbitMQ;
- CQRS as a default pattern;
- GraphQL;
- service mesh;
- API gateway assumptions;
- multi-tenant architecture;
- plugin architecture;
- monorepo tooling changes;
- new package manager;
- new build system.

## Frontend Decisions Forbidden Without Approval

The AI must not decide to add:

- Redux;
- MobX;
- a third-party component library;
- a new CSS framework;
- a new routing framework;
- heavy animation libraries;
- canvas-heavy UI;
- complex state machines;
- design system replacement;
- visual identity changes.

## Backend Decisions Forbidden Without Approval

The AI must not decide to add:

- a backend stack not listed in `allowed-stack.md`;
- an ORM without approval;
- a second database;
- background workers;
- scheduled jobs;
- message queues;
- distributed cache;
- file storage service;
- email provider integration;
- external authentication provider.

## Database Decisions Forbidden Without Approval

The AI must not decide to add:

- NoSQL as primary database;
- multiple databases;
- full-text search engine;
- vector database;
- read replicas;
- sharding;
- database triggers for business logic;
- persistence logic inside controllers or frontend.

## Security Decisions Forbidden Without Approval

The AI must not decide to:

- store passwords insecurely;
- bypass authentication;
- expose private user data;
- log sensitive data;
- disable validation;
- weaken CORS/security settings for convenience;
- create admin privileges without specification;
- implement insecure token storage.

## Rule

If a task seems to require one of these decisions, the AI must stop and request explicit approval before implementing.
