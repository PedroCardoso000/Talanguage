<!--
Talanguage — Feature Specification
Contexto: o front-end já possui UI mockada estruturada. O backend ainda não existe.
Objetivo: orientar a transição de telas mockadas para funcionalidades reais, sem recriar a UI do zero e sem criar decisões fora da arquitetura documentada.
-->

# Domain Spec — Writing Review

## Agregados

### WritingChallenge
Representa um desafio de escrita disponível.

Campos:
- id;
- language;
- level;
- prompt;
- expectedSkill;
- active.

### WritingSubmission
Representa uma resposta enviada pelo usuário.

Campos:
- id;
- userId;
- challengeId;
- content;
- status;
- feedback;
- submittedAt;
- reviewedAt.

## Value Objects
- WritingPrompt;
- WrittenAnswer;
- WritingFeedback;
- Language;
- ProficiencyLevel.

## Regras
1. Resposta não pode ser vazia.
2. Resposta deve pertencer a um desafio existente.
3. Submission revisada não deve ser revisada novamente sem nova versão.
4. Feedback deve conter pelo menos resumo e sugestões.
5. Revisão concluída gera atividade de aprendizagem.

## Eventos
- WritingSubmissionCreated;
- WritingSubmissionReviewed.
