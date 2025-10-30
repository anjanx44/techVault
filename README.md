# techVault

A full‑stack tech knowledge platform with a Quarkus (Java) backend and a Next.js (React/TypeScript) frontend. The backend exposes REST APIs for authentication, posts, tags, comments, likes, and users. The frontend is a Next.js 15 app using Mantine UI and Tailwind.

## Overview
- Backend: Quarkus 3 (Java 17), RESTEasy Reactive, Hibernate ORM/Panache, PostgreSQL, SmallRye JWT
- Frontend: Next.js 15, React 19, TypeScript, Mantine UI, Tailwind CSS
- AuthN/AuthZ: JWT (SmallRye/JWT) with role-based access (e.g., `@RolesAllowed("user")`, `@RolesAllowed("admin")`)
- CORS: Enabled (wildcard origins by default in dev)

## Requirements
- Java 17 (JDK 17)
- Maven 3.9+
- Node.js 20+ and npm 10+ (for the frontend)
- PostgreSQL 14+ (or compatible)

## Project Structure
```
techVault/
├─ LICENSE
├─ README.md
├─ backend/                      # Quarkus application (Maven)
│  ├─ pom.xml
│  └─ src/
│     ├─ main/java/com/anjan/techvault/
│     │  ├─ api/                 # REST controllers (JAX-RS)
│     │  └─ config/security/     # Security configuration (JWT)
│     └─ main/resources/
│        └─ application.properties
└─ frontend/                     # Next.js 15 application
   ├─ package.json
   └─ src/ (or app/)             # Usual Next.js structure
```

## Backend
### Stack and Key Dependencies
- Quarkus BOM: `io.quarkus.platform:quarkus-bom:3.6.0`
- REST: `quarkus-resteasy-reactive`, `quarkus-resteasy-reactive-jackson`
- Data: `quarkus-hibernate-orm`, `quarkus-hibernate-orm-panache`, `quarkus-jdbc-postgresql`
- Security: `quarkus-security`, `quarkus-elytron-security-jdbc`
- JWT: `quarkus-smallrye-jwt`, `quarkus-smallrye-jwt-build`
- Lombok (provided)
- Test: `quarkus-junit5`, `rest-assured`

### Configuration (application.properties)
```
# App
quarkus.application.name=techVault

# HTTP
quarkus.http.port=8080

# Database (PostgreSQL)
quarkus.datasource.db-kind=postgresql
quarkus.datasource.username=postgres
quarkus.datasource.password=postgres
quarkus.datasource.jdbc.url=jdbc:postgresql://localhost:5432/techvaultdb

# ORM
quarkus.hibernate-orm.database.generation=drop-and-create
quarkus.hibernate-orm.dialect=org.hibernate.dialect.PostgreSQLDialect

# JWT
mp.jwt.verify.publickey.location=publicKey.pem
mp.jwt.verify.issuer=https://techvault.com/issuer
quarkus.smallrye-jwt.enabled=true
smallrye.jwt.sign.key.location=privateKey.pem

# CORS (dev-friendly defaults)
quarkus.http.cors=true
quarkus.http.cors.origins=*
quarkus.http.cors.methods=GET,POST,PUT,DELETE
quarkus.http.cors.headers=accept,authorization,content-type,x-requested-with
quarkus.http.cors.exposed-headers=Content-Disposition
quarkus.http.cors.access-control-max-age=24H

# Permissions (examples)
quarkus.http.auth.permission.public.paths=/api/v1/posts,/api/v1/posts/search
quarkus.http.auth.permission.public.policy=permit
quarkus.http.auth.permission.public.methods=GET

quarkus.http.auth.permission.authenticated.paths=/api/v1/posts
quarkus.http.auth.permission.authenticated.policy=authenticated
quarkus.http.auth.permission.authenticated.methods=POST

quarkus.http.auth.permission.admin.paths=/api/v1/posts/*
quarkus.http.auth.permission.admin.policy=authenticated
quarkus.http.auth.permission.admin.methods=DELETE
```

Note: Quarkus config keys can be overridden via environment variables (e.g., `QUARKUS_DATASOURCE_USERNAME`, `QUARKUS_DATASOURCE_PASSWORD`, `QUARKUS_DATASOURCE_JDBC_URL`, `QUARKUS_HTTP_PORT`, etc.).

### Environment and Secrets
- Database
  - `QUARKUS_DATASOURCE_USERNAME` (default: `postgres`)
  - `QUARKUS_DATASOURCE_PASSWORD` (default: `postgres`)
  - `QUARKUS_DATASOURCE_JDBC_URL` (default: `jdbc:postgresql://localhost:5432/techvaultdb`)
