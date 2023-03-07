package net.seandeng.delimiter.util;

import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.util.MapUtils;
import net.seandeng.delimiter.context.AnalysisContext;
import net.seandeng.delimiter.converters.Converter;
import net.seandeng.delimiter.converters.ConverterKeyBuild;
import net.seandeng.delimiter.converters.ConverterKeyBuild.ConverterKey;
import net.seandeng.delimiter.converters.NullableObjectConverter;
import net.seandeng.delimiter.converters.ReadConverterContext;
import net.seandeng.delimiter.exception.DelimiterDataConvertException;
import net.seandeng.delimiter.metadata.DelimiterContentProperty;
import net.seandeng.delimiter.metadata.data.CellData;
import net.seandeng.delimiter.metadata.data.ReadCellData;
import net.seandeng.delimiter.read.metadata.holder.ReadFileHolder;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * convert util
 * @author deng
 */
public class ConverterUtils {

    public static Class<?> defaultClassGeneric = String.class;

    private ConverterUtils() {}

    /**
     * Convert it into a String map
     *
     * @param cellDataMap
     * @param context
     * @return
     */
    public static Map<Integer, String> convertToStringMap(Map<Integer, ReadCellData<?>> cellDataMap, AnalysisContext context) {
        Map<Integer, String> stringMap = MapUtils.newHashMapWithExpectedSize(cellDataMap.size());
        ReadFileHolder readFileHolder = context.readFileHolder();
        int index = 0;
        for (Map.Entry<Integer, ReadCellData<?>> entry : cellDataMap.entrySet()) {
            Integer key = entry.getKey();
            ReadCellData<?> cellData = entry.getValue();
            while (index < key) {
                stringMap.put(index, null);
                index++;
            }
            index++;
            if (cellData.getType() == CellDataTypeEnum.EMPTY) {
                stringMap.put(key, null);
                continue;
            }
            Converter<?> converter =
                    readFileHolder.converterMap().get(ConverterKeyBuild.buildKey(String.class, cellData.getType()));
            if (converter == null) {
                throw new DelimiterDataConvertException(context.readRowHolder().getRowIndex(), key, cellData, null,
                        "Converter not found, convert " + cellData.getType() + " to String");
            }
            try {
                stringMap.put(key,
                        (String)(converter.convertToJavaData(new ReadConverterContext<>(cellData, null, context))));
            } catch (Exception e) {
                throw new DelimiterDataConvertException(context.readRowHolder().getRowIndex(), key, cellData, null,
                        "Convert data " + cellData + " to String error ", e);
            }
        }
        return stringMap;
    }

    /**
     * Convert it into a Java object
     *
     * @param cellData
     * @param field
     * @param contentProperty
     * @param converterMap
     * @param context
     * @param rowIndex
     * @param columnIndex
     * @return
     */
    public static Object convertToJavaObject(ReadCellData<?> cellData, Field field,
                                             DelimiterContentProperty contentProperty, Map<ConverterKeyBuild.ConverterKey, Converter<?>> converterMap, AnalysisContext context,
                                             Integer rowIndex, Integer columnIndex) {
        Class<?> clazz;
        if (field == null) {
            clazz = String.class;
        } else {
            clazz = field.getType();
        }
        if (clazz == CellData.class || clazz == ReadCellData.class) {
            Class<?> classGeneric = getClassGeneric(field.getGenericType());
            ReadCellData<Object> cellDataReturn = cellData.clone();
            cellDataReturn.setData(doConvertToJavaObject(cellData, classGeneric, contentProperty, converterMap,
                    context, rowIndex, columnIndex));
            return cellDataReturn;
        }
        return doConvertToJavaObject(cellData, clazz, contentProperty, converterMap, context, rowIndex,
                columnIndex);
    }

    private static Class<?> getClassGeneric(Type type) {
        if (!(type instanceof ParameterizedType)) {
            return defaultClassGeneric;
        }
        ParameterizedType parameterizedType = (ParameterizedType)type;
        Type[] types = parameterizedType.getActualTypeArguments();
        if (types == null || types.length == 0) {
            return defaultClassGeneric;
        }
        Type actualType = types[0];
        if (!(actualType instanceof Class<?>)) {
            return defaultClassGeneric;
        }
        return (Class<?>)actualType;
    }

    /**
     * @param cellData
     * @param clazz
     * @param contentProperty
     * @param converterMap
     * @param context
     * @param rowIndex
     * @param columnIndex
     * @return
     */
    private static Object doConvertToJavaObject(ReadCellData<?> cellData, Class<?> clazz,
                                                DelimiterContentProperty contentProperty, Map<ConverterKey, Converter<?>> converterMap, AnalysisContext context,
                                                Integer rowIndex, Integer columnIndex) {
        Converter<?> converter = null;
        if (contentProperty != null) {
            converter = contentProperty.getConverter();
        }

        boolean canNotConverterEmpty = cellData.getType() == CellDataTypeEnum.EMPTY && !(converter instanceof NullableObjectConverter);
        if (canNotConverterEmpty) {
            return null;
        }

        if (converter == null) {
            converter = converterMap.get(ConverterKeyBuild.buildKey(clazz, cellData.getType()));
        }
        if (converter == null) {
            throw new DelimiterDataConvertException(rowIndex, columnIndex, cellData, contentProperty,
                    "Converter not found, convert " + cellData.getType() + " to " + clazz.getName());
        }

        try {
            return converter.convertToJavaData(new ReadConverterContext<>(cellData, contentProperty, context));
        } catch (Exception e) {
            throw new DelimiterDataConvertException(rowIndex, columnIndex, cellData, contentProperty,
                    "Convert data " + cellData + " to " + clazz + " error ", e);
        }
    }
}
