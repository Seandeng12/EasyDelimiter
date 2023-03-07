package net.seandeng.delimiter.write;

import java.util.Collection;

/**
 * The Builder of data line
 *
 * @author sean.deng
 */
public interface DelimiterBuilder {

    /**
     * data List to Content
     *
     * @param data
     */
    void addContent(Collection<?> data);

    /**
     * Close io
     *
     * @param onException
     */
    void finish(boolean onException);
}
