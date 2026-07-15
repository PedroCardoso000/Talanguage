<!--
Talanguage — Feature Specification
Contexto: o front-end já possui UI mockada estruturada. O backend ainda não existe.
Objetivo: orientar a transição de telas mockadas para funcionalidades reais, sem recriar a UI do zero e sem criar decisões fora da arquitetura documentada.
-->

# Frontend Spec — Flashcards

## Rota
`/flashcards`

## Componentes esperados
- FlashcardsPage;
- FlashcardList;
- FlashcardReviewCard;
- CreateFlashcardForm;
- ReviewRatingButtons;
- FlashcardStatsCard;
- EmptyFlashcardsState.

## Client API
`flashcardsApiClient` com:
- list();
- create(payload);
- review(id, payload);
- getStats().

## Estados de UI
- loading;
- listing;
- creating;
- reviewing;
- empty;
- error.

## Regras
- não calcular próxima revisão no componente;
- preservar UI mockada;
- esconder verso até ação do usuário;
- revisar apenas cards do usuário autenticado.
