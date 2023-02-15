package net.seandeng.delimiter;

import net.seandeng.delimiter.write.DelimiterBuilder;
import net.seandeng.delimiter.write.DelimiterBuilderImpl;

import java.io.Closeable;
import java.util.Collection;

/**
 * 分隔符写
 *
 * @author deng
 */
public class DelimiteWriter implements Closeable {

    private final DelimiterBuilder delimiterBuilder;

    public DelimiteWriter() {
        delimiterBuilder = new DelimiterBuilderImpl();
    }

//    public DelimiteWriter(WriteWorkbook writeWorkbook) {
//        delimiterBuilder = new DelimiterBuilderImpl(writeWorkbook);
//    }

    public DelimiteWriter write(Collection<?> data) {
        delimiterBuilder.addContent(data);
        return this;
    }

    public void finish() {
        delimiterBuilder.finish(false);
    }

    @Override
    public void close() {
        finish();
    }
}
