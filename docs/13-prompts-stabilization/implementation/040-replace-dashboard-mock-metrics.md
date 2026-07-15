# 040 — Replace Dashboard Mock Metrics

## Objetivo
Substituir gráficos e percentuais mockados da tela inicial por dados reais derivados do backend.

## Leia apenas
1. `docs/01-ai-contract/ai-execution-contract.md`
2. `docs/05-features/dashboard/api-spec.md`
3. `docs/05-features/dashboard/backend-spec.md`
4. `docs/05-features/dashboard/frontend-spec.md`
5. `docs/05-features/progress/api-spec.md`
6. `docs/06-skills/fullstack/integrar-frontend-backend/SKILL.md`

## Backend
Criar/ajustar endpoint:
- `GET /api/dashboard/summary`

Resposta deve conter apenas métricas calculáveis:
- completedActivitiesToday;
- weeklyActivityCount;
- goalCompletionPercent;
- moduleProgress baseado em atividades reais;
- currentStreak;
- recommendedNextAction.

## Frontend
Dashboard deve consumir API client.
Adicionar estados:
- loading;
- empty;
- error;
- success.

## Regras
- Não exibir porcentagens irreais.
- Se não houver dados, mostrar estado vazio honesto.
- Não calcular métrica principal no frontend.
- Não remover o visual atual sem necessidade.

## Critério de pronto
- Dashboard não depende de mocks para métricas principais.
- Percentuais têm origem documentada.
- Usuário novo vê estado vazio coerente.
