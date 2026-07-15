<!--
Talanguage — Feature Specification
Contexto: o front-end já possui UI mockada estruturada. O backend ainda não existe.
Objetivo: orientar a transição de telas mockadas para funcionalidades reais, sem recriar a UI do zero e sem criar decisões fora da arquitetura documentada.
-->

# Backend Spec — Flashcards

## Casos de uso
- ListFlashcardsUseCase;
- CreateFlashcardUseCase;
- ReviewFlashcardUseCase;
- GetFlashcardStatsUseCase.

## Portas/interfaces
- FlashcardRepository;
- ReviewSchedulePolicy;
- LearningActivityRepository.

## Regra de revisão inicial
Pode usar política simples:
- AGAIN: revisar amanhã;
- HARD: revisar em 2 dias;
- GOOD: revisar em 4 dias;
- EASY: revisar em 7 dias.

## Proibido
- implementar SRS complexo sem decisão;
- permitir acesso a cards de outro usuário;
- calcular estado de revisão no front-end.
