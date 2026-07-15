# Prompt 015 — Preparar Writing Review

## Objetivo

Preparar escrita e revisão para integração futura, removendo dependência direta de feedback fake no componente.

## Leia antes

1. `docs/01-ai-contract/ai-execution-contract.md`
2. `docs/05-features/writing-review/feature-spec.md`
3. `docs/05-features/writing-review/api-spec.md`
4. `docs/05-features/writing-review/frontend-spec.md`
5. `docs/05-features/writing-review/test-spec.md`
6. `docs/07-plans/005-writing-review.md`
7. `docs/07-plans/010-frontend-alignment.md`

## Regras

- Não implementar IA real.
- Não chamar provider externo.
- Feedback deve vir de client/mock adapter ou backend futuro.
- UI deve suportar loading/error/success.
- Não redesenhar tela.

## Entrega esperada

Informe contratos, API client, mocks e partes do feedback fake isoladas.
