# 033 — Hardening da Primeira Versão


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

Fazer estabilização geral antes de release interno/demo.

## Execute

1. Rodar build/test/lint disponíveis.
2. Verificar rotas quebradas.
3. Verificar loading/error/empty em telas integradas.
4. Verificar CORS/config local.
5. Verificar variáveis de ambiente.
6. Verificar mensagens de erro e UX básica.
7. Corrigir apenas problemas críticos.
8. Atualizar `docs/10-audits/first-version-hardening.md`.

## Proibido

- Criar nova feature.
- Refatorar arquitetura ampla.
- Alterar identidade visual.

## Critério de pronto

- Aplicação sobe localmente.
- Fluxos principais funcionam.
- Problemas restantes estão documentados.
