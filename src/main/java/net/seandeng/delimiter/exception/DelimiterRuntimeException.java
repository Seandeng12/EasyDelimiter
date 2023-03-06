package net.seandeng.delimiter.exception;

public class DelimiterRuntimeException extends RuntimeException {

    public DelimiterRuntimeException() {}

    public DelimiterRuntimeException(String message) {
        super(message);
    }

    public DelimiterRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DelimiterRuntimeException(Throwable cause) {
        super(cause);
    }
}
