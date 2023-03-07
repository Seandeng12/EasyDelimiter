package net.seandeng.delimiter.analysis;

import net.seandeng.delimiter.context.AnalysisContext;
import net.seandeng.delimiter.context.AnalysisContextImpl;
import net.seandeng.delimiter.exception.DelimiterAnalysisException;
import net.seandeng.delimiter.exception.DelimiterAnalysisStopException;
import net.seandeng.delimiter.read.metadata.ReadFile;
import net.seandeng.delimiter.read.metadata.ReadWorkbook;
import net.seandeng.delimiter.read.metadata.holder.ReadWorkbookHolder;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * analysis
 *
 * @author deng
 */
public class DelimiterAnalyserImpl implements DelimiterAnalyser {

    private static final Logger LOGGER = LoggerFactory.getLogger(DelimiterAnalyserImpl.class);

    private final AnalysisContext analysisContext;

    private final DelimiterReadExecutor delimiterReadExecutor;
    /**
     * Prevent multiple shutdowns
     */
    private boolean finished = false;

    public DelimiterAnalyserImpl(ReadWorkbook readWorkbook) {
        try {
            AnalysisContext opAnalysisContext = new AnalysisContextImpl(readWorkbook);
            analysisContext = opAnalysisContext;
            delimiterReadExecutor = new DelimiterReadExecutorImpl(opAnalysisContext);
        } catch (RuntimeException e) {
            finish();
            throw e;
        } catch (Throwable e) {
            finish();
            throw new DelimiterAnalysisException(e);
        }
    }

    @Override
    public void analysis(List<ReadFile> readFileList, Boolean readAll) {
        if (!readAll && CollectionUtils.isEmpty(readFileList)) {
            throw new IllegalArgumentException("Specify at least one read file.");
        }
        analysisContext.readWorkbookHolder().setParameterFileDataList(readFileList);
        try {
            delimiterReadExecutor.execute();
        } catch (DelimiterAnalysisStopException e) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Custom stop!");
            }
        }
    }

    @Override
    public void finish() {
        if (finished) {
            return;
        }
        finished = true;
        if (analysisContext == null || analysisContext.readWorkbookHolder() == null) {
            return;
        }
        ReadWorkbookHolder readWorkbookHolder = analysisContext.readWorkbookHolder();
        Throwable throwable = null;

        try {
            if (analysisContext.readWorkbookHolder().getAutoCloseStream()
                    && readWorkbookHolder.getInputStream() != null) {
                readWorkbookHolder.getInputStream().close();
            }
        } catch (Throwable t) {
            throwable = t;
        }
        if (throwable != null) {
            throw new DelimiterAnalysisException("Can not close IO.", throwable);
        }
    }
}
