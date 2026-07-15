<!--
Talanguage — Feature Specification
Contexto: o front-end já possui UI mockada estruturada. O backend ainda não existe.
Objetivo: orientar a transição de telas mockadas para funcionalidades reais, sem recriar a UI do zero e sem criar decisões fora da arquitetura documentada.
-->

# Domain Spec — Speaking Practice

## Agregado

### SpeakingSession
Raiz do agregado de uma prática de fala.

Campos:
- id;
- userId;
- language;
- level;
- topic;
- status;
- prompts;
- responses;
- startedAt;
- finishedAt;
- feedbackSummary.

## Value Objects
- Language;
- ProficiencyLevel;
- SpeakingTopic;
- SpeakingPrompt;
- SpeakingResponse;
- SpeakingFeedback.

## Estados
- NOT_STARTED;
- IN_PROGRESS;
- FINISHED;
- CANCELLED.

## Regras de domínio
1. Uma sessão precisa de idioma, nível e tema.
2. Sessão iniciada deve possuir `startedAt`.
3. Sessão finalizada deve possuir `finishedAt`.
4. Sessão finalizada não pode receber novas respostas.
5. Sessão finalizada deve gerar atividade de aprendizagem.

## Eventos de domínio
- SpeakingSessionStarted;
- SpeakingResponseSubmitted;
- SpeakingSessionFinished.
