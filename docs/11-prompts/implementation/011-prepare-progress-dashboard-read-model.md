# Prompt 011 — Preparar Progress/Dashboard Read Model

## Objetivo

Criar uma fronteira clara para os dados compartilhados entre Dashboard e Progress.

## Leia antes

1. `docs/01-ai-contract/ai-execution-contract.md`
2. `docs/05-features/dashboard/api-spec.md`
3. `docs/05-features/dashboard/frontend-spec.md`
4. `docs/05-features/progress/api-spec.md`
5. `docs/05-features/progress/frontend-spec.md`
6. `docs/07-plans/003-dashboard.md`
7. `docs/07-plans/008-progress.md`
8. `docs/07-plans/010-frontend-alignment.md`

## Regras

- Não recalcular progresso na UI se o contrato define read model.
- Não quebrar cards existentes.
- Não redesenhar dashboard.
- Criar client/contract se ainda não existir.
- Mocks devem simular payload de API, não regra espalhada.

## Entrega esperada

Informe arquivos alterados, contratos criados e próximos passos para backend real.
