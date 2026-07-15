# Prompt 001 — Criar UI Primitives

## Objetivo

Implemente a primeira etapa do plano de alinhamento: criar os UI primitives básicos.

## Leia antes

1. `docs/01-ai-contract/ai-execution-contract.md`
2. `docs/02-architecture/frontend-architecture.md`
3. `docs/03-design-system/ui-components.md`
4. `docs/06-skills/frontend/criar-componente-ui/SKILL.md`
5. `docs/07-plans/010-frontend-alignment.md`

## Crie

- `src/components/ui/button.tsx`
- `src/components/ui/input.tsx`
- `src/components/ui/textarea.tsx`
- `src/components/ui/select.tsx`
- `src/components/ui/card.tsx`
- `src/components/ui/badge.tsx`
- `src/components/ui/progress-bar.tsx`
- `src/components/ui/index.ts`

## Regras

- Não alterar páginas existentes.
- Não refatorar telas.
- Não mudar rotas.
- Não adicionar dependências.
- Não criar backend.
- Não mexer em store.
- Não mexer nos mocks.
- Usar TypeScript.
- Usar Tailwind.
- Componentes devem ser simples, pequenos e reutilizáveis.
- Não colocar regra de produto nos componentes.
- Exportar todos os componentes em `src/components/ui/index.ts`.

## Validação

Ao final, rode quando possível:

```bash
npm run lint
npm run build
```

## Entrega esperada

Informe:

1. arquivos criados;
2. padrão usado;
3. validações executadas;
4. riscos ou decisões assumidas.
