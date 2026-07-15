# Prompt 003 — Criar Contracts e API Clients Base

## Objetivo

Criar a fundação de contratos e API clients do front-end para futura integração com backend, sem conectar endpoints reais ainda.

## Leia antes

1. `docs/01-ai-contract/ai-execution-contract.md`
2. `docs/02-architecture/frontend-architecture.md`
3. `docs/02-architecture/api-contract-rules.md`
4. `docs/06-skills/frontend/criar-client-api/SKILL.md`
5. `docs/07-plans/010-frontend-alignment.md`
6. `docs/05-features/*/api-spec.md`

## Crie, se não existir

- `src/api/http-client.ts`
- `src/features/auth/contracts/requests.ts`
- `src/features/auth/contracts/responses.ts`
- `src/features/auth/contracts/index.ts`
- `src/features/auth/api/auth-api.ts`
- `src/features/dashboard/contracts/index.ts`
- `src/features/dashboard/api/dashboard-api.ts`
- `src/features/speaking/contracts/index.ts`
- `src/features/speaking/api/speaking-api.ts`
- `src/features/writing/contracts/index.ts`
- `src/features/writing/api/writing-api.ts`
- `src/features/review/contracts/index.ts`
- `src/features/review/api/review-api.ts`
- `src/features/goals/contracts/index.ts`
- `src/features/goals/api/goals-api.ts`
- `src/features/progress/contracts/index.ts`
- `src/features/progress/api/progress-api.ts`
- `src/features/mock-test/contracts/index.ts`
- `src/features/mock-test/api/mock-test-api.ts`

## Regras

- Não alterar páginas existentes.
- Não remover mocks ainda.
- Não criar backend.
- Não chamar endpoint real.
- API clients podem lançar erro explícito de `Not implemented` enquanto não houver backend.
- Contratos devem refletir as specs em `docs/05-features`.
- Não duplicar tipos existentes sem avaliar `src/types/index.ts`.
- Não adicionar dependências.

## Validação

```bash
npm run lint
npm run build
```

## Entrega esperada

Informe:

1. arquivos criados;
2. contratos definidos;
3. clients criados;
4. validações executadas;
5. pendências para integração real.
