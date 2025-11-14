# API Endpoints

## Authentication

### POST /api/auth/login
Login user and get JWT token.

**Request:**
```json
{
  "username": "john_doe",
  "password": "password123"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "user": {
    "id": 1,
    "username": "john_doe",
    "email": "john@example.com"
  }
}
```

### POST /api/auth/register
Register new user.

**Request:**
```json
{
  "username": "jane_doe",
  "email": "jane@example.com",
  "password": "password123",
  "firstName": "Jane",
  "lastName": "Doe"
}
```

## Posts

### GET /api/posts
Get all published posts with pagination.

**Query Parameters:**
- `page` (default: 0)
- `size` (default: 10)
- `category` (optional)
- `tag` (optional)

**Response:**
```json
{
  "content": [
    {
      "id": 1,
      "title": "Getting Started with Quarkus",
      "slug": "getting-started-with-quarkus",
      "excerpt": "Learn how to build fast Java applications...",
      "author": {
        "username": "john_doe",
        "firstName": "John"
      },
      "publishedAt": "2024-01-15T10:30:00Z",
      "categories": ["Java", "Framework"],
      "tags": ["quarkus", "java", "microservices"]
    }
  ],
  "totalElements": 25,
  "totalPages": 3,
  "number": 0,
  "size": 10
}
```

### GET /api/posts/{slug}
Get single post by slug.

### POST /api/posts
Create new post (authenticated).

**Request:**
```json
{
  "title": "New Blog Post",
  "content": "Full content here...",
  "excerpt": "Short description...",
  "categoryIds": [1, 2],
  "tagIds": [1, 3, 5],
  "status": "PUBLISHED"
}
```

### PUT /api/posts/{id}
Update existing post (authenticated, author only).

### DELETE /api/posts/{id}
Delete post (authenticated, author only).

## Categories

### GET /api/categories
Get all categories.

### POST /api/categories
Create new category (authenticated).

## Tags

### GET /api/tags
Get all tags.

### POST /api/tags
Create new tag (authenticated).

## Comments

### GET /api/posts/{postId}/comments
Get comments for a post.

### POST /api/posts/{postId}/comments
Add comment to post (authenticated).

**Request:**
```json
{
  "content": "Great article! Thanks for sharing.",
  "parentId": null
}
```

## Error Responses

### 400 Bad Request
```json
{
  "error": "VALIDATION_ERROR",
  "message": "Invalid input data",
  "details": {
    "title": "Title is required"
  }
}
```

### 401 Unauthorized
```json
{
  "error": "UNAUTHORIZED",
  "message": "Authentication required"
}
```

### 404 Not Found
```json
{
  "error": "NOT_FOUND",
  "message": "Post not found"
}
```