# 023 — Integrar Auth Frontend-Backend


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

Implementar integrar auth frontend-backend usando Spring Boot no backend e preservando a UI existente no frontend.

## Execute


1. Revisar frontend auth existente.
2. Criar ou ajustar auth API client.
3. Substituir fake login/register por chamadas reais.
4. Preservar layout existente.
5. Adicionar loading, validation error e backend error.
6. Guardar sessão/token conforme regra documentada.
7. Remover apenas mocks mortos de auth.
8. Testar fluxo login/register/dashboard guard.


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
