# 043 — Make Writing Functional With Realistic Feedback

## Objetivo
Tornar o módulo Escrever funcional com feedback simples, honesto e persistido, removendo números irreais.

## Leia apenas
1. `docs/01-ai-contract/ai-execution-contract.md`
2. `docs/05-features/writing-review/feature-spec.md`
3. `docs/05-features/writing-review/domain-spec.md`
4. `docs/05-features/writing-review/api-spec.md`
5. `docs/05-features/writing-review/backend-spec.md`
6. `docs/05-features/writing-review/frontend-spec.md`
7. `docs/06-skills/fullstack/criar-feature-ponta-a-ponta/SKILL.md`

## Backend
Implementar/ajustar:
- challenge de escrita;
- submissão de resposta;
- feedback simples por heurística documentada;
- persistência da tentativa.

Endpoints sugeridos:
- `GET /api/writing/challenges/current`
- `POST /api/writing/submissions`

## Feedback permitido na primeira versão
Sem IA real obrigatória.
Usar critérios simples e explicáveis:
- texto vazio;
- quantidade mínima de palavras;
- presença de pontuação;
- clareza básica;
- sugestão textual fixa por regra.

## Proibido
- Score aleatório.
- Porcentagem sem critério.
- Gráfico artificial após cada texto.
- Fingir correção gramatical avançada sem IA real.

## Frontend
Exibir feedback como:
- pontos fortes;
- pontos de melhoria;
- próxima ação;
- sem números fantasiosos.

## Critério de pronto
- Usuário envia texto.
- Backend registra submissão.
- Feedback é previsível e explicável.
- Progresso registra atividade real.
