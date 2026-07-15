# 020 — Revalidar Qualidade Após Correção de Stack


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

Revalidar o estado do projeto depois da correção para Spring Boot, sem criar funcionalidades novas.

## Execute

1. Identificar scripts disponíveis no projeto:
   - frontend: `package.json`
   - backend: `pom.xml`, `build.gradle` ou estrutura Spring Boot existente
2. Rodar verificações disponíveis, sem inventar comandos:
   - frontend build/lint/test, se existirem
   - backend test/build, se existir
3. Corrigir apenas problemas pequenos e diretos causados pela troca errada de stack ou inconsistências óbvias.
4. Criar ou atualizar `docs/10-audits/quality-current-state.md` com:
   - comandos executados;
   - resultados;
   - falhas;
   - correções feitas;
   - pendências.

## Proibido

- Criar feature.
- Criar backend do zero.
- Fazer refatoração ampla.
- Adicionar dependência.

## Critério de pronto

- Estado de qualidade documentado.
- Falhas críticas identificadas.
- Próximo prompt pode seguir com fundação Spring Boot real.
