<!--
Talanguage — Feature Specification
Contexto: o front-end já possui UI mockada estruturada. O backend ainda não existe.
Objetivo: orientar a transição de telas mockadas para funcionalidades reais, sem recriar a UI do zero e sem criar decisões fora da arquitetura documentada.
-->

# API Spec — Speaking Practice

## Base path
`/api/speaking`

## Endpoints

### GET /api/speaking/topics
Lista temas disponíveis.

Response 200:
```json
[
  {
    "id": "travel",
    "title": "Travel",
    "language": "ENGLISH",
    "level": "BEGINNER",
    "category": "Viagem",
    "objective": "Treinar vocabulario util para viagens.",
    "mentorMessage": "Voce chegou ao hotel e precisa confirmar reserva, horarios e pedir ajuda com o quarto.",
    "initialPrompt": "You have just arrived at the hotel. How would you confirm your reservation at the front desk?"
  }
]
```

### POST /api/speaking/sessions
Inicia sessão.

Request:
```json
{
  "language": "ENGLISH",
  "level": "BEGINNER",
  "topicId": "travel"
}
```

Response 201:
```json
{
  "sessionId": "uuid",
  "status": "IN_PROGRESS",
  "startedAt": "2026-07-07T10:00:00Z",
  "prompt": "Let's talk about travel. Where would you like to go?"
}
```

### POST /api/speaking/sessions/{sessionId}/responses
Registra resposta do usuário.

Request:
```json
{ "content": "I would like to go to Canada." }
```

Response 200:
```json
{
  "nextPrompt": "Why would you like to visit Canada?"
}
```

### POST /api/speaking/sessions/{sessionId}/finish
Finaliza sessão.

Response 200:
```json
{
  "sessionId": "uuid",
  "status": "FINISHED",
  "summary": {
    "totalMessages": 2,
    "approximateDurationMinutes": 3,
    "topicTitle": "Check-in no hotel",
    "feedback": "Sessao valida. A ideia principal apareceu, mas ainda faltou expandir melhor as respostas.",
    "nextAction": "Na proxima pratica, responda cada pergunta com pelo menos duas frases curtas e uma informacao de apoio."
  }
}
```

### GET /api/speaking/sessions/recent
Lista as sessoes recentes finalizadas do usuario autenticado.

Response 200:
```json
[
  {
    "sessionId": "uuid",
    "topicId": "travel",
    "topicTitle": "Check-in no hotel",
    "startedAt": "2026-07-07T10:00:00Z",
    "finishedAt": "2026-07-07T10:05:00Z",
    "totalMessages": 2,
    "approximateDurationMinutes": 3,
    "feedback": "Sessao valida. A ideia principal apareceu, mas ainda faltou expandir melhor as respostas.",
    "nextAction": "Na proxima pratica, responda cada pergunta com pelo menos duas frases curtas e uma informacao de apoio."
  }
]
```
