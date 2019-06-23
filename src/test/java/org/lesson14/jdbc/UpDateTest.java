package org.lesson14.jdbc;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.lesson14.tablecontents.Role;
import org.lesson14.tablecontents.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class UpDateTest {
    static PrepareStatementUtils prepareStatementUtils;
    static User expectedUser;
    static User expectedUser1;
    static UpDate upDate;
    static Connection connection;

    @BeforeAll
    static void setUp() throws SQLException {
        prepareStatementUtils = new PrepareStatementUtils();
        connection = DBConnection.getConnection();
        expectedUser = new User("Имя", LocalDate.now(), "Город", "email@mail.ru", "qwerty", Role.CLIENT);
        expectedUser1 = new User("Name", LocalDate.now(), "City", "email@rambler.ru", "description", Role.ADMINISTRATOR);
        CreateTables createTables = new CreateTables();
        upDate = new UpDate();
        createTables.createUserRoleTable();
        createTables.createRoleTable();
        createTables.createUserTable();
        upDate. insertRole(Role.ADMINISTRATOR);
        upDate.insertRole(Role.CLIENT);

    }

    @Test
    void insertUserTest() throws SQLException {
        upDate.insertUser(expectedUser);
        User actualUser = upDate.selectUserByNameAndLoginId("Имя", expectedUser.getLoginId()).get(0);
        assertEquals(actualUser, expectedUser);
    }

    @Test
    void selectUserTest() throws SQLException {
        upDate.insertUserWithBatch(expectedUser1);
        User actualUser = upDate.selectUserByNameAndLoginId(expectedUser1.getName(), expectedUser1.getLoginId()).get(0);
        assertEquals(actualUser, expectedUser1);
    }

    @Test
    void insertUsersWithBatchTest() throws SQLException {
        List<User> userExpectedList = new ArrayList<>();
        userExpectedList.add(expectedUser);
        userExpectedList.add(expectedUser1);

        upDate.insertUsersWithBatch(userExpectedList);
        List<User> users = upDate.selectUserByNameAndLoginId(expectedUser.getName(), expectedUser.getLoginId());
        User actualUser1 = users.get(0);
        User actualUser2 = upDate.selectUserByNameAndLoginId(expectedUser1.getName(), expectedUser1.getLoginId()).get(0);

        assertThat(userExpectedList).contains(actualUser1);
        assertThat(userExpectedList).contains(actualUser2);
    }


    @Test
    void insertUserWithBatchTest() throws SQLException {
        upDate.insertUserWithBatch(expectedUser);
        List<User> users = upDate.selectUserByNameAndLoginId(expectedUser.getName(), expectedUser.getLoginId());
        User actualUser1 = users.get(0);
        assertEquals(expectedUser, actualUser1);
    }

    @Test
    void insertRole() throws SQLException {
        upDate.insertRole(Role.BILLING);
        List<Role> actualRoles = getRoles();
        assertThat(actualRoles).contains(Role.BILLING);
    }

    @Test
    void insertRoleWithBatch() throws SQLException {
        upDate.insertRoleWithBatch(Role.BILLING);
        List<Role> actualRoles = getRoles();
        assertThat(actualRoles).contains(Role.BILLING);
    }

    private List<Role> getRoles() throws SQLException {
        List<Role> actualRoles = new ArrayList<>();
        ResultSet rolesSet = prepareStatementUtils.getRoleIds().executeQuery();
        while (rolesSet.next()) {
            actualRoles.add(Role.findById(rolesSet.getInt("id")));
        }
        return actualRoles;
    }

    @Test
    void workWithSavePointsTest() throws SQLException {
        upDate.insertUser(expectedUser);
        String badName = "badName";
        int loginId = 2345;
        User badUser = new User(expectedUser.getId(), badName, LocalDate.now(), loginId, "ny", "", "", Role.ADMINISTRATOR);
        WorkWithSavePoints.executeWithSavePoint(badUser);
        assertTrue(upDate.selectUserByNameAndLoginId(badName, loginId).isEmpty());
    }

    @AfterAll
    static void tearDown() throws SQLException {
        Connection connection = DBConnection.getConnection();
        String dropUserTableSQL = "DROP TABLE public.user";
        String dropRoleTableSQL = "DROP TABLE public.role";
        String dropUserRoleTableSQL = "DROP TABLE public.user_role";

        PreparedStatement userTable = connection.prepareStatement(dropUserTableSQL);
        PreparedStatement userRoleTable = connection.prepareStatement(dropUserRoleTableSQL);
        PreparedStatement roleTable = connection.prepareStatement(dropRoleTableSQL);
        userTable.executeUpdate();
        userRoleTable.executeUpdate();
        roleTable.executeUpdate();
    }
}