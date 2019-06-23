package org.lesson14.tablecontents;

import lombok.*;
import org.apache.commons.lang3.RandomUtils;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@EqualsAndHashCode
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

    public User(String name, LocalDate birthday, String city, String email, String description, Role role){
        this.id = RandomUtils.nextInt();
        this.name = name;
        this.birthday = birthday;
        this.loginId = RandomUtils.nextInt();
        this.city = city;
        this.email = email;
        this.description = description;
        this.role = role;
    }

}






