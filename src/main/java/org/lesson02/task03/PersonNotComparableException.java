package org.lesson02.task03;

/**
 * Исключительная ситуация, описывающая невозможность сравнения объектов {@link Person}
 */
class PersonNotComparableException extends Exception {

    PersonNotComparableException(Person personFirst, Person personSecond) {
        super("Объекты идентичны \n"  + personFirst + personSecond);
    }
}
