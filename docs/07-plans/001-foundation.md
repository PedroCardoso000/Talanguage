# Plan 001 — Foundation

## Objetivo

Estabelecer a base técnica da primeira versão do Talanguage sem recriar o front-end existente.

O front-end atual já possui UI estruturada e dados mockados. Este plano deve orientar a estabilização da estrutura, a preparação para integração com backend e a criação da base mínima para evolução full-stack.

## Premissas

- O front-end já existe.
- A UI não deve ser reescrita do zero.
- Os dados mockados devem ser preservados temporariamente.
- O backend ainda não existe.
- A documentação deve orientar a transição progressiva de mock para API real.
- A primeira versão deve manter simplicidade técnica e clareza arquitetural.

## Documentos obrigatórios

- `docs/00-product/product-context.md`
- `docs/00-product/first-version-scope.md`
- `docs/01-ai-contract/ai-execution-contract.md`
- `docs/01-ai-contract/allowed-stack.md`
- `docs/01-ai-contract/definition-of-done.md`
- `docs/02-architecture/system-overview.md`
- `docs/02-architecture/frontend-architecture.md`
- `docs/02-architecture/backend-architecture.md`
- `docs/02-architecture/clean-architecture-rules.md`
- `docs/08-indexes/reading-order.md`

## Ordem de execução

1. Validar estrutura atual do front-end.
2. Identificar páginas, componentes e mocks já existentes.
3. Mapear mocks para futuros contratos de API.
4. Criar ou ajustar estrutura de pastas para integração com backend.
5. Definir camada de client API no front-end.
6. Definir estrutura inicial do backend.
7. Criar configuração base de testes.
8. Criar configuração base de lint/format, se ainda não existir.
9. Garantir que o front-end continue funcionando com mocks.
10. Registrar pendências técnicas para desmock progressivo.

## Entregáveis esperados

- Estrutura frontend estabilizada.
- Mapa inicial de mocks existentes.
- Estrutura backend inicial preparada.
- Base para contratos de API.
- Base para testes.
- Nenhuma tela recriada sem necessidade.

## Proibido

- Recriar a UI inteira.
- Remover mocks antes de existir API equivalente.
- Criar backend sem seguir arquitetura definida.
- Criar features novas nesta fase.
- Adicionar dependências sem necessidade documentada.
- Implementar IA real nesta fase.

## Critério de pronto

A fundação estará pronta quando:

- o front-end continuar executando;
- a estrutura de pastas estiver coerente com a arquitetura;
- existir caminho claro para substituir mocks por API;
- o backend tiver base inicial definida;
- não houver decisão técnica fora da documentação;
- as próximas features puderem seguir specs e skills sem depender de adivinhação.
