<!--
Talanguage — Feature Specification
Contexto: o front-end já possui UI mockada estruturada. O backend ainda não existe.
Objetivo: orientar a transição de telas mockadas para funcionalidades reais, sem recriar a UI do zero e sem criar decisões fora da arquitetura documentada.
-->

# API Spec — Flashcards

## Base path
`/api/flashcards`

## Endpoints

### GET /api/flashcards
Lista flashcards do usuário.

### POST /api/flashcards
Cria flashcard.

Request:
```json
{
  "front": "How are you?",
  "back": "Como você está?",
  "language": "ENGLISH",
  "tags": ["greetings"]
}
```

Response 201:
```json
{
  "id": "uuid",
  "front": "How are you?",
  "back": "Como você está?",
  "language": "ENGLISH",
  "nextReviewAt": "2026-07-08T00:00:00Z"
}
```

### DELETE /api/flashcards/{id}
Exclui flashcard do usuario autenticado.

Response 204:
Sem corpo.

### POST /api/flashcards/{id}/review
Registra revisão.

Request:
```json
{ "rating": "GOOD" }
```

Response 200:
```json
{
  "id": "uuid",
  "nextReviewAt": "2026-07-10T00:00:00Z",
  "reviewCount": 3
}
```
