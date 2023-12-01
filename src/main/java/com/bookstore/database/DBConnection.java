package com.bookstore.database;

import com.bookstore.Main;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private DBConnection() {
    }

    public static Connection getConnection() throws DBConnectionException {
        Properties prop = new Properties();
        try (InputStream input = Main.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Unable to find config.properties");
                throw new DBConnectionException("Config file not found");
            }

            prop.load(input);
            String url = prop.getProperty("db.url");
            String user = prop.getProperty("db.username");
            String password = prop.getProperty("db.password");

            return DriverManager.getConnection(url, user, password);
        } catch (IOException | SQLException e) {
            throw new DBConnectionException("Error reading config.properties", e);
        }
    }
}
