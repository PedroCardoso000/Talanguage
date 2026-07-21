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

6. Em Speak por texto, mensagens do usuário e respostas exibidas pelo sistema ou IA compõem o histórico privado da sessão.
7. O conteúdo persistido não pode ser retornado a outro usuário nem exposto em logs.
8. A política de áudio e transcrição permanece pendente de decisão explícita.

## Eventos de domínio
- SpeakingSessionStarted;
- SpeakingResponseSubmitted;
- SpeakingSessionFinished.
