package net.seandeng.delimiter.util;

import com.alibaba.excel.enums.CellDataTypeEnum;
import net.seandeng.delimiter.context.AnalysisContext;
import net.seandeng.delimiter.converters.Converter;
import net.seandeng.delimiter.converters.ConverterKeyBuild;
import net.seandeng.delimiter.converters.ConverterKeyBuild.ConverterKey;
import net.seandeng.delimiter.converters.ReadConverterContext;
import net.seandeng.delimiter.exception.DelimiterDataConvertException;
import net.seandeng.delimiter.metadata.data.ReadCellData;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * convert util
 *
 * @author deng
 */
public class ConverterUtils {

    public static Class<?> defaultClassGeneric = String.class;

    private ConverterUtils() {
    }


    /**
     * Convert it into a Java object
     *
     * @param cellData
     * @param field
     * @param converterMap
     * @param context
     * @param rowIndex
     * @param columnIndex
     * @return
     */
    public static Object convertToJavaObject(ReadCellData cellData, Field field, Map<ConverterKey, Converter<?>> converterMap, AnalysisContext context,
                                             Integer rowIndex, Integer columnIndex) {
        Class<?> clazz;
        if (field == null) {
            clazz = String.class;
        } else {
            clazz = field.getType();
        }
        return doConvertToJavaObject(cellData, clazz, converterMap, context, rowIndex, columnIndex, field);
    }

    /**
     * @param cellData
     * @param clazz
     * @param converterMap
     * @param context
     * @param rowIndex
     * @param columnIndex
     * @return
     */
    private static Object doConvertToJavaObject(ReadCellData cellData, Class<?> clazz,
                                                Map<ConverterKey, Converter<?>> converterMap,
                                                AnalysisContext context,
                                                Integer rowIndex, Integer columnIndex, Field field) {
        boolean canNotConverterEmpty = cellData.getType() == CellDataTypeEnum.EMPTY;
        if (canNotConverterEmpty) {
            return null;
        }
        Converter<?> converter = converterMap.get(ConverterKeyBuild.buildKey(clazz, cellData.getType()));
        try {
            return converter.convertToJavaData(new ReadConverterContext<>(cellData, context));
        } catch (Exception e) {
            throw new DelimiterDataConvertException(rowIndex, columnIndex, cellData, field.getName(),
                    "Convert data [" + cellData.getStringValue() + "] to " + clazz + " error ", e);
        }
    }
}
