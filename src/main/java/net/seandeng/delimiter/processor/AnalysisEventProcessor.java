package net.seandeng.delimiter.processor;

import net.seandeng.delimiter.context.AnalysisContext;

/**
 * event processor
 *
 * @author deng
 */
public interface AnalysisEventProcessor {

    /**
     * End row
     *
     * @param analysisContext
     */
    void endRow(AnalysisContext analysisContext);
}
