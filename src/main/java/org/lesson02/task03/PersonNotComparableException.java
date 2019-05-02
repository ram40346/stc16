package org.lesson02.task03;

/**
 * Исключительная ситуация, описывающая невозможность сравнения объектов {@link Person}
 */
class PersonNotComparableException extends Exception {

    PersonNotComparableException() {
    }

    PersonNotComparableException(String message) {
        super(message);
    }
}
