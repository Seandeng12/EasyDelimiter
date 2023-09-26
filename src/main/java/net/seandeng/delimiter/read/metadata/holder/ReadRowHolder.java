package net.seandeng.delimiter.read.metadata.holder;

import net.seandeng.delimiter.enums.HolderEnum;
import net.seandeng.delimiter.metadata.Holder;
import net.seandeng.delimiter.metadata.data.ReadCellData;

import java.util.Map;

/**
 * Row read holder
 *
 * @author deng
 */
public class ReadRowHolder implements Holder {

    private Integer rowIndex;
    /**
     * Cell map
     */
    private Map<Integer, ReadCellData> cellMap;

    /**
     * he result of the previous listener
     */
    private Object currentRowAnalysisResult;

    public ReadRowHolder(Integer rowIndex, Map<Integer, ReadCellData> cellMap) {
        this.rowIndex = rowIndex;
        this.cellMap = cellMap;
    }

    @Override
    public HolderEnum holderType() {
        return HolderEnum.ROW;
    }

    public Integer getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(Integer rowIndex) {
        this.rowIndex = rowIndex;
    }

    public Map<Integer, ReadCellData> getCellMap() {
        return cellMap;
    }

    public void setCellMap(Map<Integer, ReadCellData> cellMap) {
        this.cellMap = cellMap;
    }

    public Object getCurrentRowAnalysisResult() {
        return currentRowAnalysisResult;
    }

    public void setCurrentRowAnalysisResult(Object currentRowAnalysisResult) {
        this.currentRowAnalysisResult = currentRowAnalysisResult;
    }
}
