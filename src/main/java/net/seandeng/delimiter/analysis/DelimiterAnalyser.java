package net.seandeng.delimiter.analysis;

import net.seandeng.delimiter.context.AnalysisContext;

public interface DelimiterAnalyser {

    void analysis();

    void finish();

    /**
     * get the analysis context.
     *
     * @return analysis context
     */
    AnalysisContext analysisContext();
}
