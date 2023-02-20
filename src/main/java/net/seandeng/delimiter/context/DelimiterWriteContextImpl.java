package net.seandeng.delimiter.context;

import com.alibaba.excel.exception.ExcelGenerateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Delimiter content write content implement
 *
 * @author sean.deng
 */
public class DelimiterWriteContextImpl implements DelimiterWriteContext {

    private static final Logger LOGGER = LoggerFactory.getLogger(DelimiterWriteContextImpl.class);

    /**
     * Current content holder
     */
    private final StringBuilder writeContentHolder;
    /**
     * Prevent multiple shutdowns
     */
    private boolean finished = false;

    public DelimiterWriteContextImpl() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Begin to Initialization 'WriteContextImpl'");
        }
        // initialize content holder
        writeContentHolder = new StringBuilder();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Initialization 'WriteContextImpl' complete");
        }
    }

    @Override
    public StringBuilder writeContentHolder() {
        return writeContentHolder;
    }

    @Override
    public void finish(boolean onException) {
        if (finished) {
            return;
        }
        Throwable throwable = null;
        if (onException) {
            try {
                System.out.println("some error happen");
            } catch (Throwable t) {
                throwable = t;
            }
        }
        finished = true;

        if (throwable != null) {
            throw new ExcelGenerateException("Can not close IO.", throwable);
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Finished write.");
        }
    }
}
