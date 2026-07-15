# 046 — Community Next Version And Copy Fix

## Objetivo
Deixar o módulo Comunidade como próxima versão com tela ofuscada/preview e corrigir textos ruins ou sem pontuação no sistema.

## Leia apenas
1. `docs/01-ai-contract/ai-execution-contract.md`
2. `docs/03-design-system/ux-writing.md`
3. `docs/05-features/community/feature-spec.md`
4. `docs/05-features/community/frontend-spec.md`
5. `docs/06-skills/frontend/criar-pagina/SKILL.md`

## Community
Implementar ou ajustar tela simples:
- rota `/community`, se a navegação já apontar para ela ou se a documentação exigir;
- estado visual de “Próxima versão”;
- cards ofuscados/desabilitados;
- texto claro do valor futuro;
- sem chat real;
- sem WebSocket;
- sem matchmaking real.

## Copy fix
Corrigir frases ruins, sem pontuação ou com tom inadequado.
Exemplo a substituir:
- `Voce nao precisa de perfeicao agora;`

Usar texto corrigido:
- `Você não precisa de perfeição agora. Precisa praticar com consistência.`

## Regras
- Não implementar comunidade real.
- Não criar backend para comunidade agora.
- Não fazer redesign.
- Não alterar lógica de outros módulos.

## Critério de pronto
- Comunidade aparece como próxima versão de forma honesta.
- Textos principais possuem acentuação e pontuação corretas.
- Não há promessa de funcionalidade inexistente.
