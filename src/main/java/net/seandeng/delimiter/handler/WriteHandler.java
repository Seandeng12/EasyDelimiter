package net.seandeng.delimiter.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * handle the data list value
 *
 * @author sean,deng
 */
public abstract class WriteHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(WriteHandler.class);

    /**
     * handle the value
     */
    public void handle(Object value) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("not implemented");
        }
    }
}
