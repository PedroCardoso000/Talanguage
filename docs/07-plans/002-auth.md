# Plan 002 — Auth

## Objetivo

Implementar a base de autenticação da primeira versão, conectando as telas existentes de login/cadastro ao backend real quando disponível.

## Premissas

- As telas de login e cadastro podem já existir no front-end.
- A UI existente deve ser reaproveitada.
- O backend ainda pode não existir no início da execução.
- O fluxo pode iniciar com mock, mas deve ser preparado para API real.

## Documentos obrigatórios

- `docs/01-ai-contract/ai-execution-contract.md`
- `docs/02-architecture/auth-security-rules.md`
- `docs/02-architecture/api-contract-rules.md`
- `docs/02-architecture/backend-architecture.md`
- `docs/02-architecture/frontend-architecture.md`
- `docs/05-features/auth/feature-spec.md`
- `docs/05-features/auth/domain-spec.md`
- `docs/05-features/auth/api-spec.md`
- `docs/05-features/auth/backend-spec.md`
- `docs/05-features/auth/frontend-spec.md`
- `docs/05-features/auth/test-spec.md`
- `docs/06-skills/fullstack/criar-feature-ponta-a-ponta/SKILL.md`

## Ordem de execução

1. Validar escopo da feature Auth.
2. Mapear telas existentes de login e cadastro.
3. Definir contratos de API para cadastro, login, sessão atual, logout e recuperação de senha.
4. Criar domínio mínimo de usuário/autenticação.
5. Criar casos de uso de cadastro e login.
6. Criar DTOs de entrada e saída.
7. Criar endpoints de autenticação.
8. Criar persistência mínima de usuário.
9. Criar testes unitários dos casos de uso.
10. Criar testes de API dos fluxos principais.
11. Criar ou ajustar client API no front-end.
12. Substituir mock de autenticação por integração real, quando API existir.
13. Validar redirecionamento para área privada.
14. Validar estados de erro e carregamento.
15. Implementar tokens de recuperação com hash, uso único e expiração curta.
16. Integrar `PasswordResetNotifier` a um provedor autorizado antes de disponibilizar o fluxo em produção.

## Entregáveis esperados

- Login funcional.
- Cadastro funcional.
- Logout funcional, se definido na spec.
- Recuperação e redefinição de senha no backend.
- Sessão de usuário representada no front-end.
- Contrato de API documentado e respeitado.
- Testes principais criados.

## Proibido

- Armazenar senha em texto puro.
- Criar regra de autenticação no front-end.
- Criar token sem estratégia definida.
- Criar fluxo social login sem spec.
- Expor token de recuperação em endpoint ou log.
- Inventar provedor de e-mail não autorizado.
- Misturar DTO com entidade de domínio.

## Critério de pronto

A feature Auth estará pronta quando:

- o usuário conseguir se cadastrar;
- o usuário conseguir fazer login;
- o front-end consumir a API por client dedicado;
- erros de autenticação forem exibidos de forma clara;
- os testes principais passarem;
- nenhuma regra sensível estiver no front-end.
