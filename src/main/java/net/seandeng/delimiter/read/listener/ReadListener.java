package net.seandeng.delimiter.read.listener;

import net.seandeng.delimiter.context.AnalysisContext;
import net.seandeng.delimiter.event.Listener;

/**
 * read listener
 *
 * @author deng
 */
public interface ReadListener<T> extends Listener {

    /**
     * All listeners receive this method when any one Listener does an error report. If an exception is thrown here, the entire read will terminate.
     * @param exception
     * @param context
     * @throws Exception
     */
    default void onException(Exception exception, AnalysisContext context) throws Exception {
        throw exception;
    }

    /**
     * When analysis one row trigger invoke function.
     * @param data one row value. Is is same as AnalysisContext.readRowHolder()
     * @param context analysis context
     */
    void invoke(T data, AnalysisContext context);

    /**
     * if have something to do after all analysis
     * @param context
     */
    void doAfterAllAnalysed(AnalysisContext context);

    /**
     * Verify that there is another piece of data.You can stop the read by returning false
     *
     * @param context
     * @return
     */
    default boolean hasNext(AnalysisContext context) {
        return true;
    }
}
