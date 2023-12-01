package com.bookstore.service;

import com.bookstore.model.Author;

import java.util.List;

public interface AuthorService {
    void addAuthor(Author author);

    List<Author> getAllAuthors();

    Author getAuthorById(int id);

    void updateAuthor(Author author);

    void deleteAuthor(int id);
}
