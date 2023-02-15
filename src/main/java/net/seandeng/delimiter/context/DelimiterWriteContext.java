package net.seandeng.delimiter.context;

/**
 * 分隔符写入上下文
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
     * @param onException
     */
    void finish(boolean onException);
}
