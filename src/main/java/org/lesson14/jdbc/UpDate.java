package org.lesson14.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.lesson14.tablecontents.Role;
import org.lesson14.tablecontents.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс заполняет таблицы в БД
 */

@Slf4j
public class UpDate {

    public static final String USER_ID = "id";
    public static final String ROLE_ID = "role_id";
    public static final String USER_NAME = "name";
    public static final String USER_BIRTHDAY = "birthday";
    public static final String USER_LOGIN_ID = "login_id";
    public static final String USER_CITY = "city";
    public static final String USER_EMAIL = "email";
    public static final String USER_DESCRIPTION = "description";
    private final PrepareStatementUtils prepareStatementUtils;

    UpDate() {
        this(new PrepareStatementUtils());
    }

    UpDate(PrepareStatementUtils prepareStatementUtils) {
        this.prepareStatementUtils = prepareStatementUtils;
    }

    /**
     * Добавляет user в таблицу
     *
     * @param user
     */

    public void insertUser(User user) throws SQLException {
        PreparedStatement userStatement = null;
        PreparedStatement userRoleStatement = null;

        try {
            userStatement = prepareStatementUtils.getUserInsertStatement(user);
            userRoleStatement = prepareStatementUtils.getUserRoleInsertStatement(user);
            userRoleStatement.executeUpdate();
            userStatement.executeUpdate();
            log.info("Использовался метод \"executeUpdate\" Добавлен объект: " + user.toString());
        } finally {
            if (userStatement != null) {
                userStatement.close();
            }
            if (userRoleStatement != null) {
                userRoleStatement.close();
            }
        }
    }

    /**
     * Добавляет user в таблицу через batch
     *
     * @param user
     */

    public void insertUserWithBatch(User user) throws SQLException {
        PreparedStatement userStatement = null;
        PreparedStatement roleUserStatement = null;
        try {
            userStatement = prepareStatementUtils.getUserInsertStatement(user);
            roleUserStatement = prepareStatementUtils.getUserRoleInsertStatement(user);
            roleUserStatement.addBatch();
            roleUserStatement.executeBatch();
            userStatement.addBatch();
            userStatement.executeBatch();
            log.info("Использовался метод \"executeUpdate\" Добавлен объект: " + user.toString());
        } finally {
            if (userStatement != null) {
                userStatement.close();
            }
            if (roleUserStatement != null) {
                roleUserStatement.close();
            }
        }
    }

    /**
     * Добавляет users в таблицу
     *
     * @param users
     */

    public void insertUsersWithBatch(List<User> users) throws SQLException {
        PreparedStatement userStatement = null;
        PreparedStatement roleUserStatement = null;
        try {
            for (User user : users) {

                userStatement = prepareStatementUtils.getUserInsertStatement(user);
                roleUserStatement = prepareStatementUtils.getUserRoleInsertStatement(user);
                roleUserStatement.addBatch();
                userStatement.addBatch();
                roleUserStatement.executeBatch();
                userStatement.executeBatch();
            }
            log.info("Использовался метод \"executeUpdate\" Добавлены объекты: " + users.toString());
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            if (userStatement != null) {
                userStatement.close();
            }

        }
    }

    /**
     * Заполняет таблицу role через batch
     *
     * @param role
     */

    public void insertRoleWithBatch(Role role) throws SQLException {
        PreparedStatement roleStatement = null;
        try {
            roleStatement = prepareStatementUtils.getRoleInsertStatement(role);
            roleStatement.addBatch();
            roleStatement.executeBatch();
        } finally {
            if (roleStatement != null) {
                roleStatement.close();
            }
        }
    }

    /**
     * Заполняет таблицу role
     *
     * @param role
     */
    public void insertRole(Role role) throws SQLException {
        PreparedStatement roleStatement = null;
        try {
            roleStatement = prepareStatementUtils.getRoleInsertStatement(role);
            roleStatement.executeUpdate();
        } finally {
            if (roleStatement != null) {
                roleStatement.close();
            }
        }
    }

    /**
     * Выбирает user по имени
     *
     * @param userName
     * @param userLoginId
     * @return
     */
    public List<User> selectUserByNameAndLoginId(String userName, int userLoginId) throws SQLException {
        PreparedStatement selectName = null;
        PreparedStatement selectRoleId = null;

        try {
            selectName = prepareStatementUtils.selectUser(userName, userLoginId);
            ResultSet resultSet = selectName.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt(USER_ID);
                selectRoleId = prepareStatementUtils.selectRoleIdByUserId(id);
                ResultSet setResult = selectRoleId.executeQuery();
                setResult.next();
                int roleId = setResult.getInt(ROLE_ID);
                String name = resultSet.getString(USER_NAME);
                LocalDate birthday = resultSet.getDate(USER_BIRTHDAY).toLocalDate();
                int loginId = resultSet.getInt(USER_LOGIN_ID);
                String city = resultSet.getString(USER_CITY);
                String email = resultSet.getString(USER_EMAIL);
                String description = resultSet.getString(USER_DESCRIPTION);

                User user = new User(id, name, birthday, loginId, city, email, description, Role.findById(roleId));
                users.add(user);
            }
            return users;

        } finally {
            if (selectName != null) {
                selectName.close();
            }
            if (selectRoleId != null) {
                selectRoleId.close();
            }
        }
    }
}


