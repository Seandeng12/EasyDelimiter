package net.seandeng.delimiter.converters.integer;

import com.alibaba.excel.enums.CellDataTypeEnum;
import net.seandeng.delimiter.converters.Converter;
import net.seandeng.delimiter.metadata.DelimiterContentProperty;
import net.seandeng.delimiter.metadata.data.ReadCellData;
import net.seandeng.delimiter.util.NumberUtils;

/**
 * integer and string convert
 *
 * @author deng
 */
public class IntegerStringConverter implements Converter<Integer> {

    @Override
    public Class<?> supportJavaTypeKey() {
        return Integer.class;
    }

    @Override
    public CellDataTypeEnum supportTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public Integer convertToJavaData(ReadCellData<?> cellData, DelimiterContentProperty contentProperty) throws Exception {
        return NumberUtils.parseInteger(cellData.getStringValue(), contentProperty);
    }
}
