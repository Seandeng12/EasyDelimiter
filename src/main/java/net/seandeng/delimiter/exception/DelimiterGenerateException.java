package net.seandeng.delimiter.exception;

public class DelimiterGenerateException extends DelimiterRuntimeException{

    public DelimiterGenerateException(String message) {
        super(message);
    }

    public DelimiterGenerateException(String message, Throwable cause) {
        super(message, cause);
    }

    public DelimiterGenerateException(Throwable cause) {
        super(cause);
    }
}
