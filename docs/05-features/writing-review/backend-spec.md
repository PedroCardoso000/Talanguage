<!--
Talanguage — Feature Specification
Contexto: o front-end já possui UI mockada estruturada. O backend ainda não existe.
Objetivo: orientar a transição de telas mockadas para funcionalidades reais, sem recriar a UI do zero e sem criar decisões fora da arquitetura documentada.
-->

# Backend Spec — Writing Review

## Casos de uso
- ListWritingChallengesUseCase;
- SubmitWritingAnswerUseCase;
- ReviewWritingSubmissionUseCase;
- ListRecentWritingSubmissionsUseCase.

## Portas/interfaces
- WritingChallengeRepository;
- WritingSubmissionRepository;
- WritingFeedbackProvider;
- LearningActivityRepository.

## Regras
- feedback inicial pode ser heurístico/mockado no backend;
- não chamar IA diretamente do controller;
- integração real com IA deve ficar atrás de provider;
- submissão revisada registra atividade.

## Proibido
- validação pesada no front-end como regra única;
- feedback fake dentro da tela;
- misturar feedback provider com repositório.
