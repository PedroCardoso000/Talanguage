# Skill — Criar DTO

## Objetivo

Criar DTOs explícitos para entrada e saída de API ou aplicação, sem vazar modelos internos.

## Quando usar

Use ao criar endpoints, clients, contratos ou respostas de casos de uso.

## Documentos obrigatórios

- docs/01-ai-contract/ai-execution-contract.md
- docs/01-ai-contract/allowed-stack.md
- docs/01-ai-contract/forbidden-decisions.md
- docs/02-architecture/backend-architecture.md
- docs/02-architecture/clean-architecture-rules.md
- docs/04-domain/language-learning-domain.md
- docs/04-domain/business-rules.md
- docs/05-features/{feature}/domain-spec.md
- docs/05-features/{feature}/backend-spec.md
- docs/02-architecture/api-contract-rules.md
- docs/05-features/{feature}/api-spec.md

## Processo

1. Identificar payloads esperados na api-spec.
2. Criar DTOs com nomes específicos da operação.
3. Adicionar validações de formato no limite apropriado.
4. Mapear DTO para input do caso de uso.
5. Mapear output do caso de uso para response DTO.
6. Evitar reutilização indevida entre request, response, entidade e persistência.

## Proibido

- Usar entidade como DTO.
- Criar DTO genérico para tudo.
- Colocar comportamento de domínio dentro do DTO.
- Vazar campo interno ou sensível.

## Critério de pronto

- DTO representa contrato externo claro.
- Não expõe entidade ou modelo de banco.
- Payload bate com api-spec.
- Nomes são específicos e legíveis.

## Saída esperada

Ao finalizar, informe de forma objetiva:

- arquivos criados ou alterados;
- testes criados ou atualizados;
- decisões assumidas;
- lacunas documentais encontradas;
- próximos passos recomendados, se houver.
