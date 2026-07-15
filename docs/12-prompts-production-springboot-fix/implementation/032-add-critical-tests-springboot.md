# 032 — Adicionar Testes Críticos Spring Boot e Frontend


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

Adicionar testes essenciais para proteger os fluxos funcionais implementados.

## Execute

1. Identificar scripts/frameworks existentes.
2. Backend Spring Boot:
   - testes unitários de use cases críticos;
   - testes de API com MockMvc ou equivalente;
   - testes de persistência quando necessário.
3. Frontend:
   - testes dos fluxos críticos que já tenham estrutura de teste disponível;
   - não adicionar framework novo sem necessidade.
4. Priorizar Auth, Goals, Review, Writing e Speaking texto.

## Proibido

- Criar testes frágeis só para aumentar cobertura.
- Alterar lógica para agradar teste.
- Adicionar dependências sem necessidade.

## Critério de pronto

- Testes críticos existem.
- Comandos de teste documentados.
- Falhas conhecidas documentadas.
