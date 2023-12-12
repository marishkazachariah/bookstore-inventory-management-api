package com.bookstore.service;

import com.bookstore.model.Author;

import java.sql.SQLException;
import java.util.List;

public interface AuthorService {
    void addAuthor(Author author) throws SQLException;
    List<Author> getAllAuthors() throws SQLException;
    Author getAuthorById(int id) throws SQLException;
    void updateAuthor(Author author) throws SQLException;
    void deleteAuthor(int id) throws SQLException;
}

