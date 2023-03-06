package net.seandeng.delimiter.context;

import net.seandeng.delimiter.processor.AnalysisEventProcessor;
import net.seandeng.delimiter.read.metadata.ReadWorkbook;
import net.seandeng.delimiter.read.metadata.holder.ReadHolder;
import net.seandeng.delimiter.read.metadata.holder.ReadRowHolder;

/**
 *
 * A context is the main anchorage point of a delimiter reader.
 *
 * @author deng
 */
public interface AnalysisContext {

    /**
     * read parameter
     * @return ReadWorkbook
     */
    ReadWorkbook readWorkbook();

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
