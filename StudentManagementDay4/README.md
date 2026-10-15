# Student Management System — Day 4 Full-Stack Demo

A teaching starter project for React + Spring Boot REST API + JWT + JUnit/Mockito + Docker.

## Requirements
- Java 21, Maven 3.9+, Node.js 20+, npm
- Docker Desktop (optional, for PostgreSQL)

## 1. Start the backend (demo uses H2 in-memory database)
```bash
cd backend
mvn spring-boot:run
```
API: http://localhost:8080/api
H2 console: http://localhost:8080/h2-console (JDBC URL `jdbc:h2:mem:studentsdb`, user `sa`, blank password)

Demo account: `admin@demo.com` / `Admin123!`

## 2. Start the frontend
In a second terminal:
```bash
cd frontend
npm install
npm run dev
```
Open http://localhost:5173

## 3. Test the REST API with Postman
Import `postman/Student-Management-API.postman_collection.json` into Postman. Start with **Login - get JWT token**; the collection automatically saves the token. Then test listing students, getting one student, creating, updating and deleting a student. A security-check request demonstrates that the protected endpoint returns `401` when no token is supplied. Full instructions are in `postman/README.md`.

## Preloaded sample data
On startup, the backend inserts three students into H2 if the table is empty:
- Aarav Sharma — aarav@example.com — Computer Science — 88.5 marks
- Diya Rao — diya@example.com — Information Technology — 92.0 marks
- Kabir Singh — kabir@example.com — Cybersecurity — 76.0 marks

H2 is an in-memory database, so its data resets when the application stops. The seed logic avoids inserting duplicates during the same database lifetime.

## Demo flow
1. Log in using the demo account.
2. List, add, edit and delete students.
3. Use search and status filters.
4. Sign out and observe protected API calls require a JWT.
5. Run backend tests with `cd backend && mvn test`.

## 4. Docker option
`docker compose up --build` runs the backend with PostgreSQL. The frontend is run separately using the steps above. For a local H2 demo, use `mvn spring-boot:run` instead.

## Teaching notes / limitations
This is a classroom demo, not production-ready authentication. It uses a fixed demo admin account and a simple JWT filter to keep the project small. In a real system, store users in a database, use HTTPS, manage secrets outside source control, add refresh-token/revocation strategy, stricter CORS, rate limiting, audit logs and production migrations. The UI includes a demo login so students can learn the full request flow.
