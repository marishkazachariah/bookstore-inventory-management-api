package com.bookstore.service;

import com.bookstore.model.Book;

import java.util.List;

public interface BookService {
    void addBook(Book book);

    List<Book> getAllBooks();

    Book getBookById(int id);

    void updateBook(Book book);

    void deleteBook(int id);

    List<Book> getBooksByAuthor(String author);
}
