package net.seandeng.delimiter.context;

import net.seandeng.delimiter.write.metadata.WriteWorkbook;

/**
 * delimiter context
 *
 * @author sean.deng
 */
public interface DelimiterWriteContext {

    /**
     * All information about the sheet you are currently working on
     *
     * @return StringBuilder
     */
    StringBuilder writeContentHolder();

    /**
     * WriteWorkbook
     * @return WriteHandler
     */
    WriteWorkbook writeWorkbook();

    /**
     * close
     *
     * @param onException exception
     */
    void finish(boolean onException);
}
