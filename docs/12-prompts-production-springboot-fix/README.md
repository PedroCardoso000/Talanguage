# Tala Language — Production Prompts Spring Boot Fix

Este pacote corrige a trilha de produção para **Spring Boot**.

Use este pacote porque a trilha anterior fixou backend como NestJS indevidamente.

## Regra central

O projeto já possui aplicação/front-end/documentação. A IA deve revisar o estado atual e alterar apenas o necessário. Não recriar aplicação, não trocar stack e não reestruturar sem necessidade.

## Como usar

1. Extraia este pacote na raiz do projeto.
2. Rode primeiro `019-fix-stack-to-spring-boot.md`.
3. Depois rode `020-recheck-quality-after-stack-fix.md`.
4. Continue do `021` em diante.

## Stack oficial

- Backend: Spring Boot
- Linguagem: Java
- Banco: PostgreSQL
- Persistência: Spring Data JPA
- Migrations: Flyway, se já estiver configurado ou se o projeto permitir
- Testes: JUnit 5, Mockito, MockMvc/Testcontainers quando aplicável
- Frontend: preservar stack existente
