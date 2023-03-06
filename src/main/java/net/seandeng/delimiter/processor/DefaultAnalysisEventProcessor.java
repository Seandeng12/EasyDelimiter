package net.seandeng.delimiter.processor;

import net.seandeng.delimiter.context.AnalysisContext;
import net.seandeng.delimiter.exception.DelimiterAnalysisException;
import net.seandeng.delimiter.exception.DelimiterAnalysisStopException;
import net.seandeng.delimiter.read.listener.ReadListener;
import net.seandeng.delimiter.read.metadata.holder.ReadRowHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Impl of Processor
 *
 * @author deng
 *
 */
public class DefaultAnalysisEventProcessor implements AnalysisEventProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultAnalysisEventProcessor.class);

    @Override
    public void endRow(AnalysisContext analysisContext) {
        dealData(analysisContext);
    }

    private void dealData(AnalysisContext analysisContext) {
        LOGGER.info("DefaultAnalysisEventProcessor process data");
        ReadRowHolder readRowHolder = analysisContext.readRowHolder();
        // Now is data
        for (ReadListener readListener : analysisContext.currentReadHolder().readListenerList()) {
            try {
                readListener.invoke(readRowHolder.getCurrentRowAnalysisResult(), analysisContext);
            } catch (Exception e) {
                onException(analysisContext, e);
                break;
            }
            if (!readListener.hasNext(analysisContext)) {
                throw new DelimiterAnalysisStopException();
            }
        }
    }

    private void onException(AnalysisContext analysisContext, Exception e) {
        for (ReadListener readListenerException : analysisContext.currentReadHolder().readListenerList()) {
            try {
                readListenerException.onException(e, analysisContext);
            } catch (RuntimeException re) {
                throw re;
            } catch (Exception e1) {
                throw new DelimiterAnalysisException(e1.getMessage(), e1);
            }
        }
    }
}
