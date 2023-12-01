# BookStore Inventory Management API
Mini project for Week 13 Day 05 of Startsteps x Zalando Java Program
Click [here](https://github.com/marishkazachariah/bookstore-inventory-management-api/pull/1) for the PR

## Postman Collection of Requests
I couldn't figure out how to use [Swagger](https://app.swaggerhub.com/) without wasting too much time, so I'm listing the endpoints here:

### For books
- `GET http://localhost:8080/books/` fetches all books
- `GET http://localhost:8080/books/$id` fetches a specific book by its id number
- `POST http://localhost:8080/books/` posts a book using the following JSON body parameter:
```
{
    "title":"Do Androids Dream of Electric Sheep",
    "author":"Phillip K. Dick",
    "price": "phillipkdick@example.com"
    "quantity": 123,
    "author_id": 2
}
```
- `PUT http://localhost:8080/books/$id` edit a specific book by its id number and altering its content with the JSON body parameter
- `DELETE http://localhost:8080/books/$id` deletes a book from the database by its id number

### For authors
- `GET http://localhost:8080/authors/` fetches all authors
- `GET http://localhost:8080/authors/$id` fetches a specific author by its id number
- `POST http://localhost:8080/authors/` posts an author using the following JSON body parameter:
```
{
    "id":"43",
    "firstName":"Torrey",
    "lastName": "Peters"
    "email": "tpeters@gmail.com",
}
```
- `PUT http://localhost:8080/authors/$id` edit a specific author by its id number and altering its content with the JSON body parameter
- `DELETE http://localhost:8080/authors/$id` deletes an author from the database by its id number. Books should be deleted from the database if author is deleted

## Tech Stack used
- Backend: Java with JDBC
- Database: MySQL or another SQL-based database
- API Testing: Postman

## Maven Dependencies used
- MySQL Connector: for connecting MySQL with JDBC
- Lombok for simplified model/object creation
- JSON to parse data into JSON objects
- [Glassfish Jersey](https://mvnrepository.com/artifact/org.glassfish.jersey)

## Requirements
Outline specific scenarios and expected outcomes for testing each endpoint.
- Test GET Endpoints:
- [x] Test listing all books and retrieving specific books.
- Test POST Endpoint:
- [x] Test adding a new book with different data sets.
- [x] Implement and Test PUT and DELETE Endpoints (if part of your API):
- [x] Test updating and deleting book records.
- Validate Responses:
- [x] Ensure correct status codes and data are returned.

## Optional Requirements (Afternoon Exercises)
### Task 5: Extend Database Schema
1. Design Author Table Schema:
- [x] Define column types and constraints for authors (e.g., author_id as PRIMARY KEY).
2. Create Authors Table in Database:
- [x] Write and execute SQL script to create the authors table in the bookstore database.
3. Establish Foreign Key Relationship:
- [x] Modify the books table to include author_id as a foreign key.
- [x] Ensure referential integrity between books and authors.
```
ALTER TABLE books ADD FOREIGN KEY (author_id) REFERENCES authors(id) ON DELETE CASCADE;
```
5. Populate Authors Table:
- [x] Insert sample data into the authors table for testing purposes.

### Task 6: Modify CRUD Operations to Include Authors
1. Update BookResource for Author Integration:
- [x] Modify the data model of Book to include author_id.
- [x] Update addBook, updateBook, and deleteBook methods to handle the new
author_id field.
2. Develop AuthorResource Class:
- [x] Create AuthorResource with methods like getAllAuthors(), getAuthorById(int
authorId), addAuthor(Author author), etc.
- [x] Implement JDBC operations for each of these methods.
3. Integrate Author Details in Book Queries:
- [x] Modify book-related queries to join with the authors table and fetch author details.
- *Personal note: While I implemented the join method in the BookServiceDao, I could not get it work in BookResources/with REST services*
