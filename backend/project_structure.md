
|
├── api                  # API layer (Controllers for REST endpoints)
│   ├── v1               # Versioning support for REST APIs
│   │   ├── PostController.java
│   │   ├── UserController.java
│   │   ├── CommentController.java
│
├── config               # Configuration files and classes
│   ├── SwaggerConfig.java
│   ├── SecurityConfig.java
│   ├── AppProperties.java
│
├── core                 # Core logic and domain layer (shared across services)
│   ├── exception        # Custom exceptions
│   │   ├── GlobalExceptionHandler.java
│   │   ├── ResourceNotFoundException.java
│   │
│   ├── util             # Utilities (helpers, constants, etc.)
│       ├── DateUtils.java
│       ├── StringUtils.java
│
├── domain               # Domain-specific entities and repositories
│   ├── post             # Entities and repositories for posts
│   │   ├── Post.java
│   │   ├── PostRepository.java
│   │
│   ├── user             # Entities and repositories for users
│   │   ├── User.java
│   │   ├── UserRepository.java
│   │
│   ├── comment          # Entities and repositories for comments
│       ├── Comment.java
│       ├── CommentRepository.java
│
├── dto                  # Data Transfer Objects (DTOs)
│   ├── PostDto.java
│   ├── UserDto.java
│   ├── CommentDto.java
│
├── mapper               # Mapping logic for entities and DTOs
│   ├── PostMapper.java
│   ├── UserMapper.java
│   ├── CommentMapper.java
│
├── service              # Service layer (business logic)
│   ├── post             # Services for posts
│   │   ├── PostService.java
│   │   ├── PostServiceImpl.java
│   │
│   ├── user             # Services for users
│   │   ├── UserService.java
│   │   ├── UserServiceImpl.java
│   │
│   ├── comment          # Services for comments
│       ├── CommentService.java
│       ├── CommentServiceImpl.java
│
└── BlogApplication.java # Main class
