# Backend Stack Correction

## Date

2026-07-09

## Scope

Correction of documentation that had incorrectly frozen Talanguage backend as NestJS/Prisma.

## What Was Corrected

- `docs/01-ai-contract/allowed-stack.md` now defines Spring Boot as the official backend stack.
- `docs/09-adr/001-backend-stack.md` now records Spring Boot/Java/PostgreSQL as the accepted production direction.
- `docs/02-architecture/backend-architecture.md` now describes a Spring Boot-aligned backend foundation instead of a nonexistent generic/Node-oriented backend.
- `apps/api/README.md` now reflects that `apps/api` is the official Spring Boot backend location.
- Spring Boot production prompts `021` to `030` no longer allow NestJS/Prisma/Node as alternative official backend directions.

## Why

The previous production track introduced contradictory governance: repository context and the corrected prompt pack pointed to Spring Boot, while core decision documents had been rewritten to NestJS/Prisma. That contradiction would mislead subsequent implementation prompts.

## Result

- Spring Boot is the only official backend direction in current governance documents.
- NestJS/Prisma is no longer treated as the official production backend.
- The next prompt can proceed from a consistent architectural baseline.
