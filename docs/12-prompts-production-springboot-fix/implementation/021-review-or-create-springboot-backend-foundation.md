# 021 — Revisar ou Criar Fundação Backend Spring Boot


# Regras globais deste prompt

Antes de executar:

1. Leia `docs/01-ai-contract/ai-execution-contract.md`.
2. Leia `docs/01-ai-contract/token-economy-rules.md`.
3. Leia `docs/08-indexes/reading-order.md`.
4. Use apenas documentos necessários para a tarefa atual.

Regras obrigatórias:

- Não recriar aplicação existente.
- Não trocar stack.
- Backend oficial: Spring Boot.
- Alterar o menor número possível de arquivos.
- Preservar UI existente.
- Não adicionar dependências sem necessidade real.
- Não implementar funcionalidade fora das specs.
- Se já existir implementação equivalente, revisar, corrigir e reaproveitar.
- Se houver conflito entre documentação e código, documentar a decisão antes de alterar.


## Objetivo

Preparar a base backend real em Spring Boot. Se já existir aplicação backend, revisar e ajustar. Se não existir, criar a fundação mínima.

## Leia também

- `docs/02-architecture/backend-architecture.md`
- `docs/02-architecture/clean-architecture-rules.md`
- `docs/02-architecture/api-contract-rules.md`
- `docs/02-architecture/database-rules.md`
- `docs/02-architecture/auth-security-rules.md`
- `docs/02-architecture/testing-strategy.md`
- `docs/06-skills/backend/criar-entidade/SKILL.md`
- `docs/06-skills/backend/criar-caso-de-uso/SKILL.md`
- `docs/06-skills/backend/criar-endpoint/SKILL.md`

## Execute

1. Detectar se já existe backend Spring Boot.
2. Se existir:
   - não recriar;
   - mapear estrutura;
   - ajustar apenas desvios críticos.
3. Se não existir:
   - criar aplicação Spring Boot mínima em pasta adequada do projeto;
   - estruturar camadas para domain, application, infrastructure e api/web;
   - configurar health endpoint simples;
   - preparar profile local/dev;
   - preparar CORS para o frontend local;
   - preparar PostgreSQL de forma configurável, sem credenciais fixas.
4. Criar documentação de estado em `docs/10-audits/backend-current-state.md`.

## Proibido

- Implementar Auth neste prompt.
- Criar todas as features.
- Criar microserviços.
- Usar backend diferente de Spring Boot/Java/PostgreSQL como direção oficial.
- Colocar regra de negócio em controller.

## Critério de pronto

- Backend Spring Boot existe ou está revisado.
- Estrutura de camadas está clara.
- Projeto backend compila ou falhas estão documentadas.
- Existe health check ou ponto básico de verificação.
