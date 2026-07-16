<!--
Talanguage - Feature Specification
Contexto: o front-end ja possui UI mockada estruturada.
Objetivo: orientar a transicao de telas mockadas para funcionalidades reais sem recriar a UI do zero.
-->

# API Spec - Auth

## Base path
`/api/auth`

## Endpoints

### POST /api/auth/register
Cria uma nova conta.

Request:
```json
{
  "name": "Pedro Cardoso",
  "email": "pedro@example.com",
  "password": "StrongPassword123",
  "targetLanguage": "ENGLISH"
}
```

Response 201:
```json
{
  "user": {
    "id": "uuid",
    "name": "Pedro Cardoso",
    "email": "pedro@example.com",
    "targetLanguage": "ENGLISH",
    "currentLevel": null,
    "studyGoal": null,
    "avatarUrl": null
  },
  "accessToken": "jwt-or-session-token"
}
```

### POST /api/auth/login
Autentica o usuario.

Request:
```json
{
  "email": "pedro@example.com",
  "password": "StrongPassword123"
}
```

Response 200:
```json
{
  "user": {
    "id": "uuid",
    "name": "Pedro Cardoso",
    "email": "pedro@example.com",
    "targetLanguage": "ENGLISH",
    "currentLevel": "INTERMEDIATE",
    "studyGoal": "Quero conversar em reunioes.",
    "avatarUrl": "https://images.example.com/pedro.png"
  },
  "accessToken": "jwt-or-session-token"
}
```

### GET /api/auth/me
Retorna o usuario autenticado.

Response 200:
```json
{
  "id": "uuid",
  "name": "Pedro Cardoso",
  "email": "pedro@example.com",
  "targetLanguage": "ENGLISH",
  "currentLevel": "INTERMEDIATE",
  "studyGoal": "Quero conversar em reunioes.",
  "avatarUrl": "https://images.example.com/pedro.png"
}
```

### POST /api/auth/logout
Encerra sessao quando aplicavel.

Response 204.

### POST /api/auth/forgot-password
Solicita recuperação sem revelar se o e-mail existe.

Autenticação: não requerida.

Request:
```json
{
  "email": "pedro@example.com"
}
```

Response 202 (sempre para e-mail válido, cadastrado ou não):
```json
{
  "message": "If the email is registered, password reset instructions will be sent."
}
```

O token nunca é retornado. Solicitações repetidas podem ser suprimidas pela proteção contra abuso sem alterar a resposta.

### POST /api/auth/reset-password
Redefine a senha com token válido, não expirado e ainda não utilizado.

Autenticação: não requerida.

Request:
```json
{
  "token": "opaque-token-received-by-notification",
  "newPassword": "NewStrongPassword123"
}
```

Response 204.

Erros:
- 400 `VALIDATION_ERROR` para senha fora da política;
- 400 `PASSWORD_RESET_TOKEN_INVALID` para token inválido, expirado ou já usado, sem distinguir o motivo.

## Erros
- 400 para dados invalidos;
- 401 para credenciais invalidas;
- 409 para e-mail ja cadastrado.

## Regras
- Controller nao valida regra de negocio complexa.
- DTO de entrada nao deve ser entidade.
- Nunca retornar `passwordHash`.
- Nunca retornar ou registrar token de recuperação, senha ou existência do e-mail.
