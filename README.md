# Blog

## Project Overview
This project is a blogging platform API that allows users to manage user accounts and blog posts. It provides CRUD (Create, Read, Update, Delete) operations for users and posts, along with search and pagination features.

---

### 1. Setup and Prerequisites

#### 1.1 Install Required Tools
- **Java Development Kit (JDK)**: Ensure JDK 21 or higher is installed.
- **Spring Boot**: Use Spring Initializr or your IDE (e.g., IntelliJ IDEA, Eclipse) to bootstrap the project.
- **PostgreSQL Database**: Install and configure PostgreSQL.
- **Maven/Gradle**: Dependency management.

#### 1.2 Create a Spring Boot Project
Use Spring Initializr to generate the project with:
- Spring Web
- Spring Data JPA
- PostgreSQL Driver
- Spring Boot DevTools (optional, for live reload during development)
- Validation (Hibernate Validator)

---

### 2. Features

#### 2.1 User Management
- **Create User**: Add a new user with a first name, last name, email, and phone number.
- **Get User**: Retrieve details of a specific user by their ID.
- **Search Users**: Search for users with pagination and sorting.
- **Update User**: Update the details of an existing user.
- **Delete User**: Delete a user, with support for soft deletion.

#### 2.2 Post Management
- **Create Post**: Add a new blog post with a title, content, and associated user ID.
- **Get Post**: Retrieve details of a specific post by its ID.
- **Search Posts**: Search for posts with pagination and sorting.
- **Update Post**: Update the details of an existing post.
- **Delete Post**: Delete a post, with support for soft deletion.

---

### 3. Define Project Structure

#### 3.1 Directory Structure

```
src/main/java/com/blogjavaspringboot/domain
├── controllers/
│   ├── PostController.java         // REST API endpoints
│   ├── UserController.java         // REST API endpoints
├── dtos/
│   ├── PostDto.java         // Post data transfer object
│   ├── UserDto.java         // User data transfer object
├── entities/
│   ├── Post.java         // Post entity class
│   ├── User.java         // User entity class
├── mappers/
│   ├── PostMapper.java         // Mapper class
│   ├── UserMapper.java         // Mapper class
├── repositories/
│   ├── PostRepository.java         // Repository interface
│   ├── UserRepository.java         // Repository interface
├── services/
│   ├── PostService.java         // Service class (Business Logic)
│   ├── UserService.java         // Service class (Business Logic)
```

---

### 4 API Documentation (Endpoints)

#### 4.1 User Endpoints

- **Get All Users**
   - Endpoint: `GET /users`
   - Parameters:
      - `query` (optional): Search query.
      - `page` (optional): Page number (default: 0).
      - `page_size` (optional): Number of items per page (default: 10).
      - `direction` (optional): Sorting direction (default: DESC).
      - `sorted_field_name` (optional): Field name to sort by (default: id).
      - `pageable_limit` (optional): Enable pagination (default: true).

- **Get User by ID**
   - Endpoint: `GET /users/{id}`

- **Create User**
   - Endpoint: `POST /users`
   - Request Body:
       ```json
       {
         "first_name": "string",
         "last_name": "string",
         "email": "string",
         "phone_number": "string"
       }
       ```

- **Update User**
   - Endpoint: `PATCH /users/{id}`
   - Request Body:
       ```json
       {
         "first_name": "string",
         "last_name": "string",
         "email": "string",
         "phone_number": "string"
       }
       ```

- **Delete User**
   - Endpoint: `DELETE /users/{id}`
   - Parameters:
      - `soft_delete` (optional): Enable soft delete (default: true).

#### 4.2 Post Endpoints

- **Get All Posts**
   - Endpoint: `GET /posts`
   - Parameters:
      - `query` (optional): Search query.
      - `page` (optional): Page number (default: 0).
      - `page_size` (optional): Number of items per page (default: 10).
      - `direction` (optional): Sorting direction (default: DESC).
      - `sorted_field_name` (optional): Field name to sort by (default: id).
      - `pageable_limit` (optional): Enable pagination (default: true).

- **Get Post by ID**
   - Endpoint: `GET /posts/{id}`

- **Create Post**
   - Endpoint: `POST /posts`
   - Request Body:
       ```json
       {
         "title": "string",
         "content": "string",
         "user_id": 0
       }
       ```

- **Update Post**
   - Endpoint: `PATCH /posts/{id}`
   - Request Body:
       ```json
       {
         "title": "string",
         "content": "string",
         "user_id": 0
       }
       ```

- **Delete Post**
   - Endpoint: `DELETE /posts/{id}`
   - Parameters:
      - `soft_delete` (optional): Enable soft delete (default: true).

---

### 5. Validation Rules
- **User Entity**:
   - `firstName`: Required.
   - `email`: Must be a valid email address.
   - `phoneNumber`: Must be a valid phone number.
- **Post Entity**:
   - `title`: Required.
   - `content`: Length between 50 to 10,000 characters.
   - `userId`: Required.

---

### 6. Error Handling
- Validation errors return HTTP 400 with a descriptive error message.
- Entity not found errors return HTTP 404.

---

### 7. Testing

#### 7.1 Unit and Integration Tests
- Write tests for each endpoint using JUnit and MockMvc.  
- Validate behavior for valid/invalid requests, boundary conditions, and error scenarios.

#### 7.2 Postman or Swagger
- Use Postman to manually test API endpoints.
- Optionally, integrate Swagger for API documentation:
  - Add dependency: springdoc-openapi-ui.

---

### 8. Setup Instructions

1. **Clone the Repository**:
   ```bash
   git clone <repository-url>
   cd <project-directory>
   ```

2. **Build the Project**:
   ```bash
   ./mvnw clean install
   ```

3. **Run the Application**:
   ```bash
   ./mvnw spring-boot:run
   ```

4. **API Testing**:
    - Use tools like Postman or cURL to test the endpoints.

---

### 9. License
This project is open-source and available under the MIT License.

