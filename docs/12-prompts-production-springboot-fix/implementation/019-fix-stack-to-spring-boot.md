# 019 — Corrigir Stack Oficial para Spring Boot


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


## Contexto

A trilha anterior pode ter registrado backend como NestJS/Prisma indevidamente. A stack correta do projeto é **Spring Boot**.

## Objetivo

Corrigir a documentação e qualquer artefato de prompt/planejamento que tenha fixado NestJS, Prisma ou Node como backend oficial.

## Leia também

- `docs/01-ai-contract/allowed-stack.md`
- `docs/01-ai-contract/forbidden-decisions.md`
- `docs/02-architecture/backend-architecture.md`
- `docs/02-architecture/database-rules.md`
- `docs/02-architecture/testing-strategy.md`
- `docs/12-prompts-production/implementation/019-freeze-backend-stack.md`, se existir
- `docs/12-prompts-production/implementation/020-fix-critical-quality.md`, se existir

## Execute

1. Procurar referências a NestJS, Prisma, Express ou Node como backend oficial.
2. Substituir por Spring Boot quando estiverem como decisão oficial.
3. Atualizar `allowed-stack.md` para deixar claro:
   - Backend: Spring Boot
   - Linguagem: Java
   - API: REST
   - Persistência: Spring Data JPA
   - Banco: PostgreSQL
   - Migrations: Flyway, se aplicável
   - Testes: JUnit 5, Mockito, MockMvc e Testcontainers quando necessário
4. Atualizar `backend-architecture.md` para Spring Boot, sem reescrever tudo.
5. Atualizar `database-rules.md` se tiver menção incompatível.
6. Atualizar prompts de produção existentes que apontem para NestJS, se necessário.
7. Criar um pequeno registro em `docs/10-audits/backend-stack-correction.md` explicando o que foi corrigido.

## Proibido

- Criar backend agora.
- Refatorar frontend.
- Apagar documentação útil.
- Trocar frontend.
- Criar arquitetura nova fora do que já existe.

## Critério de pronto

- Nenhum documento trata NestJS/Prisma como stack oficial.
- Spring Boot está definido como backend oficial.
- Existe registro da correção em `docs/10-audits/backend-stack-correction.md`.
