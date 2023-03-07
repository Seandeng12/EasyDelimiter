package net.seandeng.delimiter.converters.string;

import com.alibaba.excel.enums.CellDataTypeEnum;
import net.seandeng.delimiter.converters.Converter;
import net.seandeng.delimiter.metadata.DelimiterContentProperty;
import net.seandeng.delimiter.metadata.data.ReadCellData;

/**
 * string converter
 *
 * @author deng
 */
public class StringStringConverter implements Converter<String> {

    @Override
    public Class<?> supportJavaTypeKey() {
        return String.class;
    }

    @Override
    public CellDataTypeEnum supportTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public String convertToJavaData(ReadCellData<?> cellData, DelimiterContentProperty contentProperty) throws Exception {
        return cellData.getStringValue();
    }
}