- JWT
  - `MP_JWT_VERIFY_PUBLICKEY_LOCATION` (default: `publicKey.pem`)
  - `SMALLRYE_JWT_SIGN_KEY_LOCATION` (default: `privateKey.pem`)
  - `MP_JWT_VERIFY_ISSUER` (default: `https://techvault.com/issuer`)
- Place `publicKey.pem` and `privateKey.pem` in `backend/src/main/resources/` for classpath resolution, or set absolute paths.

TODO:
- Document how JWT keys are generated/managed for dev and prod.
- Provide production-safe database defaults and migration strategy (e.g., Flyway/Liquibase) if adopted.

### API Entry Points (partial)
- Auth
  - `POST /auth/register` (public) — register user
  - `POST /auth/login` (public) — obtain JWT
- Posts (`com.anjan.techvault.api.PostController`)
  - `GET  /api/v1/posts` (public) — list posts
  - `GET  /api/v1/posts/search?keyword=...` (public) — search by title
  - `POST /api/v1/posts` (role `user`) — create post
  - `DELETE /api/v1/posts/{id}` (role `admin`) — delete post
- Additional controllers present (endpoints not enumerated here): `UserController`, `TagController`, `CommentController`, `LikeController`.

TODO:
- Add a complete API reference (request/response schemas, status codes, auth requirements) for all controllers.

### How to Run (backend)
From the `backend/` directory:

- Development (hot-reload):
```
mvn quarkus:dev
```
Backend will run on `http://localhost:8080` by default.

- Tests:
```
mvn test
```

- Package (fast-jar):
```
mvn clean package -DskipTests
```
The runner JAR will be in `backend/target/`.

- Run packaged app:
```
java -jar target/quarkus-app/quarkus-run.jar
```

## Frontend
### Scripts
From the `frontend/` directory:
```
npm install
npm run dev     # Next.js dev server (default: http://localhost:3000)
npm run build   # Production build
npm start       # Start production server
npm run lint
```

### Dependencies (selected)
- `next@15.3.3`, `react@19`, `react-dom@19`
- UI: `@mantine/core`, `@mantine/hooks`, `@emotion/react`
- Styling: `tailwindcss@^4` (with `@tailwindcss/postcss`)
- Markdown tooling: `gray-matter`, `remark`, `remark-html`

TODO:
- Document environment variables for the frontend (e.g., `NEXT_PUBLIC_API_BASE_URL`).
- Link pages to backend endpoints and note any proxy configuration if used.

## Local Development (combined)
- Start the backend first (port 8080) using `mvn quarkus:dev` in `backend/`.
- Start the frontend (port 3000) using `npm run dev` in `frontend/`.
- Ensure CORS settings allow `http://localhost:3000` or configure a proxy in Next.js.

## Tests
- Backend: JUnit 5 with `@QuarkusTest` is set up. Example test class: `backend/src/test/java/com/anjan/techvault/TechVaultApplicationTests.java`.
- Frontend: No test script defined in `package.json` at the moment.

TODO:
- Add REST endpoint tests with RestAssured.
- Add unit/integration tests for services and repositories.
- Add frontend test tooling (e.g., Vitest/Jest + React Testing Library) and scripts.

## Useful Maven Commands
```
# In backend/
mvn quarkus:dev                # Dev mode with hot reload
mvn test                       # Run tests
mvn clean package              # Build
mvn clean package -DskipTests  # Build without tests
```

## Useful npm Scripts
```
# In frontend/
npm run dev
npm run build
npm start
npm run lint
```

## Environment Variables Summary
- Backend (override `application.properties` via env):
  - `QUARKUS_DATASOURCE_USERNAME`, `QUARKUS_DATASOURCE_PASSWORD`, `QUARKUS_DATASOURCE_JDBC_URL`
  - `QUARKUS_HTTP_PORT`
  - `MP_JWT_VERIFY_PUBLICKEY_LOCATION`, `SMALLRYE_JWT_SIGN_KEY_LOCATION`, `MP_JWT_VERIFY_ISSUER`
- Frontend:
  - TODO: `NEXT_PUBLIC_API_BASE_URL` and others as needed.

## Deployment
TODO:
- Provide Dockerfiles and/or containerization steps for backend and frontend.
- Describe database provisioning and migrations for production.
- CI/CD instructions (build, test, deploy pipelines).

## License
This project is licensed under the MIT License — see the [LICENSE](LICENSE) file for details.
