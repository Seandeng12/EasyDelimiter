package net.seandeng.delimiter.write;

import net.seandeng.delimiter.context.WriteContext;
import net.seandeng.delimiter.context.WriteContextImpl;
import net.seandeng.delimiter.write.executor.DelimiterWriteExecutor;
import net.seandeng.delimiter.write.metadata.WriteWorkbook;

import java.util.Collection;

/**
 * implement builder
 *
 * @author admin
 */
public class DelimiterBuilderImpl implements DelimiterBuilder {

    private final WriteContext context;

    private DelimiterWriteExecutor delimiterWriteExecutor;

    public DelimiterBuilderImpl(WriteWorkbook writeWorkbook) {
        try {
            context = new WriteContextImpl(writeWorkbook);
        } catch (RuntimeException e) {
            finishOnException();
            throw e;
        } catch (Throwable e) {
            finishOnException();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addContent(Collection<?> data) {
        try {
            if (delimiterWriteExecutor == null) {
                delimiterWriteExecutor = new DelimiterWriteExecutor(context);
            }
            delimiterWriteExecutor.add(data);
        } catch (RuntimeException e) {
            finishOnException();
            throw e;
        } catch (Throwable e) {
            finishOnException();
            throw new RuntimeException(e);
        }
    }

    private void finishOnException() {
        finish(true);
    }

    @Override
    public void finish(boolean onException) {
        if (context != null) {
            context.finish(onException);
        }
    }
}
