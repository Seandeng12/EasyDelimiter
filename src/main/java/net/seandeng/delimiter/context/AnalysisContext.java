package net.seandeng.delimiter.context;

import net.seandeng.delimiter.read.metadata.ReadFile;
import net.seandeng.delimiter.read.metadata.holder.ReadFileHolder;
import net.seandeng.delimiter.read.metadata.holder.ReadHolder;
import net.seandeng.delimiter.read.metadata.holder.ReadRowHolder;
import net.seandeng.delimiter.read.metadata.holder.ReadWorkbookHolder;
import net.seandeng.delimiter.read.processor.AnalysisEventProcessor;

/**
 *
 * A context is the main anchorage point of a delimiter reader.
 *
 * @author deng
 */
public interface AnalysisContext {

    void currentFile(ReadFile readFile);

    ReadFileHolder readFileHolder();

    /**
     * All information about the workbook you are currently working on
     *
     * @return Current workbook holder
     */
    ReadWorkbookHolder readWorkbookHolder();

    /**
     * Set row of currently operated cell
     *
     * @param readRowHolder
     *            Current row holder
     */
    void readRowHolder(ReadRowHolder readRowHolder);

    /**
     * Row of currently operated cell
     *
     * @return Current row holder
     */
    ReadRowHolder readRowHolder();
    /**
     * The current read operation corresponds to the <code>readSheetHolder</code> or <code>readWorkbookHolder</code>
     *
     * @return Current holder
     */
    ReadHolder currentReadHolder();

    /**
     * Event processor
     *
     * @return
     */
    AnalysisEventProcessor analysisEventProcessor();
}
