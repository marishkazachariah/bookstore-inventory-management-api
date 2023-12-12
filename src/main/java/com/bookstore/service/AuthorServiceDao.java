package com.bookstore.service;

import com.bookstore.database.DBConnection;
import com.bookstore.model.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorServiceDao implements AuthorService {
    @Override
    public void addAuthor(Author author) throws SQLException {
        try (Connection connection = DBConnection.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement("INSERT INTO authors (id, first_name, last_name, email) VALUES (?, ?, ?, ?)")) {
                ps.setInt(1, author.getId());
                ps.setString(2, author.getFirstName());
                ps.setString(3, author.getLastName());
                ps.setString(4, author.getEmail());
                ps.executeUpdate();
            }
        }
    }

    @Override
    public List<Author> getAllAuthors() throws SQLException {
        List<Author> authors = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM authors")) {

            while (rs.next()) {
                Author author = new Author(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"));
                authors.add(author);
            }
        }

        return authors;
    }

    @Override
    public Author getAuthorById(int id) throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM authors WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Author(resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"));
            }
        }

        return null;
    }

    @Override
    public void updateAuthor(Author author) throws SQLException {
        try (Connection connection = DBConnection.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement("UPDATE authors SET first_name = ?, last_name = ?, email = ? WHERE id = ?")) {
                ps.setString(1, author.getFirstName());
                ps.setString(2, author.getLastName());
                ps.setString(3, author.getEmail());
                ps.setInt(4, author.getId());
                ps.executeUpdate();
            }
        }
    }

    @Override
    public void deleteAuthor(int id) throws SQLException {
        try (Connection connection = DBConnection.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement("DELETE FROM authors WHERE id = ?")) {
                ps.setInt(1, id);
                ps.executeUpdate();
                connection.commit();
            }
        }
    }
}
