package net.seandeng.delimiter.write;

import java.util.Collection;

/**
 * 分隔符构建
 *
 * @author sean.deng
 */
public interface DelimiterBuilder {

    /**
     * to Content
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
