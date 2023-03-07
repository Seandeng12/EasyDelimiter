package net.seandeng.delimiter.metadata;

import com.alibaba.excel.metadata.property.DateTimeFormatProperty;
import com.alibaba.excel.metadata.property.NumberFormatProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import net.seandeng.delimiter.converters.Converter;

import java.lang.reflect.Field;

/**
 * content property
 *
 * @author deng
 */
@Getter
@Setter
@EqualsAndHashCode
public class DelimiterContentProperty {

    public static final DelimiterContentProperty EMPTY = new DelimiterContentProperty();

    /**
     * Java field
     */
    private Field field;
    /**
     * Custom defined converters
     */
    private Converter<?> converter;

    private DateTimeFormatProperty dateTimeFormatProperty;
    /**
     * number format
     */
    private NumberFormatProperty numberFormatProperty;
}
