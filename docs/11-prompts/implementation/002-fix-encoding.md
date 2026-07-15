# Prompt 002 — Corrigir Encoding/Mojibake

## Objetivo

Corrija textos corrompidos por encoding no código fonte do front-end.

## Contexto

A auditoria identificou textos como `IntermediÃ¡rio`, `ApresentaÃ§Ã£o` e `OlÃ¡`.

## Leia antes

1. `docs/01-ai-contract/ai-execution-contract.md`
2. `docs/07-plans/010-frontend-alignment.md`

## Regras

- Corrigir apenas textos corrompidos.
- Não refatorar componentes.
- Não alterar lógica.
- Não alterar rotas.
- Não alterar estrutura de estado.
- Não trocar textos que já estejam corretos.
- Não mudar identidade visual.

## Validação

Busque ocorrências comuns de mojibake e rode:

```bash
npm run lint
npm run build
```

## Entrega esperada

Informe:

1. arquivos alterados;
2. termos corrigidos;
3. validações executadas;
4. qualquer texto suspeito que tenha permanecido.
