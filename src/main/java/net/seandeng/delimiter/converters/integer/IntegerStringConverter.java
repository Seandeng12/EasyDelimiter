package net.seandeng.delimiter.converters.integer;

import com.alibaba.excel.enums.CellDataTypeEnum;
import net.seandeng.delimiter.converters.Converter;
import net.seandeng.delimiter.metadata.data.ReadCellData;
import org.apache.commons.lang3.StringUtils;

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
    public Integer convertToJavaData(ReadCellData cellData) {
        if (StringUtils.isNotEmpty(cellData.getStringValue())) {
            return Integer.parseInt(cellData.getStringValue());
        }
        return null;
    }
}
