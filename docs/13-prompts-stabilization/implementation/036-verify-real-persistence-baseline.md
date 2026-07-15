# 036 — Verify Real Persistence Baseline

## Objetivo
Garantir que o backend Spring Boot, banco real, migrations/schema, repositories e configuração de persistência estejam corretos antes de desmockar módulos.

## Leia apenas
1. `docs/01-ai-contract/ai-execution-contract.md`
2. `docs/02-architecture/backend-architecture.md`
3. `docs/02-architecture/database-rules.md`
4. `docs/06-skills/backend/criar-repositorio/SKILL.md`
5. `docs/06-skills/backend/criar-migration/SKILL.md`
6. `docs/10-audits/current-functional-gap-audit.md`

## Tarefa
Revise a aplicação existente e altere apenas o necessário para garantir persistência real.

Verifique e corrija:
1. configuração do datasource;
2. profiles de ambiente;
3. migrations ou schema initialization;
4. entidades JPA existentes;
5. repositories existentes;
6. services/use cases que ainda salvam apenas em memória;
7. endpoints que retornam dados fake quando já deveriam consultar banco.

## Regras
- Backend oficial: Spring Boot.
- Não criar NestJS, Prisma ou Node backend.
- Não recriar aplicação existente.
- Não trocar banco sem autorização.
- Não alterar UI neste prompt.
- Não implementar todas as features aqui.
- Corrigir apenas a base de persistência.

## Critério de pronto
- Backend sobe sem erro.
- Banco real é usado pela aplicação.
- Existe forma clara de persistir e consultar dados reais.
- Nenhum módulo principal depende obrigatoriamente de dados hardcoded no backend.
- Build/test backend passam ou os erros são documentados.
