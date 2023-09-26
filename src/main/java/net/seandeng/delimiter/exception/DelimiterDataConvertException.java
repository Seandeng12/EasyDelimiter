package net.seandeng.delimiter.exception;

import net.seandeng.delimiter.metadata.data.ReadCellData;
import lombok.Getter;
import lombok.Setter;

/**
 * DataConvertException
 *
 * @author deng
 */
@Getter
@Setter
public class DelimiterDataConvertException extends DelimiterRuntimeException {

    /**
     * rowIndex
     */
    private Integer rowIndex;
    /**
     * NotNull.
     */
    private Integer columnIndex;
    /**
     * NotNull
     */
    private String fieldName;
    /**
     * NotNull.
     */
    private ReadCellData cellData;

    public DelimiterDataConvertException(Integer rowIndex, Integer columnIndex, ReadCellData cellData, String message, Throwable cause) {
        super(message, cause);
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.cellData = cellData;
    }

    public DelimiterDataConvertException(Integer rowIndex, Integer columnIndex, ReadCellData cellData, String fieldName, String message, Throwable cause) {
        super(message, cause);
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.cellData = cellData;
        this.fieldName = fieldName;
    }

    /**
     * NotNull.
     */
    public DelimiterDataConvertException(String message, Throwable cause) {
        super(message, cause);
    }
}
