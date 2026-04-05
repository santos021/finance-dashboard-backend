# 💰 Finance Dashboard Backend
## 📌 Overview

This project is a backend system for managing financial records and providing dashboard analytics based on user roles. It demonstrates backend design, API structuring, role-based access control, and data aggregation using Spring Boot.

## 🚀 Tech Stack
- Java 17
- Spring Boot
- Spring Security (Basic Authentication)
- Spring Data JPA (Hibernate)
- MySQL
- Swagger (OpenAPI)

## 👤 Roles & Permissions
| Role |  Permissions |
| ------- | --------------|
| ADMIN	| Full access (manage users & records) |
| ANALYST |	View records & dashboard analytics
| VIEWER |	Read-only access
## 📊 Features
### 🔹 User Management
- Create and manage users
- Assign roles (ADMIN, ANALYST, VIEWER)
- Manage user status (ACTIVE / INACTIVE)
### 🔹 Financial Records Management
- Create, view, update, delete records
- Filter records by type, category, and date
- Search records by category (case-insensitive)
- Each record is linked to the user who created it (createdBy)
### Fields:
- Amount
- Type (INCOME / EXPENSE)
- Category
- Date
- Description
- Created By (User)
### 🔹 Dashboard APIs
- Total Income
- Total Expense
- Net Balance
- Category-wise totals
- Recent transactions
- Monthly trends
## 🔐 Authentication & Authorization
- Basic Authentication using Spring Security
- Role-based access control for APIs
- Authenticated users are associated with created records
## ⚠️ Validation & Error Handling
- Input validation using annotations (@NotNull, @NotBlank)
- Global exception handling using @RestControllerAdvice
- Clean and consistent error responses
## 🔍 Search Support

- Search records by category using:
```text
GET /api/records/search?keyword=food
```
## 🛠️ API Documentation

- Swagger UI available at:
    http://localhost:8091/swagger-ui/index.html

## 🗂️ API Endpoints
### 👤 User APIs
- POST ``` /api/admin/users ```
- GET ``` /api/admin/users ```
### 💰 Financial Records APIs
- POST ``` /api/records ```
- GET ``` /api/records ```
- PUT ``` /api/records/{id} ```
- DELETE ``` /api/records/{id} ```
- GET ``` /api/records/filter ```
- GET ``` /api/records/search ```
### 📊 Dashboard APIs
- GET ``` /api/analyst/dashboard/summary ```
- GET ``` /api/analyst/dashboard/category ```
- GET ``` /api/analyst/dashboard/recent ```
- GET ``` /api/analyst/dashboard/monthly ```
### 🗄️ Data Persistence
- MySQL database used for storage
- JPA used for ORM and entity management
- Aggregation queries used for analytics
## ▶️ How to Run

1. Clone repository:

    git clone https://github.com/santos021/finance-dashboard-backend.git

2. Create database:

    CREATE DATABASE ``` finance_db ```;
3. Configure database in ``` application.properties```

4. Run application:

    ``` mvn spring-boot:run ```

5. Open Swagger:

    http://localhost:8091/swagger-ui/index.html

## 💡 Assumptions
- Basic Authentication used for simplicity
- Monthly trends implemented (weekly optional)
- createdBy implemented using logged-in user context
## 🚀 Future Enhancements
- JWT Authentication
- Pagination
- Advanced filtering
- Unit & integration tests

## 📬 Author
### Santos Kumar Biswal
- 📧 santosbiswal543@gmail.com
- 🔗 LinkedIn: https://www.linkedin.com/in/santos-biswal07/
- 🔗 GitHub: https://github.com/santos021