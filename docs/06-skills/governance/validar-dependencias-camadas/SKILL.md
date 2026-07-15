# Skill — Validar Dependências entre Camadas

## Objetivo

Garantir que as dependências entre domínio, aplicação, infraestrutura, API e frontend apontem para a direção correta.

## Quando usar

Use em revisões arquiteturais e depois de criar backend ou integração full-stack.

## Documentos obrigatórios

- docs/01-ai-contract/ai-execution-contract.md
- docs/01-ai-contract/allowed-stack.md
- docs/01-ai-contract/forbidden-decisions.md
- docs/01-ai-contract/token-economy-rules.md
- docs/01-ai-contract/definition-of-done.md
- docs/08-indexes/reading-order.md
- docs/02-architecture/clean-architecture-rules.md

## Processo

1. Mapear arquivos alterados por camada.
2. Verificar imports/referências entre camadas.
3. Confirmar que domínio não depende de infraestrutura, API ou frontend.
4. Confirmar que aplicação depende de abstrações.
5. Confirmar que infraestrutura implementa portas.
6. Confirmar que frontend consome contratos/client API, não detalhes internos.
7. Listar violações e correções.

## Proibido

- Permitir domínio importar framework externo sem decisão.
- Permitir API acessar banco diretamente quando caso de uso é exigido.
- Permitir frontend conhecer entidade interna.

## Critério de pronto

- Direção de dependência está correta.
- Violações foram corrigidas ou listadas.
- Camadas permanecem substituíveis.

## Saída esperada

Ao finalizar, informe de forma objetiva:

- arquivos criados ou alterados;
- testes criados ou atualizados;
- decisões assumidas;
- lacunas documentais encontradas;
- próximos passos recomendados, se houver.
