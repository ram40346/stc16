package org.lesson02.task02;

/**
 * Описывает ошибку при передаче отрицательного значения
 * когда данное не предусмотрено
 */
class NegativeNumberException extends Exception {

    NegativeNumberException() {
        super();
    }

    NegativeNumberException(String message) {
        super(message);
    }
}
