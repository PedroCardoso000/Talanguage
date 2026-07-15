# Prompt 006 — Alinhar Rotas e Navegação

## Objetivo

Alinhar as rotas existentes com a documentação sem quebrar o front-end atual.

## Leia antes

1. `docs/01-ai-contract/ai-execution-contract.md`
2. `docs/02-architecture/frontend-architecture.md`
3. `docs/08-indexes/route-api-component-matrix.md`
4. `docs/07-plans/010-frontend-alignment.md`
5. `docs/10-audits/frontend-current-state.md`

## Tarefa

Verifique as rotas existentes:

- `/login`
- `/register`
- `/dashboard`
- `/speak`
- `/write`
- `/review`
- `/goals`
- `/progress`
- `/mock-test`

E compare com os documentos.

## Regras

- Não renomear rotas sem necessidade.
- Não quebrar navegação existente.
- Não recriar páginas.
- Se houver conflito entre rota atual e docs, atualize a documentação de matriz antes de alterar código.
- Não criar `/community` ainda sem decisão explícita.

## Entrega esperada

Informe:

1. rotas preservadas;
2. conflitos encontrados;
3. documentação atualizada, se necessário;
4. riscos;
5. validações executadas.
