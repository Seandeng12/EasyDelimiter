package net.seandeng.delimiter.converters;

import com.alibaba.excel.enums.CellDataTypeEnum;
import net.seandeng.delimiter.metadata.DelimiterContentProperty;
import net.seandeng.delimiter.metadata.data.ReadCellData;

/**
 * @author deng
 */
public interface Converter<T> {


    /**
     * Back to object types in Java
     *
     * @return Support for Java class
     */
    default Class<?> supportJavaTypeKey() {
        throw new UnsupportedOperationException("The current operation is not supported by the current converter.");
    }

    /**
     * Back to object enum
     *
     * @return Support for {@link CellDataTypeEnum}
     */
    default CellDataTypeEnum supportTypeKey() {
        throw new UnsupportedOperationException("The current operation is not supported by the current converter.");
    }

    /**
     * Convert objects to Java objects
     *
     * @param cellData            cell data.NotNull.
     * @param contentProperty     Content property.Nullable.
     * @return Data to put into a Java object
     * @throws Exception Exception.
     */
    default T convertToJavaData(ReadCellData<?> cellData, DelimiterContentProperty contentProperty) throws Exception {
        throw new UnsupportedOperationException("The current operation is not supported by the current converter.");
    }

    /**
     * Convert objects to Java objects
     *
     * @param context read converter context
     * @return Data to put into a Java object
     * @throws Exception Exception.
     */
    default T convertToJavaData(ReadConverterContext<?> context) throws Exception {
        return convertToJavaData(context.getReadCellData(), context.getContentProperty());
    }
}
