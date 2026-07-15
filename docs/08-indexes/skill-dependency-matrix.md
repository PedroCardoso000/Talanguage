# Skill Dependency Matrix — Talanguage

## Purpose

Define which skills must be used together for each type of task.

Skills are operational instructions for the AI. Specs define what to build. Skills define how to build it.

## Full-Stack Tasks

| Task | Required Skills | Optional Skills |
|---|---|---|
| Create complete feature | `fullstack/criar-feature-ponta-a-ponta` | `governance/validar-aderencia-arquitetural`, `governance/validar-economia-de-tokens` |
| Integrate existing frontend with backend | `fullstack/integrar-frontend-backend`, `fullstack/validar-contrato-api` | `frontend/criar-client-api`, `frontend/criar-estado-ui` |
| Review complete feature | `fullstack/revisar-feature-completa` | `governance/validar-dependencias-camadas` |
| Validate API compatibility | `fullstack/validar-contrato-api` | `backend/criar-dto`, `frontend/criar-client-api` |

## Backend Tasks

| Task | Required Skills | Optional Skills |
|---|---|---|
| Create entity | `backend/criar-entidade` | `backend/criar-value-object` |
| Create value object | `backend/criar-value-object` | None |
| Create aggregate | `backend/criar-agregado` | `backend/criar-entidade`, `backend/criar-value-object` |
| Create use case | `backend/criar-caso-de-uso` | `backend/criar-repositorio`, `backend/criar-dto` |
| Create repository | `backend/criar-repositorio` | `backend/criar-migration` |
| Create endpoint | `backend/criar-endpoint`, `backend/criar-dto` | `fullstack/validar-contrato-api` |
| Create migration | `backend/criar-migration` | `backend/criar-repositorio` |
| Create unit tests | `backend/criar-testes-unitarios` | None |
| Create integration tests | `backend/criar-testes-integracao` | `backend/criar-endpoint`, `backend/criar-repositorio` |

## Frontend Tasks

| Task | Required Skills | Optional Skills |
|---|---|---|
| Create page | `frontend/criar-pagina` | `frontend/criar-componente-ui`, `frontend/criar-estado-ui` |
| Create UI component | `frontend/criar-componente-ui` | None |
| Create form | `frontend/criar-formulario` | `frontend/criar-estado-ui` |
| Create hook | `frontend/criar-hook` | `frontend/criar-client-api` |
| Create API client | `frontend/criar-client-api` | `fullstack/validar-contrato-api` |
| Create UI state | `frontend/criar-estado-ui` | `frontend/criar-hook` |
| Create frontend tests | `frontend/criar-testes-frontend` | None |

## Governance Tasks

| Task | Required Skills |
|---|---|
| Validate first-version scope | `governance/validar-escopo-primeira-versao` |
| Validate architecture | `governance/validar-aderencia-arquitetural` |
| Validate layer dependencies | `governance/validar-dependencias-camadas` |
| Validate token usage | `governance/validar-economia-de-tokens` |

## Dependency Rules

- A full-stack task may call backend and frontend skills, but must not duplicate their instructions inline.
- A backend skill must not define UI behavior.
- A frontend skill must not define business rules.
- A governance skill may block implementation when it detects scope or architecture violations.
- A feature review must run after implementation, not before.

## Done Criteria

A task is properly guided when the AI explicitly identifies the primary skill and any supporting skills before making changes.
