# Skill — Criar Client API

## Objetivo

Criar client de API no frontend para isolar chamadas HTTP e contratos com backend.

## Quando usar

Use sempre que uma tela precisar consumir endpoint real ou preparar transição de mock para backend.

## Documentos obrigatórios

- docs/01-ai-contract/ai-execution-contract.md
- docs/01-ai-contract/allowed-stack.md
- docs/01-ai-contract/forbidden-decisions.md
- docs/02-architecture/frontend-architecture.md
- docs/03-design-system/ui-components.md
- docs/03-design-system/layout-rules.md
- docs/05-features/{feature}/frontend-spec.md
- docs/02-architecture/api-contract-rules.md
- docs/05-features/{feature}/api-spec.md

## Processo

1. Ler api-spec da feature.
2. Criar funções por caso de uso/endpoints.
3. Tipar request e response.
4. Centralizar base URL e tratamento comum conforme arquitetura frontend.
5. Mapear erros para formato consumível pela UI.
6. Substituir mock progressivamente sem alterar componente visual.
7. Criar testes ou mocks de client quando aplicável.

## Proibido

- Chamar endpoint diretamente em vários componentes.
- Divergir do contrato da api-spec.
- Criar client genérico opaco.
- Expor detalhes de HTTP para componente UI.

## Critério de pronto

- Client cobre endpoints da spec.
- Tipos refletem contrato.
- Componentes consomem client/hook, não HTTP direto.
- Transição mock → backend fica localizada.

## Saída esperada

Ao finalizar, informe de forma objetiva:

- arquivos criados ou alterados;
- testes criados ou atualizados;
- decisões assumidas;
- lacunas documentais encontradas;
- próximos passos recomendados, se houver.
