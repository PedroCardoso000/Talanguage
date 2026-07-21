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
    "type": "WELCOME",
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
  "type": "WELCOME",
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

## Politica de geracao e confiabilidade

- `WELCOME` e gerada depois que o cadastro do usuario e persistido;
- `ONBOARDING_COMPLETED` e gerada depois que o onboarding e persistido e e deduplicada por usuario;
- `PROFILE_UPDATED` representa uma alteracao de perfil efetivamente persistida e repeticoes do mesmo estado sao deduplicadas;
- `STREAK_LOST` e gerada somente quando uma nova atividade persistida confirma que a ultima atividade anterior ocorreu ha mais de um dia completo, comparando datas em UTC;
- nao existe deteccao proativa de perda de streak: sem scheduler, nenhuma notificacao e criada apenas pela passagem do tempo;
- nenhuma notificacao cuja ocorrencia nao possa ser confirmada por estado persistido e simulada;
- a chave unica `(user_id, type, deduplication_key)` e a fonte de idempotencia, inclusive sob concorrencia;
- notificacoes sao privadas: listagem e marcacao de leitura sempre usam o usuario autenticado como filtro.
