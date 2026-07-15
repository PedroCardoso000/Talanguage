# 037 — Implement Profile Management

## Objetivo
Criar ou completar a aba de perfil para permitir alteração de atributos do usuário e foto/avatar, persistindo no backend Spring Boot.

## Leia apenas
1. `docs/01-ai-contract/ai-execution-contract.md`
2. `docs/02-architecture/frontend-architecture.md`
3. `docs/02-architecture/backend-architecture.md`
4. `docs/02-architecture/api-contract-rules.md`
5. `docs/06-skills/fullstack/criar-feature-ponta-a-ponta/SKILL.md`
6. `docs/06-skills/frontend/criar-formulario/SKILL.md`
7. `docs/10-audits/current-functional-gap-audit.md`

## Escopo
Implementar perfil simples:
- nome;
- email somente leitura, se auth exigir;
- idioma alvo;
- nível atual;
- objetivo de estudo;
- foto/avatar por URL ou upload simples se já houver suporte.

## Backend
Criar/ajustar endpoints:
- `GET /api/profile/me`
- `PUT /api/profile/me`

Persistir dados no banco real.

## Frontend
Criar/ajustar rota de perfil conforme navegação existente.
Adicionar acesso no topbar/sidebar se já fizer sentido visualmente.
Tela deve ter loading, erro e sucesso.

## Regras
- Não recriar layout.
- Não implementar armazenamento complexo de arquivos se não houver infraestrutura.
- Se upload real não existir, permitir avatarUrl validado.
- Não salvar perfil apenas no localStorage.

## Critério de pronto
- Usuário consegue abrir perfil.
- Usuário consegue alterar dados permitidos.
- Dados permanecem após reload/login.
- UI preserva identidade atual.
