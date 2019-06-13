package org.lesson14.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.lesson14.tablecontents.Role;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static org.lesson14.jdbc.UpDate.*;

/**
 * Работа с транзакциями
 */

@Slf4j
public class WorkWithSavePoints {
    public static void main(String[] args) throws SQLException {
        Connection connection = DBConnection.getConnection();
        connection.setAutoCommit(false);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите " + USER_ID);
        String id = scanner.nextLine();
        System.out.println("Введите " + USER_NAME);
        String name = scanner.nextLine();
        System.out.println("Введите " + USER_EMAIL);
        String email = scanner.nextLine();
        System.out.println("Введите " + USER_BIRTHDAY + " в формате yyyy-mm-dd");
        String birthday = scanner.nextLine();
        System.out.println("Введите " + USER_LOGIN_ID);
        String loginId = scanner.nextLine();
        System.out.println("Введите " + USER_CITY);
        String city = scanner.nextLine();
        System.out.println("Введите " + USER_DESCRIPTION);
        String description = scanner.nextLine();
        System.out.println("Введите роль");
        String roleName = scanner.nextLine();
        DateTimeFormatter dateFormat = DateTimeFormatter.ISO_LOCAL_DATE;
        LocalDate startDate = LocalDate.parse(birthday, dateFormat);
        Savepoint savepoint = connection.setSavepoint();

        try {
        String insertSQL = "INSERT INTO public.user_role(id, user_id, role_id) "
                + "VALUES (?,?,?)";
        PreparedStatement userRoleStatement = connection.prepareStatement(insertSQL);
        userRoleStatement.setInt(1, RandomUtils.nextInt());
        userRoleStatement.setInt(2, Integer.valueOf(id));
        userRoleStatement.setInt(3, Role.findByName(roleName).getId());
        userRoleStatement.executeUpdate();


            String insertUserSQL = "INSERT INTO public.user(id, name, birthday, login_id, city, email, description) "
                    + "VALUES (?,?,?,?,?,?,?)";
            PreparedStatement userStatement = connection.prepareStatement(insertUserSQL);
            userStatement.setInt(1, Integer.valueOf(id));
            userStatement.setString(2, name);
            userStatement.setDate(3, Date.valueOf(birthday));
            userStatement.setInt(4, Integer.valueOf(loginId));
            userStatement.setString(5, city);
            userStatement.setString(6, email);
            userStatement.setString(7, description);
            userStatement.executeUpdate();
            connection.commit();

        } catch (SQLException | NumberFormatException | NullPointerException e) {
            connection.rollback(savepoint);
            log.error(e.getMessage());
        } finally {
            connection.close();
        }
    }
}

