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
            log.info("Подключение к БД");
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(DATA_URL, USER_NAME, PASS);
            } catch (SQLException e) {
                log.error("Произошла ошибка доступа к базе данных или URL-адрес равен нулю" + e.getMessage());
            } catch (ClassNotFoundException e) {
                log.error("Класс не может быть определён" + e.getMessage());
            }
        }
        return connection;
    }
}








