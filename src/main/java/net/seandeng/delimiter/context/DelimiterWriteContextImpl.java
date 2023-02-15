package net.seandeng.delimiter.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 分隔符写入上下文实现类
 *
 * @author sean.deng
 */
public class DelimiterWriteContextImpl implements DelimiterWriteContext {

    private static final Logger LOGGER = LoggerFactory.getLogger(DelimiterWriteContextImpl.class);

    /**
     * Current Work book
     */
//    private final WriteWorkbook writeWorkbook;
    /**
     * Current content holder
     */
    private final StringBuilder writeContentHolder;
    /**
     * Prevent multiple shutdowns
     */
    private boolean finished = false;

//    public WriteContextImpl(WriteWorkbook writeWorkbook) {
//        if (writeWorkbook == null) {
//            throw new IllegalArgumentException("Workbook argument cannot be null");
//        }
//
//        if (LOGGER.isDebugEnabled()) {
//            LOGGER.debug("Begin to Initialization 'WriteContextImpl'");
//        }
//        this.writeWorkbook = writeWorkbook;
//        // 初始化
//        writeContentHolder = new StringBuilder();
//
//        if (LOGGER.isDebugEnabled()) {
//            LOGGER.debug("Initialization 'WriteContextImpl' complete");
//        }
//    }

    public DelimiterWriteContextImpl() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Begin to Initialization 'WriteContextImpl'");
        }
        // 初始化
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
