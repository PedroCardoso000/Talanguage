<!--
Talanguage — Feature Specification
Contexto: o front-end já possui UI mockada estruturada. O backend ainda não existe.
Objetivo: orientar a transição de telas mockadas para funcionalidades reais, sem recriar a UI do zero e sem criar decisões fora da arquitetura documentada.
-->

# API Spec — Community

## Base path
`/api/community`

## Endpoints

### GET /api/community/groups
Lista grupos.

Query opcional:
- language;
- level.

Response 200:
```json
[
  {
    "id": "group-travel-beginners",
    "title": "Travel English Circle",
    "language": "ENGLISH",
    "level": "BEGINNER",
    "description": "Grupo leve para praticar situacoes de viagem.",
    "memberCount": 18
  }
]
```

### GET /api/community/partners
Lista parceiros sugeridos.

Query opcional:
- language.

Response 200:
```json
[
  {
    "userId": "partner-ana",
    "displayName": "Ana Torres",
    "languagesPracticed": ["ENGLISH"],
    "level": "INTERMEDIATE",
    "availabilityNote": "Disponivel a noite para conversas rapidas."
  }
]
```

### POST /api/community/interests
Registra interesse.

Request:
```json
{
  "targetType": "GROUP",
  "targetId": "uuid"
}
```

Response 201:
```json
{
  "id": "uuid",
  "targetType": "GROUP",
  "targetId": "uuid",
  "createdAt": "2026-07-07T10:00:00Z"
}
```

Response 200:
Mesmo payload acima quando o interesse ja existir para o mesmo usuario e alvo.

## Regras
- endpoints protegidos;
- não expor e-mail de outros usuários;
- retorno deve ser enxuto.
- interesse duplicado deve ser idempotente.
