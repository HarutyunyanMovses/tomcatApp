package com.Tomcat.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static final String URL = "jdbc:mysql://localhost:3333/tomcat_db";
    private static final String USER = "root";
    private static final String PASSWORD = "java";
    private static Connection connection = null;


    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                System.out.println("*************************");
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    connection = DriverManager.getConnection(URL,USER,PASSWORD);
                    System.out.println("DB connection Successful ");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static void main(String[] args) {
        ConnectionDB connectionDB = new ConnectionDB();
        connectionDB.getConnection();
    }
}

