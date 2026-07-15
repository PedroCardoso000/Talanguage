<!--
Talanguage — Feature Specification
Contexto: o front-end já possui UI mockada estruturada. O backend ainda não existe.
Objetivo: orientar a transição de telas mockadas para funcionalidades reais, sem recriar a UI do zero e sem criar decisões fora da arquitetura documentada.
-->

# Feature Spec — Speaking Practice

## Objetivo
Permitir que o usuário inicie e finalize uma prática de conversação em inglês ou espanhol, com tema, nível e feedback simples.

## Contexto atual
A aba/tela de Falar já existe mockada. A implementação deve conectar a UI existente a um fluxo real ou inicialmente simulado pelo backend.

## Valor para o usuário
O usuário sai do estudo passivo e pratica conversação de forma guiada.

## Escopo da primeira versão
Inclui:
- selecionar idioma;
- selecionar nível;
- selecionar tema;
- iniciar sessão de prática;
- exibir prompt/mensagens da prática;
- registrar resposta textual ou simulação de fala;
- finalizar sessão;
- receber feedback simples;
- salvar atividade no progresso.

## Fora de escopo
- áudio real;
- transcrição real;
- WebRTC;
- chamada com humano;
- correção fonética;
- IA real sem decisão explícita de custo e segurança.

## Critério de pronto
- fluxo funcional ponta a ponta;
- backend registra sessão;
- frontend deixa de depender de mock local para sessão;
- feedback simples retornado pelo backend;
- atividade impacta progresso.
