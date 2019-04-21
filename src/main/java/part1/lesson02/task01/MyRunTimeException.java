package part1.lesson02.task01;

public class MyRunTimeException extends RuntimeException {
    public MyRunTimeException() {
        super();
    }

    public MyRunTimeException(String message) {
        super(message);
    }

    public MyRunTimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyRunTimeException(Throwable cause) {
        super(cause);
    }

    protected MyRunTimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
