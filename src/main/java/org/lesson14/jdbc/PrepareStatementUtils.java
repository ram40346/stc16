package org.lesson14.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.lesson14.tablecontents.Role;
import org.lesson14.tablecontents.User;

import java.sql.*;

@Slf4j
public class PrepareStatementUtils {

    Connection connection = DBConnection.getConnection();


    /**
     * Заполняет поле в таблице user
     *
     * @param user
     * @return
     */
    public PreparedStatement getUserInsertStatement(User user) {
        try {
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
            return userStatement;
        } catch (SQLException e) {
            log.error("Ошибка " + e.getMessage());
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
            String insertRoleSQL = "INSERT INTO public.role(id, name, description) "
                    + "VALUES (?,?,?)";
            PreparedStatement roleStatement = connection.prepareStatement(insertRoleSQL);
            roleStatement.setInt(1, role.getId());
            roleStatement.setString(2, role.getName());
            roleStatement.setString(3, role.getDescription());
            return roleStatement;
        } catch (SQLException e) {
            log.error("Ошибка " + e.getMessage());
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
            String insertSQL = "INSERT INTO public.user_role(id, user_id, role_id) "
                    + "VALUES (?,?,?)";
            PreparedStatement userRoleStatement = connection.prepareStatement(insertSQL);
            userRoleStatement.setInt(1, RandomUtils.nextInt());
            userRoleStatement.setInt(2, user.getId());
            userRoleStatement.setInt(3, user.getRole().getId());
            return userRoleStatement;
        } catch (SQLException e) {
            log.error("Ошибка " + e.getMessage());
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
            String selectSQL = "SELECT * FROM public.user WHERE name = ? and login_id = ?";
            PreparedStatement userRoleStatement = connection.prepareStatement(selectSQL);
            userRoleStatement.setString(1, userName);
            userRoleStatement.setInt(2, userLoginID);
            return userRoleStatement;
        } catch (SQLException e) {
            log.error("Ошибка " + e.getMessage());
        }
        return null;
    }

    /**
     *  Выборка по полю user_id в таблицу role_id
     * @param userID
     * @return
     */

    public PreparedStatement selectRoleIdByUserId(int userID) {
        try {
            String selectSQL = "SELECT role_id from public.user_role where user_id = ?";
            PreparedStatement roleIdSelect = connection.prepareStatement(selectSQL);
            roleIdSelect.setInt(1, userID);
            return roleIdSelect;
        } catch (SQLException e) {
            log.error("Ошибка " + e.getMessage());
        }
        return null;
    }

    public  PreparedStatement getRoleIds() {
        try {
            String selectSQL = "SELECT id from public.role";
            PreparedStatement roleIdSelect = connection.prepareStatement(selectSQL);
            return roleIdSelect;
        } catch (SQLException e) {
            log.error("Ошибка " + e.getMessage());
        }
        return null;
    }

    public PreparedStatement getAllUesrIdsStatement() {
        try {
            String selectSQL = "SELECT id from public.user";
            PreparedStatement roleIdSelect = connection.prepareStatement(selectSQL);
            return roleIdSelect;
        } catch (SQLException e) {
            log.error("Ошибка " + e.getMessage());
        }
        return null;
    }
    public PreparedStatement getAllUesrRolesStatement() {
        try {
            String selectSQL = "SELECT * from public.user_role";
            PreparedStatement roleIdSelect = connection.prepareStatement(selectSQL);
            return roleIdSelect;
        } catch (SQLException e) {
            log.error("Ошибка " + e.getMessage());
        }
        return null;
    }
}


