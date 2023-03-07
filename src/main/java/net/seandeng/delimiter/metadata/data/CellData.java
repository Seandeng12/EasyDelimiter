package net.seandeng.delimiter.metadata.data;

import com.alibaba.excel.enums.CellDataTypeEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import net.seandeng.delimiter.metadata.Cell;

import java.math.BigDecimal;

/**
 * field
 *
 * @author deng
 */
@Getter
@Setter
@EqualsAndHashCode
public class CellData<T> implements Cell {

    /**
     * cell type
     */
    private CellDataTypeEnum type;
    /**
     */
    private BigDecimal numberValue;
    /**
     */
    private String stringValue;
    /**
     */
    private Boolean booleanValue;

    /**
     * The resulting converted data.
     */
    private T data;
}
