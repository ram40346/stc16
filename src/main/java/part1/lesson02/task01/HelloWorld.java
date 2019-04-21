package part1.lesson02.task01;

/**
 * Пример вызова исключительных ситуаций
 */
public class HelloWorld {
    /**
     * Выбрасывает NullPointerException
     */
    public static void tryToCallNull() {
        String stringNull = null;
        stringNull.hashCode();
    }

    /**
     * Выбрасывает ArrayIndexOutOfBoundException
     */
    public static void tryToCallWrongIndex() {
        String[] array = new String[3];
        String stringElement = array[9];
    }

    /**
     * Выбрасывает MyException
     * @throws MyException
     */
    public static void tryToThrowMyException() throws MyException {
        throw new MyException();
    }

    /**
     * Выбрасывает MyRunTimeException
     * @throws MyRunTimeException
     */
    public static void tryToThrowMyRunTimeException() {
        throw new MyRunTimeException();
    }
}
