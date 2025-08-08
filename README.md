# Supermarket System (Spring Boot + Vue 3)

This workspace contains a backend (`supermarket-backend`) and a frontend (`supermarket-frontend`).

## Backend
- Java 21 + Spring Boot 3
- H2 in-memory DB, JPA, Security (JWT)

Run (Gradle 8+ required):
- If you have Gradle installed: `gradle bootRun`
- Or generate wrapper once: `gradle wrapper` then `./gradlew bootRun`
- Build jar: `gradle bootJar` (or `./gradlew bootJar`)

API:
- `POST /api/auth/login` { username: "admin", password: "admin123" }
- `GET /api/categories` CRUD
- `GET /api/products` CRUD
- `GET/POST /api/suppliers`, `GET/POST /api/customers`
- `POST /api/purchases`, `POST /api/sales`
- `GET /api/reports/stock`, `GET /api/reports/sales-summary?from=YYYY-MM-DDTHH:mm:ss&to=...`

H2 console: `/h2-console` (JDBC URL: `jdbc:h2:mem:supermarket`)

## Frontend
Vue 3 + Vite + TS app will live in `supermarket-frontend`.

Dev:
- `pnpm install`
- `pnpm dev`

Set env `VITE_API_BASE=http://localhost:8080/api` if needed.
