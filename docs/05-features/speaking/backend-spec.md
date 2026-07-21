<!--
Talanguage — Feature Specification
Contexto: o front-end já possui UI mockada estruturada. O backend ainda não existe.
Objetivo: orientar a transição de telas mockadas para funcionalidades reais, sem recriar a UI do zero e sem criar decisões fora da arquitetura documentada.
-->

# Backend Spec — Speaking Practice

## Casos de uso
- ListSpeakingTopicsUseCase;
- StartSpeakingSessionUseCase;
- SubmitSpeakingResponseUseCase;
- FinishSpeakingSessionUseCase.

## Repositórios/portas
- SpeakingSessionRepository;
- SpeakingTopicRepository;
- LearningActivityRepository;
- SpeakingFeedbackProvider.

## Regras
- feedback pode ser mockado/heurístico no backend na primeira versão;
- não colocar geração de prompt no controller;
- integração real com IA deve ser isolada atrás de `SpeakingFeedbackProvider` ou serviço equivalente;
- finalizar sessão deve registrar atividade para progresso.

- persistir, no fluxo textual, as mensagens do usuário, respostas do sistema ou IA, feedback, métricas e timestamps necessários ao histórico funcional;
- toda consulta ou mutação de sessão deve validar o proprietário autenticado;
- conteúdo de conversa não pode aparecer em logs;
- retenção, exclusão, uso para treinamento e compartilhamento com IA seguem o ADR de persistência de conteúdo privado;
- não persistir áudio ou transcrição até a política correspondente ser decidida.

## Infraestrutura inicial
- repositório persistente ou in-memory conforme decisão de banco;
- provider simples de prompts/feedback;
- endpoints protegidos por autenticação.
