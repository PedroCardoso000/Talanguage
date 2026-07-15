<!--
Talanguage - Feature Specification
Contexto: o front-end ja possui UI mockada estruturada. O backend ainda nao existe.
Objetivo: orientar a transicao de telas mockadas para funcionalidades reais, sem recriar a UI do zero e sem criar decisoes fora da arquitetura documentada.
-->

# Frontend Spec - Speaking Practice

## Rota
`/speak`

## Contexto atual
A UI do modulo Falar ja existe com dados mockados. A tarefa e preservar o visual e trocar os dados por chamadas ao client API.

## Componentes esperados
- SpeakingPage;
- SpeakingSetupCard;
- LanguageSelect;
- LevelSelect;
- TopicSelect;
- ConversationPanel;
- ResponseInput;
- SpeakingSummaryCard;
- RecentSpeakingSessions.

## Client API
`speakingApiClient` com:
- getTopics();
- startSession(payload);
- submitResponse(sessionId, payload);
- finishSession(sessionId).

## Estados de UI
- idle;
- loadingTopics;
- starting;
- inProgress;
- submittingResponse;
- finishing;
- finished;
- error.

## Regras
- nao simular sessao dentro do componente apos integracao;
- nao implementar audio real;
- nao chamar IA diretamente pelo front-end;
- feedback vem do backend;
- nao exibir score, pronuncia, fluencia ou clareza como se fossem medidas reais.
