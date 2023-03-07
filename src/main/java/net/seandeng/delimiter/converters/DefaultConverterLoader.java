package net.seandeng.delimiter.converters;

import com.alibaba.excel.util.MapUtils;
import net.seandeng.delimiter.converters.ConverterKeyBuild.ConverterKey;
import net.seandeng.delimiter.converters.integer.IntegerStringConverter;
import net.seandeng.delimiter.converters.string.StringStringConverter;

import java.util.Map;

/**
 * default converter
 * @author deng
 */
public class DefaultConverterLoader {

    private static Map<ConverterKey, Converter<?>> allConverter;

    static {
        initAllConverter();
    }

    private static void initAllConverter() {
        allConverter = MapUtils.newHashMapWithExpectedSize(40);
        putAllConverter(new StringStringConverter());
        putAllConverter(new IntegerStringConverter());
//        putAllConverter(new BigDecimalNumberConverter());
//        putAllConverter(new BigDecimalStringConverter());
//
//        putAllConverter(new BigIntegerBooleanConverter());
//        putAllConverter(new BigIntegerNumberConverter());
//        putAllConverter(new BigIntegerStringConverter());
//
//        putAllConverter(new BooleanBooleanConverter());
//        putAllConverter(new BooleanNumberConverter());
//        putAllConverter(new BooleanStringConverter());
//
//        putAllConverter(new ByteBooleanConverter());
//        putAllConverter(new ByteNumberConverter());
//        putAllConverter(new ByteStringConverter());
//
//        putAllConverter(new DateNumberConverter());
//        putAllConverter(new DateStringConverter());
//
//        putAllConverter(new LocalDateNumberConverter());
//        putAllConverter(new LocalDateTimeStringConverter());
//
//        putAllConverter(new DoubleBooleanConverter());
//        putAllConverter(new DoubleNumberConverter());
//        putAllConverter(new DoubleStringConverter());
//
//        putAllConverter(new FloatBooleanConverter());
//        putAllConverter(new FloatNumberConverter());
//        putAllConverter(new FloatStringConverter());
//
//        putAllConverter(new IntegerBooleanConverter());
//        putAllConverter(new IntegerNumberConverter());
//        putAllConverter(new IntegerStringConverter());
//
//        putAllConverter(new LongBooleanConverter());
//        putAllConverter(new LongNumberConverter());
//        putAllConverter(new LongStringConverter());
//
//        putAllConverter(new ShortBooleanConverter());
//        putAllConverter(new ShortNumberConverter());
//        putAllConverter(new ShortStringConverter());
//
//        putAllConverter(new StringBooleanConverter());
//        putAllConverter(new StringNumberConverter());
//        putAllConverter(new StringStringConverter());
//        putAllConverter(new StringErrorConverter());
//
//        putAllConverter(new BigIntegerStringConverter());
    }

    /**
     * Load default read converter
     *
     * @return
     */
    public static Map<ConverterKey, Converter<?>> loadDefaultReadConverter() {
        return loadAllConverter();
    }

    /**
     * Load all converter
     *
     * @return
     */
    public static Map<ConverterKey, Converter<?>> loadAllConverter() {
        return allConverter;
    }

    private static void putAllConverter(Converter<?> converter) {
        allConverter.put(ConverterKeyBuild.buildKey(converter.supportJavaTypeKey(), converter.supportTypeKey()),
                converter);
    }
}
