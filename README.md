# springboot-jwt-based-security

# JWT Authentication in Spring Boot

This project implements **JWT-based authentication** using **Spring Boot and Spring Security**. The server runs on **port 8084** and provides endpoints for user authentication and securing APIs with JWT.

## 📌 Features

- User authentication with **JWT token**
- Role-based access control
- Secure API endpoints with JWT
- Password hashing using **BCrypt**
- Stateless authentication with **Spring Security**

## 🛠 Technologies Used

- **Spring Boot 3+**
- **Spring Security**
- **JWT (JSON Web Token)**
- **Spring Data JPA**
- **H2 Database**
- **Lombok**

---

## 🔧 Setup & Installation

### 1️⃣ Clone the Repository

```sh
git clone https://github.com/code-with-rj1399/springboot-jwt-based-security.git
cd springboot-jwt-based-security
```

### 2️⃣ Configure the Database

Modify `application.properties` or `application.yml` for database settings.

**H2 (In-memory Database)**:

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

### 3️⃣ Build & Run the Application

```sh
mvn clean install
mvn spring-boot:run
```

The server will start on [**http://localhost:8084**](http://localhost:8084) 🚀

---

## 🔐 Authentication & Authorization

### 1️⃣ Register a User

**[POST /auth/register](http://localhost:8084/swagger-ui.html#/AuthController/register)**

**Request Body:**

```json
{
  "username": "john_doe",
  "password": "password123"
}
```

### 2️⃣ Login & Get JWT Token

**[POST /auth/login](http://localhost:8084/swagger-ui.html#/AuthController/login)**

**Request Body:**

```json
{
  "username": "john_doe",
  "password": "password123"
}
```

**Response:**

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

### 3️⃣ Access Secured Endpoint

**[GET /users/all](http://localhost:8084/swagger-ui.html#/UserController/getAllUsers)**

**Headers:**

```http
Authorization: Bearer <JWT_TOKEN>
```

---

## 🏗 Project Structure

```
├── src/main/java/com/example/jwt
│   ├── config/SecurityConfig.java
│   ├── controller/AuthController.java
│   ├── service/JwtUtil.java
│   ├── service/UserDetailsServiceImpl.java
│   ├── filter/JwtFilter.java
│   ├── model/User.java
│   ├── model/AuthenticationRequest.java
│   ├── model/AuthenticationResponse.java
│   ├── repository/UserRepository.java
│   └── main/Application.java
```

---

## 🛠 API Endpoints

| Endpoint          | Method | Description |
|------------------|--------|-------------|
| **[POST /auth/register](http://localhost:8084/swagger-ui.html#/AuthController/register)** | POST   | Register a new user |
| **[POST /auth/login](http://localhost:8084/swagger-ui.html#/AuthController/login)**    | POST   | Authenticate and receive a JWT token |
| **[GET /users/all](http://localhost:8084/swagger-ui/index.html#/users-controller/getAllUsers)** | GET    | Get list of users (secured) |

## Swagger API Documentation
After running the application, you can access the Swagger UI at:

- **[Swagger UI](http://localhost:8084/swagger-ui.html)**
- **[OpenAPI Docs](http://localhost:8084/v3/api-docs)**

---


## Author
- GitHub: **[code-with-rj1399](https://github.com/code-with-rj1399)**

