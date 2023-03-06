package net.seandeng.delimiter.analysis;

import net.seandeng.delimiter.context.AnalysisContext;
import net.seandeng.delimiter.parser.DefaultDelimiterParser;
import net.seandeng.delimiter.read.metadata.ReadWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

/**
 * read executor
 *
 * @author deng
 */
public class DelimiterReadExecutorImpl implements DelimiterReadExecutor {

    private static final Logger LOGGER = LoggerFactory.getLogger(DelimiterReadExecutorImpl.class);

    private final ReadWorkbook readWorkbook;

    private final AnalysisContext analysisContext;

    public DelimiterReadExecutorImpl(AnalysisContext analysisContext) {
        this.analysisContext = analysisContext;
        this.readWorkbook = analysisContext.readWorkbook();
    }

    @Override
    public void execute() {
        InputStream inputStream = readWorkbook.getInputStream();
        LOGGER.info("Start to parse read file ");
        final DefaultDelimiterParser defaultDelimiterParser = new DefaultDelimiterParser(analysisContext, inputStream, readWorkbook.getClazz());
        defaultDelimiterParser.parser();
    }
}
