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
    public static Connection getConnection() throws DBConnectionException, SQLException {
        String url = System.getenv("dbUrl");
        String user = System.getenv("dbUsername");
        String password = System.getenv("dbPassword");
        return DriverManager.getConnection(url, user, password);
    }
}
