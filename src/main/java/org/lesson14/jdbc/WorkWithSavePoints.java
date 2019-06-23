package org.lesson14.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.lesson14.tablecontents.User;

import java.sql.*;
import java.util.Scanner;

/**
 * Работа с транзакциями
 */

@Slf4j
public class WorkWithSavePoints {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        User user = UserScanner.scanUserValues(scanner);

        executeWithSavePoint(user);
    }

    public static void executeWithSavePoint(User user) throws SQLException {
        Connection connection = DBConnection.getConnection();
        connection.setAutoCommit(false);
        Savepoint savepoint = connection.setSavepoint();

        try (PreparedStatement userRoleStatement = connection.prepareStatement("INSERT INTO public.user_role(id, user_id, role_id) "
                + "VALUES (?,?,?)"); PreparedStatement userStatement = connection.prepareStatement("INSERT INTO public.user(id, name, birthday, login_id, city, email, description) "
                + "VALUES (?,?,?,?,?,?,?)")) {
            userRoleStatement.setInt(1, RandomUtils.nextInt());
            userRoleStatement.setInt(2, user.getId());
            userRoleStatement.setInt(3, user.getRole().getId());
            userRoleStatement.executeUpdate();

            userStatement.setInt(1, user.getId());
            userStatement.setString(2, user.getName());
            userStatement.setDate(3, Date.valueOf(user.getBirthday()));
            userStatement.setInt(4, user.getLoginId());
            userStatement.setString(5, user.getCity());
            userStatement.setString(6, user.getEmail());
            userStatement.setString(7, user.getDescription());
            userStatement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException | NumberFormatException | NullPointerException e) {
            log.error(e.getMessage());
            connection.rollback(savepoint);
            connection.setAutoCommit(true);
        }
    }
}

