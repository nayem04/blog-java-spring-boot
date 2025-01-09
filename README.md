# Blog

## Project Overview
This project is a blogging platform API that allows users to manage user accounts and blog posts. It provides CRUD (Create, Read, Update, Delete) operations for users and posts, along with search and pagination features.

---

## Features

### User Management
- **Create User**: Add a new user with a first name, last name, email, and phone number.
- **Get User**: Retrieve details of a specific user by their ID.
- **Search Users**: Search for users with pagination and sorting.
- **Update User**: Update the details of an existing user.
- **Delete User**: Delete a user, with support for soft deletion.

### Post Management
- **Create Post**: Add a new blog post with a title, content, and associated user ID.
- **Get Post**: Retrieve details of a specific post by its ID.
- **Search Posts**: Search for posts with pagination and sorting.
- **Update Post**: Update the details of an existing post.
- **Delete Post**: Delete a post, with support for soft deletion.

---

## API Documentation

### Endpoints

#### User Endpoints
1. **Get All Users**
    - **Endpoint**: `GET /users`
    - **Parameters**:
        - `query` (optional): Search query.
        - `page` (optional): Page number (default: 0).
        - `page_size` (optional): Number of items per page (default: 10).
        - `direction` (optional): Sorting direction (default: DESC).
        - `sorted_field_name` (optional): Field name to sort by (default: `id`).
        - `pageable_limit` (optional): Enable pagination (default: true).

2. **Get User by ID**
    - **Endpoint**: `GET /users/{id}`

3. **Create User**
    - **Endpoint**: `POST /users`
    - **Request Body**:
      ```json
      {
        "first_name": "string",
        "last_name": "string",
        "email": "string",
        "phone_number": "string"
      }
      ```

4. **Update User**
    - **Endpoint**: `PATCH /users/{id}`
    - **Request Body**:
      ```json
      {
        "first_name": "string",
        "last_name": "string",
        "email": "string",
        "phone_number": "string"
      }
      ```

5. **Delete User**
    - **Endpoint**: `DELETE /users/{id}`
    - **Parameters**:
        - `soft_delete` (optional): Enable soft delete (default: true).

#### Post Endpoints
1. **Get All Posts**
    - **Endpoint**: `GET /posts`
    - **Parameters**:
        - `query` (optional): Search query.
        - `page` (optional): Page number (default: 0).
        - `page_size` (optional): Number of items per page (default: 10).
        - `direction` (optional): Sorting direction (default: DESC).
        - `sorted_field_name` (optional): Field name to sort by (default: `id`).
        - `pageable_limit` (optional): Enable pagination (default: true).

2. **Get Post by ID**
    - **Endpoint**: `GET /posts/{id}`

3. **Create Post**
    - **Endpoint**: `POST /posts`
    - **Request Body**:
      ```json
      {
        "title": "string",
        "content": "string",
        "user_id": 0
      }
      ```

4. **Update Post**
    - **Endpoint**: `PATCH /posts/{id}`
    - **Request Body**:
      ```json
      {
        "title": "string",
        "content": "string",
        "user_id": 0
      }
      ```

5. **Delete Post**
    - **Endpoint**: `DELETE /posts/{id}`
    - **Parameters**:
        - `soft_delete` (optional): Enable soft delete (default: true).

---

## Setup Instructions

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

## Validation Rules
- **User Entity**:
    - `firstName`: Required.
    - `email`: Must be a valid email address.
- **Post Entity**:
    - `title`: Required.
    - `content`: Length between 50 to 10,000 characters.
    - `userId`: Required.

---

## Error Handling
- Validation errors return HTTP 400 with a descriptive error message.
- Entity not found errors return HTTP 404.

---

## License
This project is open-source and available under the MIT License.

