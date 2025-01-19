# Blog Application - System Design

## Features
1. **User Management**
    - Registration and Login (roles: Admin, Author, Reader)
    - Profile management

2. **Post Management**
    - Create, update, delete, and view blog posts
    - Support for rich text, images, and categories
    - Tagging for posts

3. **Comment Management**
    - Add, edit, delete, and view comments
    - Support for nested (threaded) comments

4. **Like System**
    - Users can like posts and comments

5. **Search & Filtering**
    - Search posts by title, tags, or content
    - Filter by category, date, and author

6. **Admin Panel**
    - Manage users, posts, comments, and tags
    - Monitor platform activity



## Relationships

1. **User - Post:**
    - **Type:** One-to-Many
    - **Description:** A user can write multiple posts.

2. **User - Comment:**
    - **Type:** One-to-Many
    - **Description:** A user can write multiple comments.

3. **Post - Comment:**
    - **Type:** One-to-Many
    - **Description:** A post can have multiple comments.

4. **Post - Tag:**
    - **Type:** Many-to-Many
    - **Description:** A post can have multiple tags, and a tag can belong to multiple posts.

5. **Post/Comment - Like:**
    - **Type:** Many-to-One
    - **Description:** Users can like posts or comments.

---