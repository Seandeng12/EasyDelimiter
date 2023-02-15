package net.seandeng.delimiter.context;

/**
 * delimiter context
 *
 * @author sean.deng
 */
public interface DelimiterWriteContext {

    /**
     * All information about the sheet you are currently working on
     *
     * @return
     */
    StringBuilder writeContentHolder();

    /**
     * close
     *
     * @param onException exception
     */
    void finish(boolean onException);
}
