# Plan 005 — Writing Review

## Objetivo

Implementar a feature de escrita e revisão, permitindo que o usuário envie uma resposta curta, receba feedback estruturado e revise pontos de melhoria.

## Premissas

- A primeira versão pode usar feedback mockado ou regras simples.
- Integração real com IA deve exigir decisão explícita.
- O foco é criar o fluxo de prática, submissão, feedback e revisão.
- A UI mockada existente deve ser aproveitada.

## Documentos obrigatórios

- `docs/01-ai-contract/ai-execution-contract.md`
- `docs/02-architecture/api-contract-rules.md`
- `docs/02-architecture/clean-architecture-rules.md`
- `docs/05-features/writing-review/feature-spec.md`
- `docs/05-features/writing-review/domain-spec.md`
- `docs/05-features/writing-review/api-spec.md`
- `docs/05-features/writing-review/backend-spec.md`
- `docs/05-features/writing-review/frontend-spec.md`
- `docs/05-features/writing-review/test-spec.md`
- `docs/06-skills/fullstack/criar-feature-ponta-a-ponta/SKILL.md`

## Ordem de execução

1. Validar escopo da feature de escrita.
2. Mapear tela e mocks existentes.
3. Criar domínio de desafio de escrita.
4. Criar entidade ou agregado para submissão de escrita.
5. Criar use case de iniciar desafio, se aplicável.
6. Criar use case de submeter resposta.
7. Criar use case de gerar ou obter feedback.
8. Criar DTOs e endpoints.
9. Criar testes unitários dos casos de uso.
10. Criar testes de API.
11. Criar client API no front-end.
12. Integrar formulário de escrita com API.
13. Exibir feedback estruturado.
14. Tratar estados de carregamento, erro e vazio.

## Entregáveis esperados

- Desafio de escrita funcional.
- Submissão de resposta via API.
- Feedback exibido na UI.
- Histórico ou resumo conforme spec.
- Testes principais criados.

## Proibido

- Criar corretor gramatical real sem spec.
- Criar integração com IA sem decisão explícita.
- Fazer avaliação complexa no front-end.
- Expor prompt interno ao usuário.
- Criar feedback genérico demais sem estrutura.

## Critério de pronto

A feature estará pronta quando:

- o usuário conseguir escrever e enviar resposta;
- o backend processar a submissão;
- o feedback aparecer na tela;
- o mock, se existir, estiver isolado;
- testes principais passarem;
- a UI seguir o design system.
