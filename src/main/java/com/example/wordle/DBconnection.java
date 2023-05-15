package com.example.wordle;

import java.sql.Connection;
import java.sql.DriverManager;
public class DBconnection {
    public Connection dbConnection;

    public Connection getConnection() throws Exception {
        String dbName = "usersinfo";
        String dbUser = "root";
        String dbPassword = "root1234";
        String url = "jdbc:mysql://localhost:3306/" + dbName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbConnection = DriverManager.getConnection(url, dbUser, dbPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dbConnection;
    }
}
