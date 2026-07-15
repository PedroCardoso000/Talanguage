# Prompt 005 — Extrair Componentes Compartilhados

## Objetivo

Extrair padrões visuais repetidos que hoje estão presos dentro das páginas, sem redesenhar a UI.

## Leia antes

1. `docs/01-ai-contract/ai-execution-contract.md`
2. `docs/03-design-system/ui-components.md`
3. `docs/06-skills/frontend/criar-componente-ui/SKILL.md`
4. `docs/07-plans/010-frontend-alignment.md`
5. `docs/10-audits/frontend-current-state.md`

## Prioridade de extração

Avalie e extraia, se houver repetição real:

- score cards;
- feedback panels;
- metric cards;
- progress meters;
- numeric goal fields.

## Regras

- Não alterar layout percebido.
- Não alterar regra de negócio.
- Não mover lógica de domínio.
- Não criar componentes genéricos demais.
- Só extrair padrões usados ou claramente reutilizáveis.

## Validação

```bash
npm run lint
npm run build
```

## Entrega esperada

Informe:

1. componentes extraídos;
2. páginas ajustadas;
3. componentes que não foram extraídos e por quê;
4. validações executadas.
