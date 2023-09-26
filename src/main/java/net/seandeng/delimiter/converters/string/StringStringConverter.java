package net.seandeng.delimiter.converters.string;

import com.alibaba.excel.enums.CellDataTypeEnum;
import net.seandeng.delimiter.converters.Converter;
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
    public String convertToJavaData(ReadCellData cellData) {
        String stringValue = cellData.getStringValue();
        stringValue = stringValue.contains("%2C") ? stringValue.replace("%2C", ",") : stringValue;
        stringValue = stringValue.contains("%22") ? stringValue.replace("%22", "\"") : stringValue;
        stringValue = stringValue.contains("%27") ? stringValue.replace("%27", "'") : stringValue;
        stringValue = stringValue.contains("%7C") ? stringValue.replace("%7C", "|") : stringValue;
        return stringValue;
    }
}
