# Plan 006 — Flashcards

## Objetivo

Implementar a feature de flashcards para revisão de palavras, expressões e frases, conectando a UI existente a dados reais ou persistência inicial.

## Premissas

- Flashcards podem existir inicialmente como mocks no front-end.
- A primeira versão deve permitir revisão simples e rastreamento básico.
- Algoritmo avançado de repetição espaçada não é obrigatório sem spec explícita.

## Documentos obrigatórios

- `docs/01-ai-contract/ai-execution-contract.md`
- `docs/04-domain/language-learning-domain.md`
- `docs/05-features/flashcards/feature-spec.md`
- `docs/05-features/flashcards/domain-spec.md`
- `docs/05-features/flashcards/api-spec.md`
- `docs/05-features/flashcards/backend-spec.md`
- `docs/05-features/flashcards/frontend-spec.md`
- `docs/05-features/flashcards/test-spec.md`
- `docs/06-skills/fullstack/criar-feature-ponta-a-ponta/SKILL.md`

## Ordem de execução

1. Validar escopo de flashcards.
2. Mapear UI e mocks existentes.
3. Criar domínio de flashcard e deck, se aplicável.
4. Criar value objects de termo, tradução, exemplo e idioma.
5. Criar use case de listar flashcards.
6. Criar use case de registrar revisão.
7. Criar use case de criar flashcard, se previsto.
8. Criar repository e persistência mínima.
9. Criar endpoints e DTOs.
10. Criar testes unitários e de API.
11. Criar client API no front-end.
12. Integrar cards existentes com API.
13. Validar estados de revisão, vazio e erro.

## Entregáveis esperados

- Listagem de flashcards funcional.
- Revisão simples funcional.
- Registro básico de acerto/erro ou dificuldade, se especificado.
- UI integrada.
- Testes principais.

## Proibido

- Criar algoritmo complexo de spaced repetition sem decisão explícita.
- Criar gamificação infantilizada.
- Criar regra de revisão dentro do componente visual.
- Misturar flashcards com progresso sem contrato claro.

## Critério de pronto

A feature estará pronta quando:

- o usuário conseguir revisar flashcards;
- o backend fornecer ou persistir dados necessários;
- o front-end não depender de mock interno escondido;
- os estados principais funcionarem;
- os testes principais passarem.
