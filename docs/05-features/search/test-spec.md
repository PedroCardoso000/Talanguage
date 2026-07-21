# Test Spec — Global Search

## Unit tests
- rejects blank, too short, and too long terms;
- searches case-insensitively;
- filters by selected type;
- isolates private resources by user;
- caps results at the requested maximum;
- ranks exact title before prefix and partial content;
- excludes missing and sensitive resources;
- continues when one source is unavailable.

## API tests
- authenticated valid search returns `200` and the normalized query;
- unauthenticated search returns `401`;
- invalid query, type, or limit returns `400`;
- response contains only the documented projection.

## Regression
- focused search tests, complete backend suite, compilation, and `git diff --check` must pass.
