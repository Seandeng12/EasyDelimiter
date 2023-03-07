package net.seandeng.delimiter.metadata.data;

import com.alibaba.excel.enums.CellDataTypeEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * read field data
 *
 * @author Jiaju Zhuang
 */
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class ReadCellData<T> extends CellData<T> {

    /**
     * data format.
     */
    private DataFormatData dataFormatData;

    public ReadCellData(CellDataTypeEnum type) {
        super();
        if (type == null) {
            throw new IllegalArgumentException("Type can not be null");
        }
        setType(type);
    }

    public ReadCellData(T data) {
        super();
        setData(data);
    }

    public ReadCellData(String stringValue) {
        this(CellDataTypeEnum.STRING, stringValue);
    }

    public ReadCellData(CellDataTypeEnum type, String stringValue) {
        super();
        if (type != CellDataTypeEnum.STRING && type != CellDataTypeEnum.ERROR) {
            throw new IllegalArgumentException("Only support CellDataTypeEnum.STRING and  CellDataTypeEnum.ERROR");
        }
        if (stringValue == null) {
            throw new IllegalArgumentException("StringValue can not be null");
        }
        setType(type);
        setStringValue(stringValue);
    }

    public ReadCellData(BigDecimal numberValue) {
        super();
        if (numberValue == null) {
            throw new IllegalArgumentException("DoubleValue can not be null");
        }
        setType(CellDataTypeEnum.NUMBER);
        setNumberValue(numberValue);
    }

    public ReadCellData(Boolean booleanValue) {
        super();
        if (booleanValue == null) {
            throw new IllegalArgumentException("BooleanValue can not be null");
        }
        setType(CellDataTypeEnum.BOOLEAN);
        setBooleanValue(booleanValue);
    }

    public static ReadCellData<?> newEmptyInstance() {
        return new ReadCellData<>(CellDataTypeEnum.EMPTY);
    }

    public static ReadCellData<?> newInstance(Boolean booleanValue) {
        return new ReadCellData<>(booleanValue);
    }

    public static ReadCellData<?> newInstance(String stringValue) {
        return new ReadCellData<>(stringValue);
    }

    public static ReadCellData<?> newInstance(BigDecimal numberValue) {
        return new ReadCellData<>(numberValue);
    }

    @Override
    public ReadCellData<Object> clone() {
        ReadCellData<Object> readCellData = new ReadCellData<>();
        readCellData.setType(getType());
        readCellData.setNumberValue(getNumberValue());
        readCellData.setStringValue(getStringValue());
        readCellData.setBooleanValue(getBooleanValue());
        readCellData.setData(getData());
        if (getDataFormatData() != null) {
            readCellData.setDataFormatData(getDataFormatData().clone());
        }
        return readCellData;
    }
}
