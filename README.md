# Library Management System for ProductDock

## Overview

This application is designed to help users browse, reserve, and rent books from the library. In addition, it supports book recommendations for purchase, ratings, and search functionalities. The application is available for both web and mobile devices, providing access to different user roles, including admins and regular users.

## Features

- **Browse books**: Explore the complete collection of books available in the library.
- **Book reservation and rental**: Easily reserve or rent books.
- **Book recommendations**: Suggesting new books to add to the library's collection.
- **Book rating**: Rate books based on your reading experience.
- **Search functionality**: Quickly find books by title, author, or genre.
- **User roles**: Distinct access levels for admin and regular users.

## Technologies Used
- **Java 21** with **Spring Boot**
- **MongoDB** for database management
- **Docker** for containerization
## Getting Started
### Prerequisites
- Java 21
- Maven
- Docker

### Running the Application

To run the application locally, use the following methods:

#### Using Docker

To run the application using Docker, you can do so with the following command:

```bash
docker-compose up -d
```

There is an initialization script (mongo-init.js) that automatically populates the MongoDB database
with necessary data. It requires images (book cover images) to be available locally for the
application to fetch and display books correctly.

Each book in the database has an imageUrl that specifies the path to its cover image, and these
images need to be stored in the corresponding location on your machine.
Make sure you have the necessary image files stored in the corresponding directory. If you don’t
have the images, you may need to update the paths accordingly.

#### Using Maven

Run the application with the following command:

```bash
./mvnw clean spring-boot:run
```

By default, the application will run on [http://localhost:8080](http://localhost:8080).

### OpenAPI Documentation

OpenAPI (Swagger) is included in the project by default. You can access the Swagger UI at:
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
