# Skill — Criar Testes de Integração Backend

## Objetivo

Criar testes de integração para endpoints, persistência e contratos entre camadas quando a feature já possuir backend real.

## Quando usar

Use quando houver endpoint, banco, autenticação ou integração entre infraestrutura e aplicação.

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
- docs/02-architecture/testing-strategy.md
- docs/05-features/{feature}/api-spec.md
- docs/05-features/{feature}/test-spec.md

## Processo

1. Identificar fluxos críticos na test-spec.
2. Preparar ambiente de teste conforme testing-strategy.
3. Testar endpoint com request/response reais.
4. Validar status HTTP, payload e erros.
5. Validar persistência quando aplicável.
6. Limpar dados entre testes.
7. Evitar dependência de serviços externos não controlados.

## Proibido

- Usar serviço externo real sem controle.
- Criar teste lento sem necessidade.
- Testar fluxo não previsto na feature.
- Ignorar contrato da api-spec.

## Critério de pronto

- Fluxos principais de API testados.
- Persistência validada quando existir.
- Testes são reprodutíveis.
- Contrato HTTP está protegido.

## Saída esperada

Ao finalizar, informe de forma objetiva:

- arquivos criados ou alterados;
- testes criados ou atualizados;
- decisões assumidas;
- lacunas documentais encontradas;
- próximos passos recomendados, se houver.
