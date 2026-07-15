# 047 — Stabilization Hardening Pass

## Objetivo
Fazer uma revisão final de estabilidade da primeira versão funcional após correções dos módulos principais.

## Leia apenas
1. `docs/01-ai-contract/ai-execution-contract.md`
2. `docs/01-ai-contract/definition-of-done.md`
3. `docs/02-architecture/testing-strategy.md`
4. `docs/06-skills/fullstack/revisar-feature-completa/SKILL.md`
5. `docs/13-prompts-stabilization/stabilization-status.md`

## Tarefa
Revisar o sistema inteiro e corrigir apenas problemas críticos.

Verificar:
1. build frontend;
2. build backend;
3. testes existentes;
4. rotas quebradas;
5. navegação;
6. dados mockados mortos;
7. persistência real;
8. estados loading/error/empty;
9. textos com encoding quebrado;
10. módulos com números irreais.

## Regras
- Não criar feature nova.
- Não trocar stack.
- Não fazer redesign.
- Não reestruturar projeto inteiro.
- Corrigir apenas falhas necessárias para estabilização.

## Entrega
Criar/atualizar:
`docs/10-audits/release-readiness-report.md`

O relatório deve conter:
- o que está funcional;
- o que ainda é próxima versão;
- mocks remanescentes justificados;
- riscos;
- comandos executados;
- resultado de build/test.
