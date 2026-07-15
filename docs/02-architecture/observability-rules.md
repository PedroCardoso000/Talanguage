# Observability Rules — Talanguage

## Purpose

This document defines lightweight observability rules for Talanguage.

The first version does not need enterprise observability, but it must avoid being blind.

## Observability Principle

Start simple. Make important behavior visible. Do not add complex telemetry infrastructure before there is a real need.

## What Must Be Observable

The system should make these areas visible:

- API request failures;
- authentication failures;
- feature use case failures;
- validation errors;
- AI provider usage when implemented;
- slow or failed external integrations;
- unexpected backend exceptions.

## Logging Rules

Logs must be structured when possible.

Logs should include:

- timestamp;
- severity;
- request or correlation identifier when available;
- feature/module;
- operation/use case;
- error code or exception type;
- sanitized context.

## Sensitive Data Rules

Do not log:

- passwords;
- access tokens;
- private user writing submissions unless explicitly approved;
- full AI prompts with personal data;
- sensitive conversation content;
- raw provider credentials.

## Frontend Observability

The frontend should handle and surface errors clearly to users.

Frontend error tracking can be added later if needed.

For the first version, prioritize:

- clear error states;
- console noise reduction;
- predictable failed request handling;
- user-friendly messages.

## Backend Observability

The backend should log:

- unexpected errors;
- failed use cases when relevant;
- authentication failures with care;
- external provider failures;
- AI request failures when AI exists.

## Metrics

Metrics are not mandatory at the beginning, but future metrics may include:

- number of practice sessions started;
- number of writing submissions;
- flashcard reviews;
- active users;
- failed API requests;
- AI cost per feature;
- AI request count per user.

## Tracing

Distributed tracing is not required for the first version unless architecture becomes distributed.

Do not add tracing infrastructure before there is a real operational need.

## AI Cost Observability

When AI integration is implemented, track:

- provider;
- feature;
- request count;
- estimated token usage;
- cost estimate;
- failure rate;
- fallback usage.

AI usage without observability is forbidden for production-like flows.

## Health Checks

The backend should expose a simple health check when deployment requires it.

Example:

```txt
GET /health
```

The health check should not expose sensitive internal details.

## Forbidden

Do not:

- log secrets;
- log sensitive user learning content by default;
- add complex observability tools without need;
- ignore AI cost tracking when AI is introduced;
- expose internal system details in public health endpoints;
- let errors fail silently.

## Definition of Done

A production-relevant backend feature is observability-ready when:

- unexpected errors are logged;
- sensitive data is not logged;
- user-facing errors are understandable;
- external provider failures are visible;
- AI usage is measurable if AI is involved.
