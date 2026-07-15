<!--
Talanguage — Feature Specification
Contexto: o front-end já possui UI mockada estruturada. O backend ainda não existe.
Objetivo: orientar a transição de telas mockadas para funcionalidades reais, sem recriar a UI do zero e sem criar decisões fora da arquitetura documentada.
-->

# Feature Spec — Auth

## Objetivo
Permitir que o usuário crie conta, faça login, mantenha sessão ativa e acesse áreas privadas do Talanguage.

## Contexto atual
O front-end já possui telas de login/cadastro mockadas. A implementação deve preservar a estrutura visual existente e substituir comportamentos simulados por integração real com o backend.

## Valor para o usuário
O usuário consegue entrar na plataforma e iniciar uma jornada individual de aprendizagem com dados associados à sua conta.

## Escopo da primeira versão
Inclui:
- cadastro com nome, e-mail e senha;
- login com e-mail e senha;
- sessão autenticada;
- logout;
- proteção de rotas privadas;
- usuário autenticado disponível para o front-end;
- tratamento básico de erros.

## Fora de escopo da primeira versão
- login social;
- recuperação de senha completa;
- MFA/2FA;
- perfis corporativos;
- gestão avançada de permissões;
- assinatura/pagamento;
- OAuth externo.

## Fluxos principais
1. Usuário acessa `/register`.
2. Cria conta.
3. É autenticado ou redirecionado para login.
4. Usuário acessa `/login`.
5. Entra na plataforma.
6. Acessa `/dashboard`.
7. Pode sair via logout.

## Critério de pronto
- telas existentes conectadas ao backend;
- autenticação funcional;
- token/sessão armazenado com segurança adequada para a primeira versão;
- rotas privadas protegidas;
- erros exibidos de forma clara;
- testes principais criados.
