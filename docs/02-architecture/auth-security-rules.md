# Auth and Security Rules — Talanguage

## Purpose

This document defines authentication and security rules for Talanguage.

The frontend already contains UI flows, but backend authentication still needs to be implemented.

Security must be designed before real user data, AI interactions or persisted learning history are introduced.

## Authentication Direction

The first version must support:

- user registration;
- user login;
- authenticated access to private routes;
- user identity available to backend use cases;
- logout.

## Auth Boundary

The backend is the source of truth for authentication and authorization.

Frontend route protection improves user experience, but it is not a security boundary.

## Frontend Auth Rules

The frontend may:

- show login/register screens;
- store session state according to chosen auth mechanism;
- redirect unauthenticated users;
- hide private navigation items when unauthenticated;
- call authenticated endpoints with the proper token/session.

The frontend must not:

- be the only layer enforcing authorization;
- trust userId passed from UI for protected operations;
- expose tokens in unsafe places;
- hardcode credentials.

## Backend Auth Rules

The backend must:

- validate authentication for private endpoints;
- derive user identity from the authenticated context;
- reject access to resources owned by another user;
- avoid trusting userId from request body when user identity should come from auth context.

## Password Rules

If implementing local email/password auth:

- never store plain text passwords;
- use a secure password hashing strategy;
- validate minimum password requirements;
- avoid leaking whether email exists during sensitive flows when applicable.

If using an external auth provider, document the provider and integration rules.

## Authorization Rules

For the first version, authorization can remain simple:

- users can access their own learning data;
- users cannot access another user's private progress, sessions, flashcards or writing history;
- admin functionality is out of scope unless explicitly added.

## Session and Token Rules

The selected auth strategy must document:

- where token/session is stored;
- expiration behavior;
- refresh behavior if any;
- logout behavior;
- frontend handling of unauthorized responses.

## API Security Rules

Protected endpoints must:

- require authentication;
- validate input;
- avoid returning sensitive data;
- verify resource ownership when applicable;
- return consistent errors.

## AI Security Rules

Any future AI integration must consider:

- user data minimization;
- prompt injection risks;
- provider data usage policy;
- cost abuse;
- rate limiting;
- logging strategy;
- storage of conversations or writing samples.

Real AI integration requires explicit documented approval.

## Rate Limiting

Rate limiting should be considered for:

- login attempts;
- registration;
- AI-assisted endpoints;
- expensive feedback generation;
- community search if abused.

Do not overbuild rate limiting before real backend flows exist, but do not ignore it for AI endpoints.

## Forbidden

Do not:

- store plain passwords;
- trust frontend-only authorization;
- accept userId from body for private resource ownership when auth context exists;
- expose tokens in logs;
- log sensitive learning content unnecessarily;
- call AI providers with private data before privacy decision;
- create admin capabilities without spec.

## Definition of Done

An auth/security-sensitive feature is done when:

- auth requirement is documented;
- backend enforces access;
- frontend handles unauthorized state;
- resource ownership is checked;
- sensitive data is minimized;
- tests cover unauthorized and forbidden scenarios.
