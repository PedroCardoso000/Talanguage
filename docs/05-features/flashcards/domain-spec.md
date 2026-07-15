<!--
Talanguage — Feature Specification
Contexto: o front-end já possui UI mockada estruturada. O backend ainda não existe.
Objetivo: orientar a transição de telas mockadas para funcionalidades reais, sem recriar a UI do zero e sem criar decisões fora da arquitetura documentada.
-->

# Domain Spec — Flashcards

## Agregado

### Flashcard
Representa uma unidade de revisão.

Campos:
- id;
- userId;
- front;
- back;
- language;
- tags;
- difficulty;
- reviewState;
- createdAt;
- nextReviewAt.

## Value Objects
- FlashcardFront;
- FlashcardBack;
- ReviewRating;
- ReviewSchedule;
- Language.

## Regras
1. Frente e verso não podem ser vazios.
2. Card pertence a um usuário.
3. Revisão deve registrar avaliação.
4. Próxima revisão deve ser recalculada após avaliação.
5. Card revisado gera atividade de aprendizagem.

## Eventos
- FlashcardCreated;
- FlashcardReviewed.
