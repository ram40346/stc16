package org.lesson14.jdbc;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.lesson14.tablecontents.Role;
import org.lesson14.tablecontents.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import static org.lesson14.jdbc.UpDate.*;

/**
 * Вводит данные в консоль
 */

@Slf4j
@UtilityClass
public class UserScanner {

    public static User scanUserValues(Scanner scanner) {
        try {
            log.info("Ввод данных в консоль");
            System.out.println(INSERT + USER_NAME);
            String name = scanner.nextLine();
            System.out.println(INSERT + USER_EMAIL);
            String email = scanner.nextLine();
            System.out.println(INSERT + USER_BIRTHDAY + " в формате yyyy-mm-dd");
            String birthday = scanner.nextLine();
            System.out.println(INSERT + USER_CITY);
            String city = scanner.nextLine();
            System.out.println(INSERT + USER_DESCRIPTION);
            String description = scanner.nextLine();
            System.out.println(INSERT + "роль");
            String roleName = scanner.nextLine();
            DateTimeFormatter dateFormat = DateTimeFormatter.ISO_LOCAL_DATE;
            LocalDate startDate = LocalDate.parse(birthday, dateFormat);
            log.info("Возвращаем заполненое поле таблицы user");
            return new User(name, startDate, city, email, description, Role.findByName(roleName));
        } catch (DateTimeParseException e) {
            log.error("Не удалость прочитать дату\n" + e.getMessage());
        }
        return null;
    }
}

