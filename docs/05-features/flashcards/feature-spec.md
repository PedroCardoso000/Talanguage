<!--
Talanguage — Feature Specification
Contexto: o front-end já possui UI mockada estruturada. O backend ainda não existe.
Objetivo: orientar a transição de telas mockadas para funcionalidades reais, sem recriar a UI do zero e sem criar decisões fora da arquitetura documentada.
-->

# Feature Spec — Flashcards

## Objetivo
Permitir que o usuário revise palavras, expressões e frases relevantes para fixação de vocabulário.

## Contexto atual
O front-end possui UI mockada de cards/revisão. A implementação deve conectar esse fluxo a dados reais do usuário.

## Escopo da primeira versão
Inclui:
- listar flashcards do usuário;
- criar flashcard manualmente;
- revisar flashcard;
- marcar nível de lembrança;
- atualizar próxima revisão;
- exibir estatísticas simples.

## Fora de escopo
- algoritmo SRS avançado;
- importação em massa;
- decks públicos;
- marketplace de cards;
- áudio/pronúncia automática.

## Critério de pronto
- usuário consegue criar e revisar cards;
- revisão atualiza estado do card;
- dados persistidos;
- atividade impacta progresso.
