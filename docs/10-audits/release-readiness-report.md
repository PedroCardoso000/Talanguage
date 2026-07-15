# Release Readiness Report

## Status geral
A primeira versao funcional esta pronta para execucao local com frontend e backend integrados nos modulos principais.

## O que esta funcional
- autenticacao real via backend para login, cadastro e sessao atual;
- dashboard conectado ao backend com streak, atividade semanal e recomendacao;
- modulo Falar com fluxo text-first, persistencia de sessao e feedback heuristico honesto;
- modulo Escrever com desafio atual, submissao real e feedback textual;
- modulo Revisar com CRUD de flashcards, revisao persistida e estatisticas reais;
- modulo Metas com persistencia real de configuracoes;
- modulo Progresso com summary, atividades e weekly summary vindos do backend;
- modulo Simulado com tentativa real, correcao, persistencia e resultado final;
- modulo Perfil e Notificacoes nas rotas ja existentes;
- rota Comunidade tratada como preview bloqueado de proxima versao, sem prometer funcionalidade inexistente.

## O que ainda e proxima versao
- comunidade real com grupos, parceiros e registro de interesse na UI;
- chat, video, feed social e matchmaking;
- nivel estimado oficial com regra de produto e backend dedicados;
- persistencia de conteudo bruto de speaking e writing, que segue fora da primeira versao.

## Mocks remanescentes justificados
- feedback de speaking e writing continua heuristico no backend, nao por IA real;
- preview da comunidade usa dados estaticos apenas para ilustrar a futura proposta do modulo bloqueado;
- estado local em Zustand continua existindo para sessao do usuario, metas locais auxiliares e pequenos espelhos de UX, mas os numeros principais de dashboard e progresso nao dependem mais disso.

## Riscos remanescentes
- existem arquivos legados de frontend da comunidade que nao sao mais usados pela rota atual; isso nao bloqueia a release, mas aumenta ruido de manutencao;
- parte da documentacao historica fora dos arquivos ajustados nesta etapa ainda usa contexto antigo de "frontend mockado", o que pode confundir futuras automacoes se a leitura nao for restrita;
- o store local ainda acumula alguns contadores auxiliares usados por hooks de treino; hoje isso nao dirige os read models principais, mas merece limpeza futura para reduzir duplicidade conceitual.

## Correcoes criticas aplicadas no hardening
- correção de strings com mojibake visivel no store;
- substituicao de percentual arbitrario de flashcards por taxa de acerto real;
- ajuste de copy visivel quebrado na tela de revisao;
- confirmacao de que nao restaram textos com encoding quebrado em `src/`.

## Comandos executados
- `npm run lint`
- `npm run build`
- `mvn test`
- `rg -n 'Ã|â€”|â€¢|concluÃ|IntermediÃ|BÃ¡sico|NÃ£o|ApresentaÃ|OlÃ¡' src`

## Resultado
- frontend lint: OK
- frontend build: OK
- backend tests: OK
- textos com encoding quebrado em `src`: nenhum encontrado

## Conclusao
O sistema esta apto para rodar localmente como primeira versao funcional estabilizada. Os bloqueios restantes estao concentrados em escopo futuro, nao em falhas criticas da release atual.
