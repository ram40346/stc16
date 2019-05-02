package org.lesson02.task03;

import org.apache.commons.lang3.RandomStringUtils;

import java.security.SecureRandom;

/**
 * Класс характеризующий человека
 */
public class Person {

    private static final int MAX_NAME_LENGTH = 10;
    private static final int MAX_AGE = 100;

    private Integer age;
    private Sex sex;
    private String name;

    Person(int age, Sex sex, String name) {
        this.age = age;
        this.sex = sex;
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return age.hashCode() * 31 + sex.hashCode() * 31 + name.hashCode() * 31;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Person) {
            Person person = (Person) obj;
            return this.age.equals(person.getAge())
                    && this.getSex().equals(person.getSex())
                    && this.name.equals(person.getName());
        }
        return false;
    }

    /**
     * Генерирует объекты {@link Person}
     *
     * @return Сгенерированный объект
     */

    public static Person generatePerson() {
        SecureRandom randomizer = new SecureRandom();
        Sex[] sexes = Sex.class.getEnumConstants();
        return new Person(randomizer.nextInt(MAX_AGE), sexes[randomizer.nextInt(sexes.length)],
                RandomStringUtils.randomAlphabetic(randomizer.nextInt(MAX_NAME_LENGTH)));
    }

    @Override
    public String toString() {
        return "Person{\n" +
                "  age=" + age +
                ",\n    sex=" + sex +
                ",\n  name='" + name + '\'' +
                "\n}";
    }
}