package com.bookstore.service;

import com.bookstore.database.DBConnection;
import com.bookstore.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookServiceDao implements BookService {
    @Override
    public void addBook(Book book) {
        try (Connection connection = DBConnection.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement("INSERT INTO books (title, author, price, quantity) VALUES (?, ?, ?, ?)")) {
                ps.setString(1, book.getTitle());
                ps.setString(2, book.getAuthor());
                ps.setDouble(3, book.getPrice());
                ps.setInt(4, book.getQuantity());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Book> getAllBooks() {
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
                        rs.getInt("quantity"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    @Override
    public Book getBookById(int id) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM books WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Book(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("quantity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateBook(Book book) {
        try (Connection connection = DBConnection.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement("UPDATE books SET title = ?, author = ?, price = ?, quantity = ? WHERE id = ?")) {
                ps.setString(1, book.getTitle());
                ps.setString(2, book.getAuthor());
                ps.setDouble(3, book.getPrice());
                ps.setInt(4, book.getQuantity());

                ps.setInt(5, book.getId());

                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBook(int id) {
        try (Connection connection = DBConnection.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement("DELETE FROM books WHERE id = ?")) {
                ps.setInt(1, id);
                ps.executeUpdate();
                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
