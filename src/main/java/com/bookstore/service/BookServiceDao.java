package com.bookstore.service;

import com.bookstore.database.DBConnection;
import com.bookstore.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookServiceDao implements BookService {
    @Override
    public void addBook(Book book) throws SQLException {
        try (Connection connection = DBConnection.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement("INSERT INTO books (title, author, price, quantity, author_id) VALUES (?, ?, ?, ?, ?)")) {
                ps.setString(1, book.getTitle());
                ps.setString(2, book.getAuthor());
                ps.setDouble(3, book.getPrice());
                ps.setInt(4, book.getQuantity());
                ps.setInt(5, book.getAuthorId());
                ps.executeUpdate();
            }
        }
    }

    @Override
    public List<Book> getAllBooks() throws SQLException {
        List<Book> books = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM books")) {

            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getInt("author_id"));

                books.add(book);
            }
        }
        return books;
    }

    @Override
    public Book getBookById(int id) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM books WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Book(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("quantity"),
                        resultSet.getInt("author_id"));
            }
        }
        return null;
    }

    @Override
    public void updateBook(Book book) throws SQLException {
        try (Connection connection = DBConnection.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement("UPDATE books SET title = ?, author = ?, price = ?, quantity = ?, author_id = ? WHERE id = ?")) {
                ps.setString(1, book.getTitle());
                ps.setString(2, book.getAuthor());
                ps.setDouble(3, book.getPrice());
                ps.setInt(4, book.getQuantity());
                ps.setInt(5, book.getAuthorId());

                ps.setInt(6, book.getId());

                ps.executeUpdate();
            }
        }
    }

    @Override
    public void deleteBook(int id) throws SQLException {
        try (Connection connection = DBConnection.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement("DELETE FROM books WHERE id = ?")) {
                ps.setInt(1, id);
                ps.executeUpdate();
                connection.commit();
            }
        }
    }

    @Override
    public List<Book> getBooksByAuthor(String authorName) throws SQLException {
        List<Book> booksByAuthor = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection()) {
            String sql = "SELECT books.id, books.title, books.price, books.quantity, books.author_id, " +
                    "authors.first_name, authors.last_name, authors.email " +
                    "FROM books " +
                    "JOIN authors ON books.author_id = authors.id " +
                    "WHERE authors.first_name = ? OR authors.last_name = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                int idx = authorName.lastIndexOf(' ');
                if (idx == -1)
                    throw new IllegalArgumentException("Only a single name: " + authorName);
                String firstName = authorName.substring(0, idx);
                String lastName  = authorName.substring(idx + 1);
                statement.setString(1, firstName);
                statement.setString(2, lastName);

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Book book = new Book();
                        book.setId(resultSet.getInt("id"));
                        book.setTitle(resultSet.getString("title"));
                        book.setPrice(resultSet.getDouble("price"));
                        book.setQuantity(resultSet.getInt("quantity"));
                        book.setAuthorId(resultSet.getInt("author_id"));

                        booksByAuthor.add(book);
                    }
                }
            }
        }
        return booksByAuthor;
    }
}
