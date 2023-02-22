package net.seandeng.delimiter.context;

import net.seandeng.delimiter.handler.TransmitHandler;
import net.seandeng.delimiter.write.metadata.WriteWorkbook;
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
     * write parameter
     */
    private final WriteWorkbook writeWorkbook;

    /**
     * Current content holder
     */
    private final StringBuilder writeContentHolder;
    /**
     * Prevent multiple shutdowns
     */
    private boolean finished = false;

    public DelimiterWriteContextImpl(WriteWorkbook writeWorkbook) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Begin to Initialization 'WriteContextImpl'");
        }
        this.writeWorkbook = writeWorkbook;
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
    public WriteWorkbook writeWorkbook() {
        return writeWorkbook;
    }

    @Override
    public void finish(boolean onException) {
        if (finished) {
            return;
        }
        final TransmitHandler transmitHandler = writeWorkbook.getTransmitHandler();
        transmitHandler.invoke(writeContentHolder);
        finished = true;
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Finished write.");
        }
    }
}
