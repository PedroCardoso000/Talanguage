# Skill — Criar Componente UI

## Objetivo

Criar componente visual reutilizável, pequeno e consistente com o design system.

## Quando usar

Use quando uma tela precisar de um elemento visual repetível ou quando houver duplicação clara.

## Documentos obrigatórios

- docs/01-ai-contract/ai-execution-contract.md
- docs/01-ai-contract/allowed-stack.md
- docs/01-ai-contract/forbidden-decisions.md
- docs/02-architecture/frontend-architecture.md
- docs/03-design-system/ui-components.md
- docs/03-design-system/layout-rules.md
- docs/05-features/{feature}/frontend-spec.md

## Processo

1. Verificar se componente semelhante já existe.
2. Definir responsabilidade única.
3. Criar props explícitas e tipadas.
4. Manter componente sem regra de negócio.
5. Garantir acessibilidade básica.
6. Evitar estilização fora do padrão visual.
7. Criar exemplo de uso ou teste quando relevante.

## Proibido

- Duplicar componente existente.
- Criar componente genérico demais sem uso.
- Acoplar componente a API.
- Colocar regra de produto no componente UI.
- Usar estilo fora do design system.

## Critério de pronto

- Componente tem responsabilidade clara.
- Props são simples e tipadas.
- Visual segue design system.
- Pode ser reutilizado sem conhecer a feature.

## Saída esperada

Ao finalizar, informe de forma objetiva:

- arquivos criados ou alterados;
- testes criados ou atualizados;
- decisões assumidas;
- lacunas documentais encontradas;
- próximos passos recomendados, se houver.
