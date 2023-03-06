package net.seandeng.delimiter.exception;

public class DelimiterAnalysisStopException extends DelimiterAnalysisException{

    public DelimiterAnalysisStopException() {}

    public DelimiterAnalysisStopException(String message) {
        super(message);
    }

    public DelimiterAnalysisStopException(String message, Throwable cause) {
        super(message, cause);
    }

    public DelimiterAnalysisStopException(Throwable cause) {
        super(cause);
    }
}
