package org.lesson02.task03;

/**
 * Утилитный класс для проверки полей объекта Person
 */
public class PersonCompareUtils {
    public static boolean isWomanBeforeMan(Person personFirst, Person personSecond) {
        return personFirst.getSex() == Sex.WOMAN && personSecond.getSex() == Sex.MAN;
    }

    public static boolean isManBeforeWoman(Person personFirst, Person personSecond) {
        return personFirst.getSex() == Sex.MAN && personSecond.getSex() == Sex.WOMAN;
    }

    public static boolean isSexMatches(Person personFirst, Person personSecond) {
        return personFirst.getSex().equals(personSecond.getSex());
    }

    public static boolean isAgeMore(Person personFirst, Person personSecond) {
        return personFirst.getAge() < personSecond.getAge();
    }

    public static boolean isAgeLess(Person personFirst, Person personSecond) {
        return personFirst.getAge() > personSecond.getAge();
    }

    public static boolean isAgeEquals(Person personFirst, Person personSecond) {
        return personFirst.getAge().equals(personSecond.getAge());
    }

    public static boolean isNameLess(Person personFirst, Person personSecond) {
        return personFirst.getName().compareToIgnoreCase(personSecond.getName()) < 0;
    }

    public static boolean isNameMore(Person personFirst, Person personSecond) {
        return personFirst.getName().compareToIgnoreCase(personSecond.getName()) > 0;
    }
}