package org.lesson14.jdbc;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;

/**
 * Класс устанавливает соединение с БД
 */

@Slf4j
public class DBConnection {

    private static final String DATA_URL = "jdbc:postgresql://127.0.0.1:5432/postgres";
    private static final String USER_NAME = "postgres";
    private static final String PASS = "qwerty";
    private static Connection connection;

    private DBConnection() {
    }

    public static Connection getConnection(){
        if (connection == null) {
            log.info("connect to the database");
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(DATA_URL, USER_NAME, PASS);
            } catch (SQLException e) {
                log.error("database access error occurs or the url is null" + e.getMessage());
            } catch (ClassNotFoundException e) {
                log.error("Class cannot be located" + e.getMessage());
            }
        }
        return connection;
    }
}








