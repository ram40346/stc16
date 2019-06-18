package org.lesson14.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.lesson14.tablecontents.Role;
import org.lesson14.tablecontents.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Класс заполняет таблицы в БД
 */

@Slf4j
public class UpDate {

    public static final String PROPERTIES_URL = "C:\\Users\\Рамиль\\IdeaProjects\\stc16\\src\\main\\resources\\log4j2.properties";
    public static final String INSERT = "Введите ";
    public static final String USER_ID = "id";
    public static final String ROLE_ID = "role_id";
    public static final String USER_NAME = "name";
    public static final String USER_BIRTHDAY = "birthday";
    public static final String USER_LOGIN_ID = "login_id";
    public static final String USER_CITY = "city";
    public static final String USER_EMAIL = "email";
    public static final String USER_DESCRIPTION = "description";
    public static final String ERROR = "Ошибка ";
    private final PrepareStatementUtils prepareStatementUtils = new PrepareStatementUtils();
    public static final int MIN_RANDOM = 1;
    public static final int MAX_RANDOM = 10000;

    /**
     * Добавляет user в таблицу
     *
     * @param user
     */

    public void insertUser(User user) {
        try {
            log.info("Добавляем объект user в таблицу user");
            PreparedStatement userStatement = prepareStatementUtils.getUserInsertStatement(user);
            PreparedStatement userRoleStatement = prepareStatementUtils.getUserRoleInsertStatement(user);
            userRoleStatement.executeUpdate();
            userStatement.executeUpdate();
            log.info("Использовался метод \"executeUpdate\" Добавлен объект: " + user.toString());
        } catch (SQLException e) {
            log.error("Нет доступа к базе", e);
        }
    }

    /**
     * Добавляет user в таблицу через batch
     *
     * @param user
     */

    public void insertUserWithBatch(User user) {
        try {
            log.info("Добавляем объект user в таблицу user");
            PreparedStatement userStatement = prepareStatementUtils.getUserInsertStatement(user);
            PreparedStatement roleUserStatement = prepareStatementUtils.getUserRoleInsertStatement(user);
            roleUserStatement.addBatch();
            roleUserStatement.executeBatch();
            userStatement.addBatch();
            userStatement.executeBatch();
            log.info("Использовался метод \"executeUpdate\" Добавлен объект: " + user.toString());
        } catch (SQLException e) {
            log.error("Нет доступа к базе", e);
        }
    }

    /**
     * Добавляет users в таблицу
     *
     * @param users
     */

    public void insertUsersWithBatch(List<User> users) {
        PreparedStatement userStatement = null;
        try {
            log.info("Добавляем писок users в таблицу user через batch процесс");
            for (User user : users) {
                userStatement = prepareStatementUtils.getUserInsertStatement(user);
                userStatement.addBatch();
            }
            assert userStatement != null;
            userStatement.executeBatch();
            log.info("Использовался метод \"executeUpdate\" Добавлены объекты: " + users.toString());
        } catch (SQLException e) {
            log.error("Нет доступа к базе", e);
        }
    }

    /**
     * Заполняет таблицу role через batch
     *
     * @param role
     */

    public void insertRoleWithBatch(Role role) {
        try {
            log.info("Добавляем объект role в таблицу role через batch процесс");
            PreparedStatement roleStatement = prepareStatementUtils.getRoleInsertStatement(role);
            roleStatement.addBatch();
            roleStatement.executeBatch();
            log.info("Объекст успешно добавлен");
        } catch (SQLException e) {
            log.error("Нет доступа к базе", e);
        }
    }

    /**
     * Заполняет таблицу role
     *
     * @param role
     */
    public void insertRole(Role role) {
        try {
            log.info("Добавляем объект role в таблицу role");
            PreparedStatement roleStatement = prepareStatementUtils.getRoleInsertStatement(role);
            roleStatement.executeUpdate();
            log.info("Объекст успешно добавлен");
        } catch (SQLException e) {
            log.error("Нет доступа к базе", e);
        }
    }

    /**
     * Выбирает user по имени
     *
     * @param userName
     * @param userLoginId
     * @return
     */
    public List<User> selectUserByName(String userName, int userLoginId) {
        try {
            log.info("Достаём объекты user из таблицы user по параметрам name и login_id");
            PreparedStatement selectName = prepareStatementUtils.selectUser(userName, userLoginId);
            ResultSet resultSet = selectName.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt(USER_ID);
                PreparedStatement selectRoleId = prepareStatementUtils.selectRoleIdByUserId(id);
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
                log.info("Объекты успешно выбраны");
            }
            return users;
        } catch (SQLException | NullPointerException e) {
            log.error(ERROR + e.getMessage());
        }
        return Collections.emptyList();
    }

}



