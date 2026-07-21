# API Spec — Global Search

## GET `/api/search`
Authenticated endpoint.

Query parameters:
- `q`: required after trim, 2 to 100 characters;
- `types`: optional comma-separated `SearchResultType` values; all declared types by default;
- `limit`: optional, default 10, minimum 1, maximum 30.

Response `200`:
```json
{
  "query": "travel",
  "results": [
    {
      "type": "FLASHCARD",
      "id": "uuid",
      "title": "Travel vocabulary",
      "description": "Vocabulary for airports and hotels",
      "route": "/review",
      "score": 1.0
    }
  ]
}
```

Errors:
- `400` for invalid query, type, or limit;
- `401` without authentication.

No JPA entity is returned. Empty and unavailable selected sources yield an empty `results` array.
