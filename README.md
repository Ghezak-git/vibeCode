# Demo Spring Boot WebService

A modern web service for access control, user management, product catalog, supplier management, and audit logging. Built with **Spring Boot** (REST API) and **ReactJS + Bootstrap** (UI).

## Features
- **Role-Based Access Control (RBAC):** Secure login, JWT session, dynamic menu per role.
- **User Management:** CRUD users, assign roles, profile management, password reset (OTP optional).
- **Product Catalog:** CRUD categories & products, stock management (business rule: stock ≥ 0).
- **Supplier Management:** CRUD suppliers, map products to suppliers (N..N).
- **Audit Logging:** All master changes logged (who/when/what).
- **API Documentation:** OpenAPI/Swagger UI.
- **Responsive UI:** React + Bootstrap, dynamic navigation, protected routes.

## Tech Stack
- **Backend:** Spring Boot 3.x, Spring Web, Spring Security, Spring Data JPA, Validation, MapStruct, Springdoc OpenAPI, Lombok
- **Database:** PostgreSQL 8.0+, Flyway for migrations
- **Frontend:** React 18, React Router, React Query, Axios, Bootstrap 5, Formik + Yup
- **Build/CI:** Maven, Docker, GitHub Actions

## Setup & Run
1. **Clone the repo:**
   ```sh
   git clone <repo-url>
   cd demo
   ```
2. **Configure DB:**
   - Edit `src/main/resources/application.properties` for your PostgreSQL settings.
3. **Run migrations:**
   - Flyway auto-runs on app start (see `db/migration/`).
4. **Start backend:**
   ```sh
   ./mvnw spring-boot:run
   ```
5. **API Docs:**
   - Visit [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## API Overview
- **Base URL:** `/api/v1`
- **Auth:** `POST /auth/login`, `GET /auth/me`, `GET /auth/me/menus`
- **Users:** CRUD, password reset, profile
- **Roles & Menus:** CRUD, mapping
- **Categories & Products:** CRUD, stock adjust
- **Suppliers:** CRUD, mapping
- **Logs:** All changes tracked

## Example Payloads
```json
POST /api/v1/products
{
  "idKategoriProduk": 1,
  "namaProduk": "Kemeja Oxford",
  "merk": "Alpha",
  "model": "OX-2025",
  "warna": "Navy",
  "deskripsiProduk": "Kemeja lengan panjang",
  "stok": 50
}
```

## Testing
- **Backend:** JUnit5, Mockito, Testcontainers
- **Frontend:** Vitest/Jest, React Testing Library, Playwright/Cypress

## Deployment
- **Docker-ready:** See `docker-compose.yml` (API + PostgreSQL + Web)
- **Environments:** dev, staging, prod (Spring profiles)
- **CI/CD:** GitHub Actions

## License
MIT (or specify your license)

---
_See `Project_Brief.md` for full details and architecture diagrams._
