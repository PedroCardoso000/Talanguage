# Reading Order — Talanguage

## Purpose

Define which documents the AI must read for each type of task.

This file exists to reduce token usage and prevent the AI from reading unrelated documentation.

## Current Project Context

The project already has a structured frontend with mocked data and non-functional UI flows.

The backend has not been created yet.

Future implementation must progressively connect the existing frontend to real backend contracts without recreating the UI from scratch.

## Global Rule

The AI must not read all project documentation for every task.

The AI must read only the documents listed for the task type.

## For Any Task

Always read:

1. `docs/01-ai-contract/ai-execution-contract.md`
2. `docs/01-ai-contract/token-economy-rules.md`
3. `docs/01-ai-contract/allowed-stack.md`
4. The specific spec, skill, plan or template related to the task

## For Product Scope Decisions

Read:

1. `docs/00-product/product-context.md`
2. `docs/00-product/first-version-scope.md`
3. `docs/00-product/out-of-scope.md`
4. `docs/01-ai-contract/forbidden-decisions.md`

## For Architecture Decisions

Read:

1. `docs/02-architecture/system-overview.md`
2. The specific architecture document related to the decision
3. `docs/01-ai-contract/forbidden-decisions.md`
4. `docs/09-templates/adr.template.md`

## For Backend Feature Implementation

Read:

1. `docs/01-ai-contract/ai-execution-contract.md`
2. `docs/02-architecture/backend-architecture.md`
3. `docs/02-architecture/clean-architecture-rules.md`
4. `docs/02-architecture/api-contract-rules.md`
5. `docs/02-architecture/database-rules.md`, only if persistence is required
6. `docs/02-architecture/auth-security-rules.md`, only if authentication or authorization is involved
7. `docs/05-features/{feature}/domain-spec.md`
8. `docs/05-features/{feature}/backend-spec.md`
9. `docs/05-features/{feature}/api-spec.md`
10. `docs/05-features/{feature}/test-spec.md`
11. The required backend skill

## For Frontend Feature Implementation

Read:

1. `docs/01-ai-contract/ai-execution-contract.md`
2. `docs/02-architecture/frontend-architecture.md`
3. `docs/03-design-system/visual-identity.md`
4. `docs/03-design-system/ui-components.md`
5. `docs/03-design-system/layout-rules.md`
6. `docs/05-features/{feature}/frontend-spec.md`
7. `docs/05-features/{feature}/api-spec.md`, when the feature consumes backend data
8. The required frontend skill

## For Full-Stack Feature Implementation

Read:

1. `docs/01-ai-contract/ai-execution-contract.md`
2. `docs/01-ai-contract/definition-of-done.md`
3. `docs/02-architecture/system-overview.md`
4. `docs/02-architecture/backend-architecture.md`
5. `docs/02-architecture/frontend-architecture.md`
6. `docs/02-architecture/clean-architecture-rules.md`
7. `docs/02-architecture/api-contract-rules.md`
8. `docs/04-domain/language-learning-domain.md`
9. `docs/05-features/{feature}/feature-spec.md`
10. `docs/05-features/{feature}/domain-spec.md`
11. `docs/05-features/{feature}/backend-spec.md`
12. `docs/05-features/{feature}/api-spec.md`
13. `docs/05-features/{feature}/frontend-spec.md`
14. `docs/05-features/{feature}/test-spec.md`
15. `docs/06-skills/fullstack/criar-feature-ponta-a-ponta/SKILL.md`

## For Desmocking Existing Frontend

Read:

1. `docs/01-ai-contract/ai-execution-contract.md`
2. `docs/02-architecture/frontend-architecture.md`
3. `docs/02-architecture/api-contract-rules.md`
4. `docs/05-features/{feature}/api-spec.md`
5. `docs/05-features/{feature}/frontend-spec.md`
6. `docs/06-skills/fullstack/integrar-frontend-backend/SKILL.md`

## For Test Creation

Read:

1. `docs/01-ai-contract/ai-execution-contract.md`
2. `docs/02-architecture/testing-strategy.md`
3. `docs/05-features/{feature}/test-spec.md`
4. The required test skill

## For UI Review

Read:

1. `docs/03-design-system/visual-identity.md`
2. `docs/03-design-system/ui-components.md`
3. `docs/03-design-system/layout-rules.md`
4. `docs/03-design-system/ux-writing.md`
5. `docs/03-design-system/responsive-rules.md`
6. `docs/05-features/{feature}/frontend-spec.md`

## For Scope Validation

Read:

1. `docs/00-product/first-version-scope.md`
2. `docs/00-product/out-of-scope.md`
3. `docs/01-ai-contract/forbidden-decisions.md`
4. `docs/06-skills/governance/validar-escopo-primeira-versao/SKILL.md`

## Prohibited

The AI must not:

- read all docs by default;
- load unrelated feature specs;
- use templates as product requirements;
- infer backend behavior from mocked frontend data alone;
- change architecture based only on one feature need;
- create code from a feature name without reading the feature specs.

## Done Criteria

This index is respected when each task explicitly lists the documents read before implementation.
