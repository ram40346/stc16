package org.lesson14.tablecontents;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Перечисление ролей
 */
@Getter
public enum Role {
    ADMINISTRATOR(1, "Administrator", "Администратор системы"),
    CLIENT(2, "Client", "Клиент"),
    BILLING(3, "Billing", "Система");

    private int id;
    private String name;
    private String description;

    Role(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static Role findByName(String i) {
        Role[] roles = Role.values();
        for (Role role : roles) {
            if (role.name.equals(i)) {
                return role;
            }
        }
        return null;
    }

    public static Role findById(int i) {
        Role[] roles = Role.values();
        for (Role role : roles) {
            if (role.id == i) {
                return role;
            }
        }
        return null;
    }

}
