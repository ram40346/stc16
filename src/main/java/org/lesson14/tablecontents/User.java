package org.lesson14.tablecontents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.RandomUtils;

import java.time.LocalDate;

import static org.lesson14.jdbc.UpDate.MAX_RANDOM;
import static org.lesson14.jdbc.UpDate.MIN_RANDOM;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class User {

    private int id;
    private String name;
    private LocalDate birthday;
    private int loginId;
    private String city;
    private String email;
    private String description;

    private Role role;

    public User(String name, LocalDate birthday, String city, String email, String description, Role role) {
        this.id = RandomUtils.nextInt(MIN_RANDOM, MAX_RANDOM);
        this.name = name;
        this.birthday = birthday;
        this.loginId = RandomUtils.nextInt(MIN_RANDOM, MAX_RANDOM);
        this.city = city;
        this.email = email;
        this.description = description;
        this.role = role;
    }

}






