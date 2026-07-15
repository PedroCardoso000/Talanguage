<!--
Talanguage - Feature Specification
Objetivo: definir o contrato da feature de notificacoes antes da integracao front-back.
-->

# API Spec - Notifications

## Base path
`/api/notifications`

## Authentication
Todos os endpoints exigem usuario autenticado.

## Endpoints

### GET /api/notifications
Lista as notificacoes mais recentes do usuario.

Response 200:
```json
[
  {
    "id": "uuid",
    "title": "Bem-vindo ao Tala",
    "message": "Seu treino comecou. Ajuste o perfil e mantenha consistencia diaria.",
    "actionRoute": "/profile",
    "createdAt": "2026-07-13T14:00:00Z",
    "readAt": null,
    "read": false
  }
]
```

### PATCH /api/notifications/{id}/read
Marca uma notificacao como lida.

Response 200:
```json
{
  "id": "uuid",
  "title": "Bem-vindo ao Tala",
  "message": "Seu treino comecou. Ajuste o perfil e mantenha consistencia diaria.",
  "actionRoute": "/profile",
  "createdAt": "2026-07-13T14:00:00Z",
  "readAt": "2026-07-13T14:05:00Z",
  "read": true
}
```

## Error responses
- `401 UNAUTHORIZED` para usuario nao autenticado;
- `404 NOT_FOUND` para notificacao inexistente ou de outro usuario.

## Frontend usage notes
- contador do topbar deve ser derivado das notificacoes nao lidas;
- nao ha tempo real nesta versao;
- marcar como lida deve persistir no backend.
