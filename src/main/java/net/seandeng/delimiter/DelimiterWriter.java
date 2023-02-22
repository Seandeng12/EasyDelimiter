package net.seandeng.delimiter;

import net.seandeng.delimiter.write.DelimiterBuilder;
import net.seandeng.delimiter.write.DelimiterBuilderImpl;
import net.seandeng.delimiter.write.metadata.WriteWorkbook;

import java.io.Closeable;
import java.util.Collection;

/**
 * 分隔符写
 *
 * @author deng
 */
public class DelimiterWriter implements Closeable {

    private final DelimiterBuilder delimiterBuilder;

    public DelimiterWriter(WriteWorkbook writeWorkbook) {
        delimiterBuilder = new DelimiterBuilderImpl(writeWorkbook);
    }

    public DelimiterWriter write(Collection<?> data) {
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
