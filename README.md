# note-management-system-14814-14868

Notes Backend (Spring Boot)
- Start: ./gradlew bootRun from notes_backend
- Swagger UI: /swagger-ui.html
- Health: /health

Auth
- POST /api/auth/signup { "username": "alice", "password": "P@ssw0rd!" }
- POST /api/auth/login { "username": "alice", "password": "P@ssw0rd!" } -> { token }

Notes (Authorization: Bearer <token>)
- GET /api/notes
- POST /api/notes { "title": "My Note", "content": "Hello" }
- GET /api/notes/{id}
- PUT /api/notes/{id} { "title": "Updated", "content": "World" }
- DELETE /api/notes/{id}