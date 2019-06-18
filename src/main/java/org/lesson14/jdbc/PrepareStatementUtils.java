package org.lesson14.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.lesson14.tablecontents.Role;
import org.lesson14.tablecontents.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.lesson14.jdbc.UpDate.*;

@Slf4j
public class PrepareStatementUtils {



    Connection connection;

    public PrepareStatementUtils() {
        this.connection = DBConnection.getConnection();
    }

    /**
     * Заполняет поле в таблице user
     *
     * @param user
     * @return
     */
    public PreparedStatement getUserInsertStatement(User user) {
        try {
            log.info("Начинаю заполнять поле в таблице user");
            String insertSQL = "INSERT INTO public.user(id, name, birthday, login_id, city, email, description) "
                    + "VALUES (?,?,?,?,?,?,?)";
            PreparedStatement userStatement = connection.prepareStatement(insertSQL);
            userStatement.setInt(1, user.getId());
            userStatement.setString(2, user.getName());
            userStatement.setDate(3, Date.valueOf(user.getBirthday()));
            userStatement.setInt(4, user.getLoginId());
            userStatement.setString(5, user.getCity());
            userStatement.setString(6, user.getEmail());
            userStatement.setString(7, user.getDescription());
            log.info("Выполнен запрос: INSERT INTO public.user(id, name, birthday, login_id, city, email, description) " +
                            "VALUES ({},{},{},{},{},{},{}", user.getId(), user.getName(), Date.valueOf(user.getBirthday()),
                    user.getLoginId(), user.getCity(), user.getEmail(), user.getDescription());
            return userStatement;
        } catch (SQLException e) {
            log.error(ERROR + e.getMessage());
        }
        return null;
    }

    /**
     * Заполняет поле в таблице role
     *
     * @param role
     * @return
     */
    public PreparedStatement getRoleInsertStatement(Role role) {
        try {
            log.info("Начинаем заполнение поле в таблице role");
            String insertRoleSQL = "INSERT INTO public.role(id, name, description) "
                    + "VALUES (?,?,?)";
            PreparedStatement roleStatement = connection.prepareStatement(insertRoleSQL);
            roleStatement.setInt(1, role.getId());
            roleStatement.setString(2, role.getName());
            roleStatement.setString(3, role.getDescription());
            log.info("Выполнен запрос: INSERT INTO public.role(id, name, description) \"\n" +
                            "                    + \"VALUES ({},{},{})", role.getId(), role.getName(),
                    role.getDescription());
            return roleStatement;
        } catch (SQLException e) {
            log.error(ERROR + e.getMessage());
        }
        return null;
    }

    /**
     * Заполняет поле в таблице user_role
     *
     * @param user
     * @return
     */
    public PreparedStatement getUserRoleInsertStatement(User user) {
        try {
            log.info("Начинаем заполнять поле в таблицу user_role");
            String insertSQL = "INSERT INTO public.user_role(id, user_id, role_id) "
                    + "VALUES (?,?,?)";
            PreparedStatement userRoleStatement = connection.prepareStatement(insertSQL);
            userRoleStatement.setInt(1, RandomUtils.nextInt(MIN_RANDOM, MAX_RANDOM));
            userRoleStatement.setInt(2, user.getId());
            userRoleStatement.setInt(3, user.getRole().getId());
            log.info("Выполнен запрос: INSERT INTO public.user_role(id, user_id, role_id) \"\n" +
                    "                    + \"VALUES ({},{},{})", RandomUtils.nextInt(), user.getId(),
                    user.getRole().getId());
            return userRoleStatement;
        } catch (SQLException e) {
            log.error(ERROR + e.getMessage());
        }
        return null;
    }

    /**
     * Делает выборку по столбцам name и в login_id в таблице user
     *
     * @param userName
     * @param userLoginID
     * @return
     */

    public PreparedStatement selectUser(String userName, int userLoginID) {
        try {
            log.info("Начало выборки по столбцам name и в login_id в таблице user");
            String selectSQL = "SELECT * FROM public.user WHERE name = ? and login_id = ?";
            PreparedStatement userRoleStatement = connection.prepareStatement(selectSQL);
            userRoleStatement.setString(1, userName);
            userRoleStatement.setInt(2, userLoginID);
            log.info("Выполнен запрос: SELECT * FROM public.user WHERE name = {} and login_id = {}", userName,
                    userLoginID);
            return userRoleStatement;
        } catch (SQLException e) {
            log.error(ERROR + e.getMessage());
        }
        return null;
    }

    /**
     * Выборка по полю user_id в таблицу role_id
     *
     * @param userID
     * @return
     */

    public PreparedStatement selectRoleIdByUserId(int userID) {
        try {
            log.info("Начало выборки по полю user_id в таблицу role_id");
            String selectSQL = "SELECT role_id from public.user_role where user_id = ?";
            PreparedStatement roleIdSelect = connection.prepareStatement(selectSQL);
            roleIdSelect.setInt(1, userID);
            log.info("Выполнен запрос: SELECT role_id from public.user_role where user_id = {}", userID);
            return roleIdSelect;
        } catch (SQLException e) {
            log.error(ERROR + e.getMessage());
        }
        return null;
    }
}


