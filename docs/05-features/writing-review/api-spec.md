<!--
Talanguage — Feature Specification
Contexto: o front-end já possui UI mockada estruturada. O backend ainda não existe.
Objetivo: orientar a transição de telas mockadas para funcionalidades reais, sem recriar a UI do zero e sem criar decisões fora da arquitetura documentada.
-->

# API Spec — Writing Review

## Base path
`/api/writing`

## Endpoints

### GET /api/writing/challenges
Lista desafios.

Query opcional:
- language;
- level.

Response 200:
```json
[
  {
    "id": "introduce-yourself",
    "language": "ENGLISH",
    "level": "BEGINNER",
    "prompt": "Introduce yourself in 4 sentences.",
    "focus": "Clarity and basic structure."
  }
]
```

### GET /api/writing/challenges/current
Retorna o desafio atual padrao do fluxo de escrita.

Response 200:
```json
{
  "id": "introduce-yourself",
  "language": "ENGLISH",
  "level": "BEGINNER",
  "prompt": "Introduce yourself in 4 sentences.",
  "focus": "Clarity and basic structure."
}
```

### POST /api/writing/submissions
Envia resposta.

Request:
```json
{
  "challengeId": "introduce-yourself",
  "content": "My name is Pedro. I am from Brazil. I work with software. I want to learn English."
}
```

Response 201:
```json
{
  "submissionId": "uuid",
  "status": "REVIEWED",
  "feedback": {
    "strengths": ["Voce usou pontuacao, o que ajuda a separar melhor as ideias."],
    "improvements": ["Use conectores simples como because, but, so ou however para ligar melhor as frases."],
    "nextAction": "Reescreva o texto adicionando um conector simples entre as ideias para ganhar fluidez."
  }
}
```

### GET /api/writing/submissions/recent
Lista envios recentes.
