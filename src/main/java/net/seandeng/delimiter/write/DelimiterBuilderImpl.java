package net.seandeng.delimiter.write;

import net.seandeng.delimiter.context.DelimiterWriteContext;
import net.seandeng.delimiter.context.DelimiterWriteContextImpl;
import net.seandeng.delimiter.executor.DelimiterWriteExecutor;

import java.util.Collection;

/**
 * 分隔符写实现类
 *
 * @author admin
 */
public class DelimiterBuilderImpl implements DelimiterBuilder {

    private final DelimiterWriteContext context;

    private DelimiterWriteExecutor delimiterWriteExecutor;

    public DelimiterBuilderImpl() {
        try {
            context = new DelimiterWriteContextImpl();
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
