package net.seandeng.delimiter.exception;

public class DelimiterAnalysisException extends DelimiterRuntimeException {

    public DelimiterAnalysisException() {}

    public DelimiterAnalysisException(String message) {
        super(message);
    }

    public DelimiterAnalysisException(String message, Throwable cause) {
        super(message, cause);
    }

    public DelimiterAnalysisException(Throwable cause) {
        super(cause);
    }
}
