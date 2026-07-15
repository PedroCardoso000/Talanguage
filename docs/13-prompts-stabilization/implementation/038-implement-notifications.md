# 038 — Implement Notifications

## Objetivo
Implementar funcionalidade simples de notificações persistidas no backend, sem tempo real.

## Leia apenas
1. `docs/01-ai-contract/ai-execution-contract.md`
2. `docs/02-architecture/api-contract-rules.md`
3. `docs/02-architecture/backend-architecture.md`
4. `docs/02-architecture/frontend-architecture.md`
5. `docs/06-skills/fullstack/criar-feature-ponta-a-ponta/SKILL.md`
6. `docs/10-audits/current-functional-gap-audit.md`

## Escopo
Notificação simples:
- listar notificações do usuário;
- marcar como lida;
- mostrar contador no topbar;
- criar seed inicial opcional para desenvolvimento.

## Backend
Criar/ajustar:
- entidade Notification;
- repository;
- use cases;
- endpoints:
  - `GET /api/notifications`
  - `PATCH /api/notifications/{id}/read`

## Frontend
Conectar topbar a dados reais.
Adicionar painel/dropdown simples ou página simples se já existir padrão.

## Regras
- Não implementar WebSocket.
- Não implementar push notification.
- Não criar sistema complexo de preferências.
- Não usar mock como fonte final.

## Critério de pronto
- Notificações vêm do backend.
- Contador muda ao marcar como lida.
- Estado persiste no banco.
