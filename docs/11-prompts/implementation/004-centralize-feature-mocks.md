# Prompt 004 — Centralizar Mocks por Feature

## Objetivo

Organizar mocks para que eles deixem de ficar espalhados entre páginas, store e helpers, criando uma fronteira clara para desmocking futuro.

## Leia antes

1. `docs/01-ai-contract/ai-execution-contract.md`
2. `docs/02-architecture/frontend-architecture.md`
3. `docs/07-plans/010-frontend-alignment.md`
4. `docs/10-audits/frontend-current-state.md`

## Tarefa

Mapeie os mocks existentes em:

- `src/data/mock-content.ts`
- `src/store/use-app-store.ts`
- `src/lib/feedback.ts`
- arrays inline em páginas e componentes

Crie ou ajuste mocks por feature em:

- `src/features/{feature}/mocks/`

## Regras

- Não alterar comportamento visual.
- Não mudar fluxo de navegação.
- Não integrar backend.
- Não remover mocks ainda se a página depende deles.
- Apenas mover/organizar quando o risco for baixo.
- Se uma movimentação for grande demais, documente como pendência em vez de executar.

## Validação

```bash
npm run lint
npm run build
```

## Entrega esperada

Informe:

1. mocks movidos;
2. mocks mantidos por risco;
3. arquivos alterados;
4. validações executadas;
5. próximos pontos de desmocking.
