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

## Infraestrutura inicial
- repositório persistente ou in-memory conforme decisão de banco;
- provider simples de prompts/feedback;
- endpoints protegidos por autenticação.
