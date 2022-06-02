package com.example.intellijidea_projects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
    public static final String USER_NAME = "root";
    public static final String  PASS = "root";
    public static final String URL = "jdbc:mysql://localhost:3306/demotest";

    public static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Statement statement;

    static {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    ;





}
