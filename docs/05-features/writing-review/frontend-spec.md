<!--
Talanguage - Feature Specification
Contexto: o front-end ja possui UI mockada estruturada. O backend ainda nao existe.
Objetivo: orientar a transicao de telas mockadas para funcionalidades reais, sem recriar a UI do zero e sem criar decisoes fora da arquitetura documentada.
-->

# Frontend Spec - Writing Review

## Rota
`/write-review`

## Componentes esperados
- WritingReviewPage;
- WritingChallengeSelector;
- WritingEditor;
- SubmitWritingButton;
- WritingFeedbackCard;
- RecentWritingSubmissions.

## Client API
`writingApiClient` com:
- getChallenges(filters);
- getCurrentChallenge();
- submitAnswer(payload);
- getRecentSubmissions().

## Estados de UI
- loadingChallenges;
- writing;
- submitting;
- reviewed;
- error;
- empty.

## Regras
- nao inserir feedback fixo no componente;
- preservar layout mockado existente;
- editor deve ser simples;
- feedback deve ser claro e acionavel;
- nao exibir score, porcentagem ou grafico artificial como se fosse correcao precisa.
