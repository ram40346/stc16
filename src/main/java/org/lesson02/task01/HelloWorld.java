package org.lesson02.task01;

/**
 * Пример вызова исключительных ситуаций
 */
class HelloWorld {

    private HelloWorld() {
    }

    /**
     * Выбрасывает NullPointerException
     */
    static void tryToCallNull() {
        String stringNull = null;
        stringNull.hashCode();
    }

    /**
     * Выбрасывает ArrayIndexOutOfBoundException
     */
    static void tryToCallWrongIndex() {
        String[] array = new String[3];
        String stringElement = array[9];
    }

    /**
     * Выбрасывает MyException
     * @throws MyException реализация обрабатываемой(checked) ошибки
     */
    static void tryToThrowMyException() throws MyException {
        throw new MyException();
    }

    /**
     * Выбрасывает MyRunTimeException
     * @throws MyRunTimeException реализация необрабатываемой(unchecked) ошибки
     */
    static void tryToThrowMyRunTimeException() {
        throw new MyRunTimeException();
    }
}
