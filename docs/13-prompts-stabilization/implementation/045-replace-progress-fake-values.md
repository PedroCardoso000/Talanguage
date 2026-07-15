# 045 — Replace Progress Fake Values

## Objetivo
Substituir valores irreais do módulo Progresso por dados calculados a partir de atividades reais persistidas.

## Leia apenas
1. `docs/01-ai-contract/ai-execution-contract.md`
2. `docs/05-features/progress/feature-spec.md`
3. `docs/05-features/progress/domain-spec.md`
4. `docs/05-features/progress/api-spec.md`
5. `docs/05-features/progress/backend-spec.md`
6. `docs/05-features/progress/frontend-spec.md`
7. `docs/06-skills/fullstack/integrar-frontend-backend/SKILL.md`

## Backend
Criar/ajustar endpoint:
- `GET /api/progress/summary`

Calcular com base em:
- ActivityLog;
- flashcard reviews;
- writing submissions;
- speaking sessions;
- mock test attempts;
- goals quando aplicável.

## Frontend
Substituir mocks por API client.
Exibir estado vazio honesto para usuário novo.

## Regras
- Não calcular progresso principal no frontend.
- Não exibir porcentagens sem base.
- Não usar seed fixa como se fosse dado real.

## Critério de pronto
- Progresso reflete ações reais do usuário.
- Usuário novo não vê números falsos.
- Dados persistem após reload.
