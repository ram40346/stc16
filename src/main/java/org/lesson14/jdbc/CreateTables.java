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

    Connection connection = DBConnection.getConnection();


    /**
     * Создание таблицы user
     */

    public void createUserTable() {
        try {
            String userTableSQL = "CREATE TABLE public.user( id SERIAL primary key," +
                    " name CHARACTER VARYING(30) NOT NULL," +
                    " birthday date NOT NULL," +
                    " login_id INT NOT NULL UNIQUE, " +
                    " city CHARACTER VARYING(30) NOT NULL," +
                    " email CHARACTER VARYING(30) UNIQUE NOT NULL," +
                    " description CHARACTER VARYING(30)," +
                    " CONSTRAINT user_role_user_fk FOREIGN KEY (id) REFERENCES public.user_role (user_id) on update cascade on delete cascade)";

            PreparedStatement userTable = connection.prepareStatement(userTableSQL);
            userTable.executeUpdate();
            log.info("Таблица user создана");
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * Создание таблицы role
     */

    public void createRoleTable() {
        try {
            String roleCreateSQL = "CREATE TABLE public.role" +
                    "(id SERIAL primary key," +
                    "    name CHARACTER VARYING(30) NOT NULL, " +
                    "    description CHARACTER VARYING(30)" +
                    "    )";
            PreparedStatement roleTable = connection.prepareStatement(roleCreateSQL);
            roleTable.executeUpdate();
            log.info("Таблица role создана и заполнена");
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * Создание таблицы user_role
     */

    public void createUserRoleTable() {
        try {
            String userRoleCreateSQL = "CREATE TABLE public.user_role" +
                    "(id SERIAL primary key ," +
                    "    user_id INT NOT NULL UNIQUE," +
                    "    role_id INT NOT NULL" +
                    " )";
            PreparedStatement userRoleTable = connection.prepareStatement(userRoleCreateSQL);
            userRoleTable.executeUpdate();
            log.info("Таблица user_role создана");
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }
}