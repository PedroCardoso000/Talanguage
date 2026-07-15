<!--
Talanguage — Feature Specification
Contexto: o front-end já possui UI mockada estruturada. O backend ainda não existe.
Objetivo: orientar a transição de telas mockadas para funcionalidades reais, sem recriar a UI do zero e sem criar decisões fora da arquitetura documentada.
-->

# Feature Spec — Dashboard

## Objetivo
Dar ao usuário uma visão clara da rotina diária, progresso resumido e próximos passos de estudo.

## Contexto atual
O front-end já possui painel inicial mockado. A implementação deve preservar a estrutura visual e substituir dados estáticos por dados vindos do backend quando disponíveis.

## Valor para o usuário
O usuário entende rapidamente o que fazer hoje e como está evoluindo.

## Escopo da primeira versão
Inclui:
- saudação personalizada;
- resumo de metas diárias;
- sequência de dias;
- progresso por habilidade;
- atividades recentes;
- atalhos para módulos principais;
- recomendação simples de próxima ação.

## Fora de escopo
- analytics avançado;
- recomendação inteligente complexa;
- ranking competitivo;
- gamificação infantilizada;
- dashboards administrativos.

## Critério de pronto
- dashboard conectado ao usuário autenticado;
- dados reais ou derivados dos módulos quando disponíveis;
- fallback claro para estados vazios;
- layout consistente com a UI existente.
