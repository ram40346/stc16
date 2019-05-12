package org.lesson02.task03;

import static org.lesson02.task03.PersonCompareUtils.*;

/**
 * Класс характеризующий человека
 */
public class Person implements Comparable<Person> {

    private Integer age;
    private Sex sex;
    private String name;

    public Person(int age, Sex sex, String name) {
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

    @Override
    public String toString() {
        return "Person{\n" +
                "  age=" + age +
                ",\n    sex=" + sex +
                ",\n  name='" + name + '\'' +
                "\n}";
    }

    /**
     * Сравнивает два объекта {@link Person}
     *
     * @param person
     * @return
     */
    @Override
    public int compareTo(Person person) {
        if (PersonCompareUtils.isWomanBeforeMan(this, person)) {
            return -1;
        } else if (isManBeforeWoman(this, person)) {
            return 1;
        }

        if (isAgeLess(this, person)) {
            return 1;
        } else if (isAgeMore(this, person)) {
            return -1;
        }

        if (isNameLess(this, person)) {
            return -1;
        } else if (this.getName().equals(person.getName())) {
            return 0;
        }
        return 1;
    }
}