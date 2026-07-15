# Skill — Criar Formulário

## Objetivo

Criar formulário de frontend com validação, estados e integração controlada com API ou mock adapter.

## Quando usar

Use em login, cadastro, criação de prática, escrita, flashcards ou qualquer entrada do usuário.

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

1. Ler frontend-spec e api-spec.
2. Definir campos, validações e mensagens.
3. Manter validação de UI separada da regra de domínio.
4. Criar submit chamando client API ou adapter documentado.
5. Tratar loading, sucesso e erro.
6. Evitar enviar dados fora do contrato.
7. Criar testes de interação quando previsto.

## Proibido

- Validar regra de negócio exclusiva no frontend.
- Chamar fetch diretamente dentro de componente profundo se houver client API.
- Criar campos não previstos.
- Expor mensagem técnica ao usuário.

## Critério de pronto

- Campos seguem spec.
- Payload segue api-spec.
- Estados de submit estão tratados.
- Mensagens são claras e aderentes a ux-writing.

## Saída esperada

Ao finalizar, informe de forma objetiva:

- arquivos criados ou alterados;
- testes criados ou atualizados;
- decisões assumidas;
- lacunas documentais encontradas;
- próximos passos recomendados, se houver.
