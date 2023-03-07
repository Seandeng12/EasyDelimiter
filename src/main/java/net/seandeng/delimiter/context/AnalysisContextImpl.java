package net.seandeng.delimiter.context;

import lombok.extern.slf4j.Slf4j;
import net.seandeng.delimiter.read.metadata.ReadFile;
import net.seandeng.delimiter.read.metadata.ReadWorkbook;
import net.seandeng.delimiter.read.metadata.holder.ReadFileHolder;
import net.seandeng.delimiter.read.metadata.holder.ReadHolder;
import net.seandeng.delimiter.read.metadata.holder.ReadRowHolder;
import net.seandeng.delimiter.read.metadata.holder.ReadWorkbookHolder;
import net.seandeng.delimiter.read.processor.AnalysisEventProcessor;
import net.seandeng.delimiter.read.processor.DefaultAnalysisEventProcessor;

/**
 * Analysis Context
 *
 * @author deng
 */
@Slf4j
public class AnalysisContextImpl implements AnalysisContext {

    private final ReadWorkbookHolder readWorkbookHolder;

    /**
     * current file holder
     */
    private ReadFileHolder readFileHolder;
    /**
     * current row holder
     */
    private ReadRowHolder readRowHolder;
    /**
     * currently operated value
     */
    private ReadHolder currentReadHolder;

    private final AnalysisEventProcessor analysisEventProcessor;

    public AnalysisContextImpl(ReadWorkbook readWorkbook) {
        if (readWorkbook == null) {
            throw new IllegalArgumentException("Workbook argument cannot be null");
        }
        readWorkbookHolder = new ReadWorkbookHolder(readWorkbook);
        currentReadHolder = readWorkbookHolder;
        analysisEventProcessor = new DefaultAnalysisEventProcessor();
        if (log.isDebugEnabled()) {
            log.debug("Initialization 'AnalysisContextImpl' complete");
        }
    }

    @Override
    public void currentFile(ReadFile readFile) {
        readFileHolder = new ReadFileHolder(readFile, readWorkbookHolder);
        currentReadHolder = readFileHolder;
        if (log.isDebugEnabled()) {
            log.debug("Began to read：{}", readFileHolder);
        }
    }

    @Override
    public ReadFileHolder readFileHolder() {
        return readFileHolder;
    }

    @Override
    public ReadRowHolder readRowHolder() {
        return readRowHolder;
    }

    @Override
    public ReadWorkbookHolder readWorkbookHolder() {
        return readWorkbookHolder;
    }

    @Override
    public void readRowHolder(ReadRowHolder readRowHolder) {
        this.readRowHolder = readRowHolder;
    }

    @Override
    public ReadHolder currentReadHolder() {
        return currentReadHolder;
    }

    @Override
    public AnalysisEventProcessor analysisEventProcessor() {
        return analysisEventProcessor;
    }
}
