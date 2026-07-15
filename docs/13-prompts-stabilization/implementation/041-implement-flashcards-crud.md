# 041 — Implement Flashcards CRUD

## Objetivo
Transformar flashcards de lista fixa para funcionalidade real com adicionar, revisar e excluir palavras/expressões.

## Leia apenas
1. `docs/01-ai-contract/ai-execution-contract.md`
2. `docs/05-features/flashcards/feature-spec.md`
3. `docs/05-features/flashcards/domain-spec.md`
4. `docs/05-features/flashcards/api-spec.md`
5. `docs/05-features/flashcards/backend-spec.md`
6. `docs/05-features/flashcards/frontend-spec.md`
7. `docs/06-skills/fullstack/criar-feature-ponta-a-ponta/SKILL.md`

## Backend
Implementar/ajustar:
- entidade Flashcard;
- repository;
- use cases;
- endpoints:
  - `GET /api/flashcards`
  - `POST /api/flashcards`
  - `PUT /api/flashcards/{id}` se necessário;
  - `DELETE /api/flashcards/{id}`
  - `POST /api/flashcards/{id}/review`

## Frontend
Na tela de revisão/flashcards:
- listar flashcards reais;
- adicionar palavra/expressão;
- excluir flashcard;
- marcar acerto/erro;
- mostrar estado vazio.

## Regras
- Não manter flashcards fixos como fonte principal.
- Não excluir visual atual sem necessidade.
- Não calcular progresso global no componente.

## Critério de pronto
- Usuário cria flashcard.
- Usuário exclui flashcard.
- Flashcards persistem após reload.
- Revisão registra atividade real.
