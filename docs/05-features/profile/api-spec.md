<!--
Talanguage - Feature Specification
Objetivo: definir o contrato da feature de perfil antes da integracao front-back.
-->

# API Spec - Profile

## Base path
`/api/profile`

## Authentication
Todos os endpoints exigem usuario autenticado.

## Endpoints

### GET /api/profile/me
Retorna o perfil atual do usuario autenticado.

Response 200:
```json
{
  "id": "uuid",
  "name": "Pedro Cardoso",
  "email": "pedro@example.com",
  "targetLanguage": "ENGLISH",
  "currentLevel": "INTERMEDIATE",
  "studyGoal": "Quero conversar em reunioes sem roteiro.",
  "avatarUrl": "https://images.example.com/pedro.png"
}
```

### PUT /api/profile/me
Atualiza os campos editaveis do perfil.

Request:
```json
{
  "name": "Pedro Cardoso",
  "targetLanguage": "FRENCH",
  "currentLevel": "INTERMEDIATE",
  "studyGoal": "Quero conversar em reunioes sem roteiro.",
  "avatarUrl": "https://images.example.com/pedro.png"
}
```

Response 200:
```json
{
  "id": "uuid",
  "name": "Pedro Cardoso",
  "email": "pedro@example.com",
  "targetLanguage": "FRENCH",
  "currentLevel": "INTERMEDIATE",
  "studyGoal": "Quero conversar em reunioes sem roteiro.",
  "avatarUrl": "https://images.example.com/pedro.png"
}
```

## Validation rules
- `name` obrigatorio;
- `targetLanguage` aceito: `ENGLISH`, `SPANISH`, `FRENCH`;
- `currentLevel` aceito: `BEGINNER`, `INTERMEDIATE`, `ADVANCED`;
- `studyGoal` maximo de 240 caracteres;
- `avatarUrl` opcional, mas quando enviada deve iniciar com `http://` ou `https://`.

## Error responses
- `400 VALIDATION_ERROR` para payload invalido;
- `401 UNAUTHORIZED` para usuario nao autenticado.

## Frontend usage notes
- UI deve tratar `email` como somente leitura;
- upload de arquivo nao faz parte desta versao;
- `avatarUrl` substitui upload real ate existir infraestrutura dedicada.
