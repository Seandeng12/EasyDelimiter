package net.seandeng.delimiter.context;

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
        if (onException) {
            LOGGER.info("数据拼接异常。");
            return;
        }
        if (finished) {
            return;
        }
        finished = true;
    }
}
