# 031 — Remover Mocks Mortos


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

Remover mocks que ficaram obsoletos depois das integrações reais, sem quebrar fixtures necessárias para dev/test.

## Execute

1. Mapear mocks em `src/data`, `src/features/**/mocks`, store e libs.
2. Identificar mocks substituídos por API real.
3. Remover ou isolar como fixtures de teste/dev.
4. Garantir que páginas integradas não dependam de mock morto.
5. Atualizar documentação se necessário.

## Proibido

- Remover fixture ainda usada por teste.
- Quebrar tela não integrada.
- Refatorar feature fora do escopo.

## Critério de pronto

- Mocks mortos removidos.
- Mocks restantes têm finalidade clara.
- Build/test não quebram.
