# 042 — Fix Mock Test Interaction And Persistence

## Objetivo
Corrigir o módulo de simulado para permitir selecionar alternativas, finalizar prova, calcular resultado e persistir tentativa real.

## Leia apenas
1. `docs/01-ai-contract/ai-execution-contract.md`
2. `docs/05-features/*mock*`, se existir
3. `docs/02-architecture/api-contract-rules.md`
4. `docs/06-skills/fullstack/criar-feature-ponta-a-ponta/SKILL.md`
5. `docs/10-audits/current-functional-gap-audit.md`

## Backend
Criar/ajustar endpoints:
- `GET /api/mock-tests/current`
- `POST /api/mock-tests/attempts`
- `GET /api/mock-tests/attempts/{id}`

Persistir:
- tentativa;
- respostas selecionadas;
- pontuação;
- data;
- recomendação simples.

## Frontend
Corrigir:
- seleção de opção;
- estado visual da alternativa selecionada;
- botão finalizar;
- bloqueio de finalização sem respostas obrigatórias, se aplicável;
- tela de resultado.

## Regras
- Não deixar scoring apenas no frontend.
- Não gerar recomendação aleatória.
- Não quebrar navegação existente.

## Critério de pronto
- Usuário seleciona alternativas.
- Usuário finaliza prova.
- Resultado aparece.
- Tentativa persiste no backend.
