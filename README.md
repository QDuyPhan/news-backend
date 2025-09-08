# News Backend

This is a Spring Boot backend application for a news platform, providing RESTful APIs for managing users, news articles, categories, roles, permissions, and authentication.

## Features
- User authentication and authorization (JWT-based)
- CRUD operations for news articles, categories, tags, authors
- Role-based access control and permission management
- User management (registration, login, profile)
- Category and tag management
- Secure API endpoints

## Technology Stack
- Java 21
- Spring Boot 3.4.2
- Spring Data JPA
- Spring Security
- MySQL
- Lombok
- MapStruct
- JWT (nimbus-jose-jwt)

## Project Structure
- `controller/` - REST API controllers
- `entity/` - JPA entities (Author, Category, News, Permission, Role, Tag, User, InvalidatedToken)
- `service/` - Business logic
- `repository/` - Data access layer
- `dto/` - Data transfer objects
- `mapper/` - MapStruct mappers
- `configuration/` - Security and application configuration

## Getting Started
### Prerequisites
- Java 21+
- Maven
- MySQL

### Setup
1. Clone the repository
2. Configure your database in `src/main/resources/application.yaml`
3. Build the project:
   ```bash
   ./mvnw clean install
   ```
4. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

### Default Configuration
- Server runs on port `8080`
- Database: `jdbc:mysql://localhost:3306/news_backend`
- JWT secret and durations are set in `application.yaml`

## API Overview
- `/api/auth` - Authentication endpoints
- `/api/users` - User management
- `/api/news` - News articles
- `/api/categories` - Categories
- `/api/roles` - Roles
- `/api/permissions` - Permissions

## License
This project is for educational and internal use.

---
For more details, see the source code and controller classes for specific endpoints and request/response formats.
