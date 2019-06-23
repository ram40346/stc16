package org.lesson14.jdbc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lesson14.tablecontents.User;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.lesson14.jdbc.UpDate.*;
import static org.lesson14.tablecontents.Role.*;
import static org.mockito.Mockito.*;

public class UpDateMockAndSpy {
    PrepareStatementUtils prepareStatementUtils;
    UpDate upDate;

    int userId = 123;
    Date birthday = new Date(1234512);
    String mockMail = "mockMail@kdf.ru";
    String mockCity = "mockCity";
    String userName = "mockUserName";
    int loginId = 111;

    @BeforeEach
    void setUp() throws SQLException {
        prepareStatementUtils = spy(new PrepareStatementUtils());
        PreparedStatement mockPreparedRoleStatement = mock(PreparedStatement.class);
        when(prepareStatementUtils.getRoleIds()).thenReturn(mockPreparedRoleStatement);
        ResultSet mockRoleResultSet = mock(ResultSet.class);
        when(mockPreparedRoleStatement.executeQuery()).thenReturn(mockRoleResultSet);
        when(mockRoleResultSet.next())
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(false);
        when(mockRoleResultSet.getInt(eq("id")))
                .thenReturn(CLIENT.getId())
                .thenReturn(ADMINISTRATOR.getId())
                .thenReturn(BILLING.getId());

        PreparedStatement userStatement = mock(PreparedStatement.class);
        PreparedStatement roleStatement = mock(PreparedStatement.class);
        when(prepareStatementUtils.selectUser(eq(userName), eq(loginId))).thenReturn(userStatement);
        ResultSet userSet = mock(ResultSet.class);
        when(userSet.next()).thenReturn(TRUE).thenReturn(FALSE);
        when(userSet.getInt(eq(USER_ID))).thenReturn(userId);
        when(userSet.getDate(eq(USER_BIRTHDAY))).thenReturn(birthday);
        when(userSet.getString(eq(USER_NAME))).thenReturn(userName);
        when(userSet.getInt(eq(USER_LOGIN_ID))).thenReturn(loginId);
        when(userSet.getString(eq(USER_CITY))).thenReturn(mockCity);
        when(userSet.getString(eq(USER_EMAIL))).thenReturn(mockMail);

        when(userStatement.executeQuery()).thenReturn(userSet);
        when(prepareStatementUtils.selectRoleIdByUserId(eq(userId))).thenReturn(roleStatement);
        ResultSet roleResult = mock(ResultSet.class);
        when(roleResult.next()).thenReturn(TRUE).thenReturn(FALSE);
        when(roleStatement.executeQuery()).thenReturn(roleResult);
        when(roleResult.getInt(eq(ROLE_ID))).thenReturn(ADMINISTRATOR.getId());

        upDate = new UpDate(prepareStatementUtils);
    }

    @Test
    void selectUserTest() throws SQLException {
        User actualUser = upDate.selectUserByNameAndLoginId(userName, loginId).get(0);
        assertEquals(actualUser.getId(), userId);
        assertEquals(actualUser.getName(), userName);
        assertEquals(actualUser.getLoginId(), loginId);
        assertEquals(actualUser.getRole(), ADMINISTRATOR);
        assertEquals(actualUser.getBirthday(), birthday.toLocalDate());
        assertEquals(actualUser.getCity(), mockCity);
        assertEquals(actualUser.getEmail(), mockMail);
    }
}
