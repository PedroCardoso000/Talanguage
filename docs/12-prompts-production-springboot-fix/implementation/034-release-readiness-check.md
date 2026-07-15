# 034 — Release Readiness Check


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

Validar se o Tala Language está pronto para primeira demonstração funcional.

## Execute

1. Criar checklist em `docs/10-audits/release-readiness.md`.
2. Validar fluxos:
   - register/login;
   - dashboard;
   - goals;
   - review/flashcards;
   - writing;
   - speaking texto;
   - progress;
   - community simples.
3. Validar backend Spring Boot:
   - sobe localmente;
   - conecta banco ou profile local configurado;
   - endpoints principais respondem.
4. Validar frontend:
   - navegação;
   - integração API;
   - estados loading/error.
5. Listar bloqueadores e não-bloqueadores.

## Proibido

- Corrigir tudo neste prompt.
- Criar feature nova.

## Critério de pronto

- Documento de readiness criado.
- Estado real da primeira versão está claro.
- Próxima release pode ser planejada com base em fatos, não achismo.
