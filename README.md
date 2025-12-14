Spring Boot Inventory Backend (complete)

How to run:
1. unzip the project
2. cd into the project folder
3. docker-compose up --build
4. API available at http://localhost:9090/api

Default admin:
  email: admin@azakary.com
  password: admin123

Endpoints:
- POST /api/auth/register  {"email","password"}
- POST /api/auth/login  {"email","password"} -> returns {token}
- GET /api/products (authenticated after login)
