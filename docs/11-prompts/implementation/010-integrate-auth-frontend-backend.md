# Prompt 010 — Integrar Auth Frontend-Backend

## Objetivo

Substituir gradualmente o fluxo fake de Auth por integração real com o backend.

## Leia antes

1. `docs/01-ai-contract/ai-execution-contract.md`
2. `docs/05-features/auth/api-spec.md`
3. `docs/05-features/auth/frontend-spec.md`
4. `docs/05-features/auth/test-spec.md`
5. `docs/06-skills/fullstack/integrar-frontend-backend/SKILL.md`
6. `docs/07-plans/002-auth.md`
7. `docs/07-plans/010-frontend-alignment.md`

## Regras

- Não redesenhar login/register.
- Não alterar UI além do necessário para loading/error.
- Página não chama fetch direto.
- Usar auth-api client.
- Tratar loading, erro de validação e erro inesperado.
- Não manter fallback fake silencioso em produção.

## Validação

Rodar validações disponíveis de frontend e backend.

## Entrega esperada

Informe:

1. fluxo integrado;
2. mocks removidos ou isolados;
3. estados de UI adicionados;
4. testes criados/alterados;
5. validações executadas.
