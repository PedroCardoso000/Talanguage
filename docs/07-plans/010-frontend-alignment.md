# Frontend Alignment Plan

## Scope Read

- `docs/10-audits/frontend-current-state.md`
- `docs/01-ai-contract/ai-execution-contract.md`
- `docs/01-ai-contract/token-economy-rules.md`
- `docs/02-architecture/frontend-architecture.md`
- `docs/02-architecture/api-contract-rules.md`
- `docs/03-design-system/ui-components.md`
- `docs/08-indexes/reading-order.md`

## 1. Diagnóstico Resumido

O front-end atual já entrega uma casca visual coerente, navegável e útil para validação de UX. O problema central não é interface. O problema é fronteira arquitetural.

Hoje o cliente ainda concentra:

- autenticação fake;
- persistência local como fonte principal de verdade;
- cálculo de progresso;
- feedback de fala e escrita;
- correção de simulado;
- estimativa de nível;
- atualização de métricas compartilhadas.

Isso conflita diretamente com a documentação arquitetural, que exige contratos explícitos, API clients por feature, mocks fora da UI e remoção gradual de regras de domínio do front-end.

## 2. O Que Preservar

Preservar sem recriar:

- direção visual atual;
- rotas existentes;
- shell de navegação;
- composição geral das páginas;
- fluxo de uso já validado entre módulos;
- componentes de layout já coerentes:
  `AppShell`, `AuthShell`, `Sidebar`, `Topbar`, `PageShell`, `Logo`;
- componentes de produto já úteis:
  `ModuleCard`, `StatCard`, `ProgressChart`;
- estrutura App Router já em uso;
- persistência local apenas como suporte temporário de mock e fallback controlado.

Regra prática:

- manter a UI;
- mover responsabilidades;
- criar pontos de integração;
- reduzir ambiguidade.

## 3. O Que Ajustar

Deve ser ajustado progressivamente:

- remover regra de negócio do store global;
- parar de usar o store como backend simulado universal;
- separar estado de sessão, estado de tela e estado derivado;
- introduzir camada explícita de API client por feature;
- introduzir contracts tipados de request/response;
- concentrar mocks em local documentado;
- extrair padrões visuais estáveis que hoje estão inline nas páginas;
- preparar páginas para estados de `loading`, `empty`, `error`, `success`;
- eliminar strings, arrays e heurísticas soltas espalhadas entre páginas, store e libs;
- corrigir risco de encoding quebrado antes de avançar na integração.

## 4. Estrutura Alvo

Sem recriar o projeto, o front-end deve convergir para esta estrutura lógica:

```txt
src/
├── app/
│   ├── (auth)/
│   ├── (app)/
│   └── providers/
├── pages/
│   ├── auth/
│   ├── dashboard/
│   ├── speaking/
│   ├── writing/
│   ├── review/
│   ├── goals/
│   ├── progress/
│   └── mock-test/
├── features/
│   ├── auth/
│   │   ├── api/
│   │   ├── contracts/
│   │   ├── mocks/
│   │   ├── state/
│   │   └── components/
│   ├── dashboard/
│   ├── speaking/
│   ├── writing/
│   ├── review/
│   ├── goals/
│   ├── progress/
│   └── mock-test/
├── components/
│   ├── ui/
│   ├── layout/
│   └── shared/
├── api/
│   ├── clients/
│   └── contracts/
├── data/
│   └── mocks/
├── hooks/
├── store/
├── types/
└── lib/
```

Diretriz de migração:

- não mover tudo de uma vez;
- criar a estrutura por feature conforme o desmocking avançar;
- só extrair o que cria uma fronteira real.

## 5. Estratégia Para API Clients

Cada feature integrada deve expor um client próprio e explícito.

Formato recomendado:

```txt
src/features/{feature}/api/{feature}-api.ts
```

Responsabilidades do API client:

- chamar apenas endpoints documentados;
- traduzir request e response tipados;
- encapsular HTTP, headers e tratamento base de erro;
- expor métodos simples para a camada de UI.

Exemplos esperados:

- `auth-api.ts`
- `dashboard-api.ts`
- `speaking-api.ts`
- `writing-api.ts`
- `review-api.ts`
- `goals-api.ts`
- `progress-api.ts`
- `mock-test-api.ts`

Regra de alinhamento:

- página não chama `fetch`;
- componente visual não conhece endpoint;
- mock não entra dentro do componente.

## 6. Estratégia Para Contracts/Types

Separar com clareza:

- tipos de UI local;
- contracts de API;
- tipos derivados de exibição.

Estrutura recomendada:

```txt
src/features/{feature}/contracts/
├── requests.ts
├── responses.ts
└── index.ts
```

ou, quando fizer sentido:

```txt
src/api/contracts/{feature}.ts
```

Princípios:

