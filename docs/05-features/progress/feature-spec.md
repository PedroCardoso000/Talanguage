<!--
Talanguage — Feature Specification
Contexto: o front-end já possui UI mockada estruturada. O backend ainda não existe.
Objetivo: orientar a transição de telas mockadas para funcionalidades reais, sem recriar a UI do zero e sem criar decisões fora da arquitetura documentada.
-->

# Feature Spec — Progress

## Objetivo
Permitir que o usuário acompanhe sua evolução por consistência, atividades realizadas e habilidades praticadas.

## Contexto atual
A UI de progresso existe mockada. A implementação deve conectar os cards/gráficos a dados reais derivados das atividades.

## Escopo da primeira versão
Inclui:
- sequência de dias;
- metas diárias;
- atividades concluídas;
- progresso por habilidade;
- histórico simples;
- resumo semanal.

## Fora de escopo
- relatórios avançados;
- predição de fluência;
- certificação;
- comparação social;
- dashboards administrativos.

## Critério de pronto
- atividades dos módulos impactam progresso;
- dashboard e tela de progresso usam dados consistentes;
- estados vazios tratados;
- cálculos ficam no backend/domínio, não na UI.
