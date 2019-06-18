package org.lesson14.jdbc;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Класс создаёт таблица
 */

@Slf4j
public class CreateTables {

    Connection connection;

    CreateTables() {
        this.connection = DBConnection.getConnection();
    }

    /**
     * Создание таблицы user
     */

    public void createUserTable() {
        try {
            log.info("Создаём таблицу user");
            String userTableSQL = "CREATE TABLE public.user( id SERIAL primary key," +
                    " name CHARACTER VARYING(255) NOT NULL," +
                    " birthday date NOT NULL," +
                    " login_id INT NOT NULL UNIQUE, " +
                    " city CHARACTER VARYING(255) NOT NULL," +
                    " email CHARACTER VARYING(255) UNIQUE NOT NULL," +
                    " description CHARACTER VARYING(255)," +
                    " CONSTRAINT user_role_user_fk FOREIGN KEY (id) REFERENCES public.user_role (user_id) on update cascade on delete cascade)";

            PreparedStatement userTable = connection.prepareStatement(userTableSQL);
            userTable.executeUpdate();
            log.info("Таблица user создана");
        } catch (SQLException e) {
            log.error("Нет соединения с базой данных", e);
        }
    }

    /**
     * Создание таблицы role
     */

    public void createRoleTable() {
        try {
            log.info("Создаём таблицу role");
            String roleCreateSQL = "CREATE TABLE public.role" +
                    "(id SERIAL primary key," +
                    "    name CHARACTER VARYING(255) NOT NULL, " +
                    "    description CHARACTER VARYING(255)" +
                    "    )";
            PreparedStatement roleTable = connection.prepareStatement(roleCreateSQL);
            roleTable.executeUpdate();
            log.info("Таблица role создана и заполнена");
        } catch (SQLException e) {
            log.error("Нет соединения с базой данных", e);
        }
    }

    /**
     * Создание таблицы user_role
     */

    public void createUserRoleTable() {
        try {
            log.info("Создаём таблицу user_role");
            String userRoleCreateSQL = "CREATE TABLE public.user_role" +
                    "(id SERIAL primary key ," +
                    "    user_id INT NOT NULL UNIQUE," +
                    "    role_id INT NOT NULL" +
                    " )";
            PreparedStatement userRoleTable = connection.prepareStatement(userRoleCreateSQL);
            userRoleTable.executeUpdate();
            log.info("Таблица user_role создана");
        } catch (SQLException e) {
            log.error("Нет соединения с базой данных", e);
        }


    }

    public void createLogTable() {
        try {
            log.info("Собзаём тоблицу для записывания логов");
            String logTableSQL = "CREATE TABLE public.logs(" +
                    "    ID SERIAL, " +
                    "    DATE date," +
                    "     LOG_LEVEL VARCHAR(10)," +
                    "      MESSAGE VARCHAR(1000)," +
                    "      EXCEPTION text)";
            PreparedStatement logTable = connection.prepareStatement(logTableSQL);
            logTable.executeUpdate();
            log.info("Таблица успешно создана");
        } catch (SQLException e) {
            log.error("Нет соединения с базой данных", e);
        }
    }
}
