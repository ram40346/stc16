package part1.lesson02.task02;

/**
 * Описывает ошибку при передаче отрицательного значения
 * когда данное не предусмотрено
 */
public class NegativeNumberException extends Exception {
    public NegativeNumberException() {
        super();
    }

    public NegativeNumberException(String message) {
        super(message);
    }

    public NegativeNumberException(String message, Throwable cause) {
        super(message, cause);
    }

    public NegativeNumberException(Throwable cause) {
        super(cause);
    }

    protected NegativeNumberException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
