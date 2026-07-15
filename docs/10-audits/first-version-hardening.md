# First Version Hardening Audit

## Scope Read

- `docs/01-ai-contract/ai-execution-contract.md`
- `docs/01-ai-contract/token-economy-rules.md`
- `docs/08-indexes/reading-order.md`
- `docs/12-prompts-production-springboot-fix/implementation/033-first-version-hardening.md`

## Checks Executed

- `apps/api`: `mvn test`
- web: `npm run lint`
- web: `npm run build`
- route file inspection under `src/app`
- integrated screen inspection for loading, error and empty states
- auth/session guard inspection
- local config, CORS and environment inspection
- `docker compose config`
- attempted `docker compose up --build -d`

## Result Summary

The first version is stable enough for local development and internal demo preparation, with one important caveat: container runtime was not fully verifiable in this environment because the Docker daemon was unavailable at audit time.

No critical frontend code defect was confirmed during this hardening pass. The integrated routes inspected already expose minimum loading, error or empty states, the auth/session boundary is consistent with the current backend contract, and CORS plus local API base URL are aligned for localhost use.

## Validation Notes

### Build, Test and Lint

- `mvn test`: passed
- `npm run lint`: passed
- `npm run build`: passed

### Routes Confirmed In Build Output

- `/`
- `/login`
- `/register`
- `/dashboard`
- `/community`
- `/speak`
- `/write`
- `/review`
- `/goals`
- `/progress`
- `/mock-test`

### Auth and Navigation

- `/` redirects to `/login` or `/dashboard` after persisted-store hydration.
- protected routes are wrapped by `ProtectedGuard` and require a stored access token plus successful `/api/auth/me`.
- public auth routes are wrapped by `PublicGuard` and redirect authenticated users back to `/dashboard`.
- logout clears the local session token even if the backend logout call fails.

### Loading, Error and Empty States

Inspected integrated pages:

- `/dashboard`
- `/community`
- `/speak`
- `/write`
- `/review`
- `/goals`
- `/progress`
- `/mock-test`

Result:

- all inspected integrated pages expose at least one explicit loading, error or empty branch where relevant;
- no route inspected depends on silent failure to render its main state;
- UX copy is consistent with the current product tone.

### CORS and Environment

- frontend HTTP client defaults to `NEXT_PUBLIC_API_BASE_URL` and falls back to `http://localhost:8080`
- backend CORS uses `APP_CORS_ALLOWED_ORIGINS` and defaults to `http://localhost:3000`
- `docker-compose.yml` passes matching localhost values for web and API
- backend security permits `POST /api/auth/register`, `POST /api/auth/login`, `GET /api/health`, authenticated `GET /api/auth/me`, authenticated `POST /api/auth/logout`, and currently permits remaining routes

## Findings

### 1. Docker runtime was not verifiable in this environment

Severity: medium

`docker compose config` resolved successfully, but `docker compose up --build -d` failed because the local Docker daemon was unavailable:

- `open //./pipe/dockerDesktopLinuxEngine: The system cannot find the file specified`

This is an environment blocker, not a confirmed repository defect. Still, the release claim "compose sobe" was not fully validated in this audit.

### 2. Some markdown documentation still has encoding noise in terminal reads

Severity: low

Files such as `docs/12-prompts-production-springboot-fix/implementation/033-first-version-hardening.md` and `docs/12-prompts-production-springboot-fix/production-status.md` show mojibake in PowerShell output. Source inspection of frontend files with UTF-8-aware reads did not confirm equivalent corruption in the product UI. This is a documentation quality issue, not a release-blocking frontend defect.

### 3. Root operational docs remain weaker than the implemented state

Severity: low

The repository still contains generic or outdated operational guidance, especially at root level. The codebase is ahead of the written runbook. That is not breaking runtime today, but it raises demo/setup friction and should be cleaned before a wider handoff.

## Changes Applied

- no application code changes were required in this hardening pass

## Remaining Risks

- container startup still needs validation on a machine with Docker running
- current backend security is intentionally permissive outside the auth endpoints; acceptable for this phase, but not for a real release
- release/runbook documentation is behind the actual implementation state

## Hardening Verdict

Prompt 033 can be considered functionally complete for the codebase itself:

- build, lint and tests are green
- main navigable routes are present
- integrated frontend routes have minimum resilience states
- localhost CORS and API base URL are aligned
- no critical defect required emergency code correction

Before claiming release readiness, validate `docker compose up --build` on a machine with an active Docker daemon and tighten release documentation accordingly.
