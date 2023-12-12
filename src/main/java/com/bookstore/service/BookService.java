package com.bookstore.service;

import com.bookstore.model.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookService {
    void addBook(Book book) throws SQLException;

    List<Book> getAllBooks() throws SQLException;

    Book getBookById(int id) throws SQLException;

    void updateBook(Book book) throws SQLException;

    void deleteBook(int id) throws SQLException;

    List<Book> getBooksByAuthor(String author) throws SQLException;
}