- contracts devem seguir `docs/05-features/{feature}/api-spec.md`;
- frontend não deve inferir payload a partir dos mocks;
- response types devem representar o contrato da API, não o shape interno da página;
- tipos de estado local podem continuar em `src/types/` apenas enquanto forem realmente compartilhados e não forem contracts.

Separação obrigatória:

- `request DTO shape`
- `response DTO shape`
- `page view model`

## 7. Estratégia Para Mocks

Mocks devem parar de ficar espalhados entre:

- `src/data/mock-content.ts`
- `src/store/use-app-store.ts`
- `src/lib/feedback.ts`
- arrays inline em páginas e componentes

Estratégia alvo:

- mover mocks estáticos para `src/data/mocks/` ou `src/features/{feature}/mocks/`;
- deixar claro o que é fixture visual e o que é comportamento temporário;
- centralizar adapters de mock por feature;
- quando a feature for integrada, remover mock morto em vez de manter fallback silencioso.

Classificação recomendada:

- mocks de conteúdo:
  cenários, flashcards, perguntas, cards;
- mocks de resposta:
  payloads simulados de API;
- mocks de estado:
  apenas quando necessários para desenvolvimento isolado.

Regra:

- mock pode existir;
- mock oculto em store ou helper com regra de domínio, não.

## 8. Estratégia Para Estado Global/Local

Estado global atual está grande demais para a responsabilidade real do app.

Direção correta:

- manter global apenas o que atravessa várias áreas;
- mover o resto para estado local ou hook por feature.

Distribuição recomendada:

- global:
  sessão autenticada, preferências globais mínimas, hidratação de acesso;
- feature-level:
  resumo do dashboard, progresso carregado, goals carregadas, dados de fluxo por módulo quando compartilhados dentro da mesma feature;
- local de página/componente:
  formulário, seleção temporária, respostas em andamento, estado de submissão, feedback visual, navegação interna do fluxo.

Regras práticas:

- progresso não deve ser calculado no estado global;
- score não deve ser calculado no estado global;
- feedback não deve nascer no estado global;
- Zustand só deve permanecer onde houver benefício real de compartilhamento transversal.

## 9. Ordem de Execução

### Fase 1 - Preparação estrutural

1. Formalizar a estrutura alvo mínima sem mover tudo.
2. Separar layout/shared/ui components logicamente.
3. Identificar e listar padrões inline que merecem extração futura.
4. Corrigir o problema de encoding antes de aumentar a superfície de integração.

### Fase 2 - Fronteiras de integração

1. Criar convenção para `features/{feature}/api`.
2. Criar convenção para `features/{feature}/contracts`.
3. Criar convenção para `features/{feature}/mocks`.
4. Reduzir o papel de `useAppStore` a estado realmente compartilhado.

### Fase 3 - Desmocking por prioridade

1. Auth
   Primeiro porque define identidade, sessão e guarda de acesso.
2. Dashboard/progress summary
   Segundo porque várias telas dependem desse read model.
3. Goals
   Fluxo pequeno, ideal para validar padrão request/response/update.
4. Review
   Boa feature para consolidar fetch + submit + stats update.
5. Mock test
   Introduz correção remota sem a complexidade de tempo real.
6. Writing
   Requer contrato mais rico de prompt, submissão e feedback.
7. Speaking
   Deixar por último entre os módulos atuais porque concentra mais regra fake e maior chance de mudança de contrato.

### Fase 4 - Consolidação

1. Remover mocks mortos por feature integrada.
2. Garantir estados de loading/empty/error.
3. Extrair componentes estáveis reaproveitados em mais de uma tela.
4. Revisar se ainda existe regra de domínio escondida no front-end.

## 10. Critério de Pronto

O front-end estará alinhado com a arquitetura quando:

- a UI atual tiver sido preservada sem recriação desnecessária;
- cada feature pronta para integração possuir ponto claro de API client;
- contracts de request/response estiverem separados dos tipos de UI;
- mocks estiverem fora de componentes e fora de regras globais;
- o estado global estiver limitado ao que realmente precisa ser compartilhado;
- páginas estiverem menores e focadas em composição;
- regras de progresso, scoring, recomendação e feedback não estiverem mais definidas no cliente;
- cada tela integrada suportar `loading`, `empty`, `validation error`, `backend error` e `unexpected error`;
- o desmocking puder ocorrer por feature sem reescrever o shell inteiro;
- nenhuma nova dependência ou nova rota tiver sido criada fora do que a documentação permite.

## Resumo Final

O alinhamento não pede redesenho. Pede disciplina arquitetural. A prioridade é transformar o front-end atual de um mock acoplado a regras locais em uma casca estável, com fronteiras claras para contratos, API clients, mocks e estado. Se isso não for feito antes do desmocking real, o backend vai entrar em cima de uma base visual boa, mas estruturalmente confusa.
