# Tala API Foundation

This backend area is reserved for the official Talanguage backend stack:

- Java;
- Spring Boot;
- REST API;
- Spring Data JPA;
- PostgreSQL.

Current status:

- `apps/api` is the correct backend location to evolve;
- the backend is not production-ready yet;
- some earlier work may have been reverted and must be revalidated before new implementation;
- feature slices must follow the documented modular monolith boundaries.

Target structure:

```txt
apps/api/
├── src/
│   ├── main/
│   │   ├── java/
│   │   └── resources/
│   └── test/
└── tests/
```

Implementation guidance:

1. preserve separation between API, Application, Domain, Infrastructure and Contracts;
2. implement real backend slices incrementally instead of recreating the application;
3. keep persistence behind repository interfaces;
4. follow the production prompt track under `docs/12-prompts-production-springboot-fix/`.

Expected runtime direction:

- Maven build;
- Spring Boot application startup;
- JUnit 5, Mockito and MockMvc for tests;
- Flyway when real schema migrations are introduced.
