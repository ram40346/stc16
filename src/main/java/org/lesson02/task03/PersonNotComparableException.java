package org.lesson02.task03;

/**
 * Исключительная ситуация, описывающая невозможность сравнения объектов {@link Person}
 */
public class PersonNotComparableException extends Exception {

    public PersonNotComparableException() {
    }

    public PersonNotComparableException(String message) {
        super(message);
    }
}
