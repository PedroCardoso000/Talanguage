# Plan 004 — Speaking Practice

## Objetivo

Implementar a prática de fala como uma feature ponta a ponta, conectando a UI mockada a um backend preparado para sessões de conversação.

## Premissas

- A primeira versão pode usar conversa simulada ou feedback mockado.
- Integração real com IA só deve ocorrer se houver decisão explícita.
- Áudio real, transcrição e correção fonética estão fora desta etapa, salvo spec contrária.
- A UI existente deve ser preservada quando adequada.

## Documentos obrigatórios

- `docs/01-ai-contract/ai-execution-contract.md`
- `docs/02-architecture/api-contract-rules.md`
- `docs/02-architecture/clean-architecture-rules.md`
- `docs/04-domain/language-learning-domain.md`
- `docs/05-features/speaking/feature-spec.md`
- `docs/05-features/speaking/domain-spec.md`
- `docs/05-features/speaking/api-spec.md`
- `docs/05-features/speaking/backend-spec.md`
- `docs/05-features/speaking/frontend-spec.md`
- `docs/05-features/speaking/test-spec.md`
- `docs/06-skills/fullstack/criar-feature-ponta-a-ponta/SKILL.md`

## Ordem de execução

1. Validar escopo da prática de fala.
2. Mapear UI e mocks existentes da tela de fala.
3. Criar domínio de sessão de fala.
4. Criar value objects de idioma, nível e tema.
5. Criar use case de iniciar sessão.
6. Criar use case de finalizar sessão.
7. Criar repository interface.
8. Criar implementação inicial in-memory ou persistente conforme spec.
9. Criar DTOs e endpoints.
10. Criar testes unitários de domínio e casos de uso.
11. Criar testes de API.
12. Criar client API no front-end.
13. Integrar tela existente com API.
14. Substituir mocks diretos por camada de client ou service.
15. Validar estados idle, loading, in progress, finished e error.

## Entregáveis esperados

- Sessão de fala iniciada via API.
- Sessão finalizada via API.
- Tela `/speak` funcional.
- Feedback simples ou simulado conforme spec.
- Testes backend principais.
- Integração front-back concluída.

## Proibido

- Implementar IA real sem decisão explícita.
- Implementar áudio real sem spec.
- Criar WebRTC.
- Colocar regra de sessão no front-end.
- Criar prompts extensos não documentados.
- Persistir dados sensíveis desnecessários.

## Critério de pronto

A feature estará pronta quando:

- o usuário conseguir iniciar uma prática;
- o usuário conseguir finalizar uma prática;
- o resultado for exibido na UI;
- o backend controlar estado da sessão;
- os testes principais passarem;
- mocks restantes estiverem explícitos e isolados.
