# API Contract Rules — Talanguage

## Purpose

This document defines how frontend and backend must communicate.

Because the frontend already exists with mocked data and the backend does not yet exist, API contracts are the bridge between the current UI and the future functional product.

## Contract Principle

Every integrated feature must have a documented API contract before backend and frontend integration.

The API contract must be defined in:

```txt
docs/05-features/{feature}/api-spec.md
```

## API Contract Must Define

Each endpoint must define:

- method;
- path;
- purpose;
- authentication requirement;
- request body;
- path parameters;
- query parameters;
- success response;
- error responses;
- validation rules;
- frontend usage notes when relevant.

## Endpoint Naming Rules

Use resource-oriented paths with explicit actions only when needed.

Examples:

```txt
POST /api/auth/register
POST /api/auth/login
GET /api/dashboard/summary
POST /api/speaking/sessions
POST /api/speaking/sessions/{sessionId}/finish
GET /api/flashcards/due
POST /api/flashcards/{flashcardId}/review
GET /api/progress/summary
```

## Request Rules

Requests must:

- be explicit;
- use predictable field names;
- avoid frontend-only fields;
- avoid sending derived values that backend can calculate;
- validate required values.

## Response Rules

Responses must:

- be stable;
- be typed;
- avoid exposing internal domain or database models;
- include only data needed by the client for the documented flow;
- avoid large payloads unless justified.

## Error Response Standard

Use a consistent error response shape.

Example:

```json
{
  "error": {
    "code": "VALIDATION_ERROR",
    "message": "Invalid request data.",
    "details": [
      {
        "field": "language",
        "message": "Language is required."
      }
    ]
  }
}
```

## HTTP Status Rules

Use standard HTTP status codes:

- `200 OK` for successful reads or updates;
- `201 Created` for successful creation;
- `204 No Content` for successful delete without body;
- `400 Bad Request` for validation errors;
- `401 Unauthorized` for unauthenticated access;
- `403 Forbidden` for authenticated but unauthorized access;
- `404 Not Found` for missing resources;
- `409 Conflict` for state conflicts;
- `500 Internal Server Error` for unexpected failures.

## Frontend Integration Rules

The frontend must call APIs through feature-specific API clients.

UI components must not call endpoints directly.

Mocked frontend data must be replaced by API clients only after the contract is documented.

## Contract Compatibility

When changing an existing API contract:

1. check frontend usage;
2. update api-spec;
3. update backend DTOs;
4. update frontend API client;
5. update tests;
6. remove obsolete mock assumptions.

## Versioning

Do not introduce API versioning until there is a real need.

The first version may use `/api/...` paths without `/v1` unless a decision is made otherwise.

## Forbidden

Do not:

- create undocumented endpoints;
- let frontend infer backend behavior from mock shape alone;
- return database entities directly;
- create one generic endpoint for many unrelated actions;
- use inconsistent error shapes;
- expose sensitive user data unnecessarily;
- create API contracts after implementation as an afterthought.

## Definition of Done

An API contract is ready when:

- request and response are documented;
- errors are documented;
- authentication requirement is clear;
- frontend usage is clear;
- backend use case is identified;
- tests can be derived from the contract.
