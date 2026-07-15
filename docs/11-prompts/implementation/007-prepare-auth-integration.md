# Prompt 007 — Preparar Auth Para Integração

## Objetivo

Preparar a feature Auth para substituir login/register fake por contrato real futuramente, sem implementar backend ainda.

## Leia antes

1. `docs/01-ai-contract/ai-execution-contract.md`
2. `docs/05-features/auth/feature-spec.md`
3. `docs/05-features/auth/api-spec.md`
4. `docs/05-features/auth/frontend-spec.md`
5. `docs/05-features/auth/test-spec.md`
6. `docs/06-skills/frontend/criar-client-api/SKILL.md`
7. `docs/07-plans/002-auth.md`
8. `docs/07-plans/010-frontend-alignment.md`

## Tarefa

Crie uma fronteira clara para Auth:

- contracts de login/register/session;
- auth-api client;
- auth mock adapter, se necessário;
- separação entre UI de formulário e ação de autenticação.

## Regras

- Não criar backend.
- Não remover fluxo fake se ele ainda mantém navegação funcional.
- Não quebrar login/register existentes.
- Não colocar regra de sessão em componente visual.
- Não adicionar dependências.

## Validação

```bash
npm run lint
npm run build
```

## Entrega esperada

Informe:

1. fronteiras criadas;
2. arquivos alterados;
3. fluxo fake preservado ou ajustado;
4. pendências para backend real;
5. validações executadas.
