# Skill — Criar Repositório

## Objetivo

Criar interface e implementação de repositório para persistência de agregados ou consultas necessárias.

## Quando usar

Use quando um caso de uso precisar salvar, recuperar ou consultar dados.

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
- docs/02-architecture/database-rules.md

## Processo

1. Definir a interface no limite correto da aplicação/domínio conforme arquitetura.
2. Criar métodos orientados ao caso de uso, não CRUD genérico automático.
3. Implementar versão in-memory quando a feature ainda estiver em transição de mock.
4. Implementar versão persistente apenas quando database-rules permitir.
5. Mapear modelos de persistência sem vazar estrutura do banco para domínio.
6. Criar testes de integração quando houver persistência real.

## Proibido

- Criar repositório genérico sem necessidade.
- Expor query de banco para o domínio.
- Criar método que mistura responsabilidade de múltiplos agregados.
- Persistir dados sensíveis sem regra de segurança.

## Critério de pronto

- Interface expressa necessidade da aplicação.
- Implementação não vaza infraestrutura.
- Testes cobrem consultas principais.
- Estratégia in-memory ou persistente está explícita.

## Saída esperada

Ao finalizar, informe de forma objetiva:

- arquivos criados ou alterados;
- testes criados ou atualizados;
- decisões assumidas;
- lacunas documentais encontradas;
- próximos passos recomendados, se houver.
