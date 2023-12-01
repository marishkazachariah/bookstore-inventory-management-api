# BookStore Inventory Management API
Mini project for Week 13 Day 05 of Startsteps x Zalando Java Program
Click [here](https://github.com/marishkazachariah/bookstore-inventory-management-api/pull/1) for the PR

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

### Optional Requirements (Afternoon Exercises)
#### Task 5: Extend Database Schema
1. Design Author Table Schema:
[x] Define column types and constraints for authors (e.g., author_id as PRIMARY KEY).
2. Create Authors Table in Database:
[x] Write and execute SQL script to create the authors table in the bookstore database.
3. Establish Foreign Key Relationship:
[x] Modify the books table to include author_id as a foreign key.
[x] Ensure referential integrity between books and authors.
```
ALTER TABLE books ADD FOREIGN KEY (author_id) REFERENCES authors(id) ON DELETE CASCADE;
```
5. Populate Authors Table:
[x] Insert sample data into the authors table for testing purposes.
