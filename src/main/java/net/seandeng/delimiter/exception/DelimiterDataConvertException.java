package net.seandeng.delimiter.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import net.seandeng.delimiter.metadata.DelimiterContentProperty;
import net.seandeng.delimiter.metadata.data.CellData;

/**
 * DataConvertException
 *
 * @author deng
 */
@Getter
@Setter
@EqualsAndHashCode
public class DelimiterDataConvertException extends DelimiterRuntimeException {

    private Integer rowIndex;
    /**
     * NotNull.
     */
    private Integer columnIndex;
    /**
     * NotNull.
     */
    private CellData<?> cellData;

    private DelimiterContentProperty delimiterContentProperty;

    public DelimiterDataConvertException(Integer rowIndex, Integer columnIndex, CellData<?> cellData,
                                         DelimiterContentProperty delimiterContentProperty, String message) {
        super(message);
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.cellData = cellData;
        this.delimiterContentProperty = delimiterContentProperty;
    }

    public DelimiterDataConvertException(Integer rowIndex, Integer columnIndex, CellData<?> cellData,
                                         DelimiterContentProperty delimiterContentProperty, String message, Throwable cause) {
        super(message, cause);
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.cellData = cellData;
        this.delimiterContentProperty = delimiterContentProperty;
    }

    /**
     * NotNull.
     */
    public DelimiterDataConvertException(String message, Throwable cause) {
        super(message, cause);
    }
}
