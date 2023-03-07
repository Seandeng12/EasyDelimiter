package net.seandeng.delimiter.analysis;

import net.seandeng.delimiter.context.AnalysisContext;
import net.seandeng.delimiter.parser.DefaultDelimiterParser;
import net.seandeng.delimiter.read.metadata.ReadFile;
import net.seandeng.delimiter.read.metadata.ReadWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.List;

/**
 * read executor
 *
 * @author deng
 */
public class DelimiterReadExecutorImpl implements DelimiterReadExecutor {

    private static final Logger LOGGER = LoggerFactory.getLogger(DelimiterReadExecutorImpl.class);

    private final AnalysisContext analysisContext;

    public DelimiterReadExecutorImpl(AnalysisContext analysisContext) {
        this.analysisContext = analysisContext;
    }

    @Override
    public void execute() {
        List<ReadFile> fileList = analysisContext.readWorkbookHolder().getParameterFileDataList();
        for (ReadFile readFile : fileList) {
            if (readFile != null) {
                analysisContext.currentFile(readFile);
                final ReadWorkbook readWorkbook = analysisContext.readWorkbookHolder().getReadWorkbook();
                final InputStream inputStream = readWorkbook.getInputStream();
                LOGGER.info("Start to parse read file ");
                final DefaultDelimiterParser defaultDelimiterParser = new DefaultDelimiterParser(analysisContext, inputStream, readWorkbook.getClazz());
                defaultDelimiterParser.parser();

                analysisContext.analysisEventProcessor().endFile(analysisContext);
            }
        }
    }
}
