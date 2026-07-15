# 039 — Implement Training Streak

## Objetivo
Implementar sequência de treino real baseada em atividades persistidas, removendo números fake.

## Leia apenas
1. `docs/01-ai-contract/ai-execution-contract.md`
2. `docs/04-domain/business-rules.md`
3. `docs/05-features/progress/domain-spec.md`
4. `docs/05-features/progress/backend-spec.md`
5. `docs/05-features/progress/frontend-spec.md`
6. `docs/06-skills/fullstack/criar-feature-ponta-a-ponta/SKILL.md`

## Regra de negócio mínima
- Um dia conta na sequência se o usuário concluir ao menos uma atividade válida no dia.
- Atividades válidas: writing, speaking, review, mock-test ou goal completion quando aplicável.
- Sequência quebra se houver um dia completo sem atividade.

## Backend
Criar/ajustar modelo de ActivityLog ou equivalente.
Criar serviço/use case para calcular:
- currentStreak;
- longestStreak;
- lastActivityDate.

## Frontend
Exibir sequência real no dashboard/progress onde já houver lugar visual.

## Regras
- Não calcular streak no frontend.
- Não usar número fixo.
- Não gerar porcentagem sem base de atividade real.

## Critério de pronto
- Sequência muda quando atividade real é registrada.
- Dados persistem.
- Reload não perde sequência.
