package net.seandeng.delimiter.read.metadata.holder;

import net.seandeng.delimiter.metadata.Holder;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Row read holder
 *
 * @author deng
 */
public class ReadRowHolder implements Holder {

    private Map<Integer, Field> sortedAllFieldMap;

    /**
     * The result of the previous listener
     */
    private Object currentRowAnalysisResult;

    public ReadRowHolder(Map<Integer, Field> sortedAllFieldMap) {
        this.sortedAllFieldMap = sortedAllFieldMap;
    }

    public Object getCurrentRowAnalysisResult() {
        return currentRowAnalysisResult;
    }

    public void setCurrentRowAnalysisResult(Object currentRowAnalysisResult) {
        this.currentRowAnalysisResult = currentRowAnalysisResult;
    }

    public Map<Integer, Field> getSortedAllFieldMap() {
        return sortedAllFieldMap;
    }

    public void setSortedAllFieldMap(Map<Integer, Field> sortedAllFieldMap) {
        this.sortedAllFieldMap = sortedAllFieldMap;
    }
}
