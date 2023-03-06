package net.seandeng.delimiter.processor;

import net.seandeng.delimiter.context.AnalysisContext;

/**
 * event processor
 *
 * @author deng
 */
public interface AnalysisEventProcessor {

    /**
     * End line
     *
     * @param analysisContext
     */
    void endLine(AnalysisContext analysisContext);

    /**
     * end one file
     * @param analysisContext
     */
    void endFile(AnalysisContext analysisContext);
}
