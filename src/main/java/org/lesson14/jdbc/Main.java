package org.lesson14.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.lesson14.tablecontents.Role;
import org.lesson14.tablecontents.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 *  Демонстрация записи в БД и запросы в базы данных
 */
@Slf4j
public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        CreateTables createTables = new CreateTables();
        System.out.println("Создавать таблицы? (Y/N)");
        if (scanner.nextLine().toLowerCase().equals("y")) {
            createTables.createUserRoleTable();
            createTables.createRoleTable();
            createTables.createUserTable();
        }
        UpDate upDate = new UpDate();
        System.out.println("Заполнять таблицу ролей? (Y/N)");
        if (scanner.nextLine().toLowerCase().equals("y")) {
            upDate.insertRole(Role.ADMINISTRATOR);
            upDate.insertRole(Role.CLIENT);
            upDate.insertRole(Role.BILLING);
        }
        ArrayList<User> userArrayList = new ArrayList<>();
        System.out.println("Введите любой символ чтобы начать вводить нового пользователя.\n" +
                "Введите пустую строку чтобы завершить");

        while (true) {
            String line = scanner.nextLine();
            if (line.equals(EMPTY)) {
                break;
            } else {
                User user = UserScanner.scanUserValues(scanner);
                userArrayList.add(user);
                if (userArrayList.size() == 1) {
                    upDate.insertUser(userArrayList.get(0));
                }
                upDate.insertUsersWithBatch(userArrayList);
                System.out.println("Введите любой символ чтобы продолжить вводить других пользователей.\n" +
                        "Введите пустую строку чтобы завершить");
            }
        }

        System.out.println("Для поиска пользователей по таблице" +
                " введите данные пользователя: name и в следующей строке login_id");
        String name = scanner.nextLine();
        String loginId = scanner.nextLine();

        List<User> users = upDate.selectUserByName(name, Integer.valueOf(loginId));
        log.info(users.stream().map(user -> user.toString()).collect(Collectors.joining("\n")));
        DBConnection.getConnection().close();
    }
}

