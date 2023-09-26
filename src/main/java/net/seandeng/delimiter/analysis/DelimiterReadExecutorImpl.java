package net.seandeng.delimiter.analysis;

import net.seandeng.delimiter.context.AnalysisContext;
import net.seandeng.delimiter.parser.DefaultDelimiterParser;
import net.seandeng.delimiter.read.metadata.ReadFile;
import net.seandeng.delimiter.read.metadata.ReadWorkbook;

import java.util.List;

/**
 * read executor
 *
 * @author deng
 */
public class DelimiterReadExecutorImpl implements DelimiterReadExecutor {

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
                DefaultDelimiterParser defaultDelimiterParser;
                final String file = readWorkbook.getFile();
                defaultDelimiterParser = new DefaultDelimiterParser(analysisContext, file, readWorkbook.getClazz());
                defaultDelimiterParser.parser();
                analysisContext.analysisEventProcessor().endFile(analysisContext);
            }
        }
    }
}
