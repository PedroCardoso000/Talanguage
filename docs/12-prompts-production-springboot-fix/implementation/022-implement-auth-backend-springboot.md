# 022 — Auth Backend Real


# Regras globais deste prompt

Antes de executar:

1. Leia `docs/01-ai-contract/ai-execution-contract.md`.
2. Leia `docs/01-ai-contract/token-economy-rules.md`.
3. Leia `docs/08-indexes/reading-order.md`.
4. Use apenas documentos necessários para a tarefa atual.

Regras obrigatórias:

- Não recriar aplicação existente.
- Não trocar stack.
- Backend oficial: Spring Boot.
- Alterar o menor número possível de arquivos.
- Preservar UI existente.
- Não adicionar dependências sem necessidade real.
- Não implementar funcionalidade fora das specs.
- Se já existir implementação equivalente, revisar, corrigir e reaproveitar.
- Se houver conflito entre documentação e código, documentar a decisão antes de alterar.


## Leia também

- `docs/05-features/auth/feature-spec.md`, se existir
- `docs/05-features/auth/domain-spec.md`, se existir
- `docs/05-features/auth/api-spec.md`, se existir
- `docs/05-features/auth/backend-spec.md`, se existir
- `docs/05-features/auth/frontend-spec.md`, se existir
- `docs/05-features/auth/test-spec.md`, se existir
- `docs/06-skills/fullstack/criar-feature-ponta-a-ponta/SKILL.md`
- `docs/06-skills/fullstack/integrar-frontend-backend/SKILL.md`

## Objetivo

Implementar auth backend real usando Spring Boot no backend e preservando a UI existente no frontend.

## Execute


1. Implementar cadastro e login reais conforme specs.
2. Criar entidades/value objects mínimos de usuário/sessão conforme domínio.
3. Criar use cases para register/login/me/logout quando aplicável.
4. Criar DTOs request/response.
5. Criar endpoints REST.
6. Persistir usuário em PostgreSQL quando banco estiver configurado; usar alternativa local apenas se a documentação permitir.
7. Implementar hash de senha com mecanismo seguro disponível na stack.
8. Criar testes unitários dos use cases principais.
9. Criar testes de API básicos para register/login/me.


## Proibido

- Recriar tela do zero se ela já existir.
- Usar backend diferente de Spring Boot/Java/PostgreSQL como direção oficial.
- Colocar regra de negócio no frontend.
- Criar dependência nova sem necessidade.
- Implementar IA real, áudio real ou WebRTC neste prompt, salvo se explicitamente pedido.

## Critério de pronto

- Fluxo funcional ponta a ponta quando aplicável.
- Backend Spring Boot contém use case, endpoint e teste principal.
- Frontend chama API client, não mock direto.
- UI existente preservada.
- Mocks mortos removidos ou isolados.
