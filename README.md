# Library Management System

A RESTful Library Management System built using Spring Boot, Spring Data JPA, Hibernate, and MySQL.

## Features

### Book Management

* Add Book
* Update Book
* Delete Book
* Get Book By ID
* Get All Books
* Search Books by Title/Author
* Filter Books by Genre

### Member Management

* Register Member
* Update Member
* Delete Member
* Get Member By ID
* Get All Members

### Borrow Management

* Borrow Book
* Return Book
* Get Borrow Records
* Get Borrow Records By Member
* Get Borrow Records By Status

## Tech Stack

* Java 21
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL
* Lombok
* Maven/Gradle
* Postman

## Database Design

### Book

* id
* title
* author
* isbn
* totalCopies
* availableCopies
* genre

### Member

* id
* name
* email
* phone
* status
* memberSince

### Borrow

* id
* book
* member
* borrowDate
* dueDate
* returnDate
* status

## JPA Relationships

* One Book can have many Borrow Records
* One Member can have many Borrow Records
* Implemented using @ManyToOne relationships

## API Endpoints

### Books

* POST /api/books
* GET /api/books
* GET /api/books/{id}
* PUT /api/books/{id}
* DELETE /api/books/{id}

### Members

* POST /api/members
* GET /api/members
* GET /api/members/{id}
* PUT /api/members/{id}
* DELETE /api/members/{id}

### Borrows

* POST /api/borrows/borrow
* PATCH /api/borrows/return/{borrowId}
* GET /api/borrows
* GET /api/borrows/{id}
* GET /api/borrows/member/{memberId}
* GET /api/borrows/status/{status}

## Future Enhancements

* Global Exception Handler
* DTO Layer
* Swagger/OpenAPI Documentation
* Spring Security & JWT Authentication
* Unit Testing using JUnit and Mockito

## Author

Ankit Kumar
B.Tech Information Technology
Manipal University Jaipur
