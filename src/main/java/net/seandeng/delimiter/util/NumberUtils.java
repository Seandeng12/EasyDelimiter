package net.seandeng.delimiter.util;

import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.util.StringUtils;
import com.alibaba.excel.util.WorkBookUtil;
import net.seandeng.delimiter.metadata.DelimiterContentProperty;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;

/**
 * Number Utils
 *
 * @author deng
 */
public class NumberUtils {
    
    private NumberUtils() {}

    /**
     * format
     *
     * @param num
     * @param contentProperty
     * @return
     */
    public static String format(Number num, DelimiterContentProperty contentProperty) {
        if (contentProperty == null || contentProperty.getNumberFormatProperty() == null
                || StringUtils.isEmpty(contentProperty.getNumberFormatProperty().getFormat())) {
            if (num instanceof BigDecimal) {
                return ((BigDecimal)num).toPlainString();
            } else {
                return num.toString();
            }
        }
        String format = contentProperty.getNumberFormatProperty().getFormat();
        RoundingMode roundingMode = contentProperty.getNumberFormatProperty().getRoundingMode();
        DecimalFormat decimalFormat = new DecimalFormat(format);
        decimalFormat.setRoundingMode(roundingMode);
        return decimalFormat.format(num);
    }

    /**
     * format
     *
     * @param num
     * @param contentProperty
     * @return
     */
    public static WriteCellData<?> formatToCellDataString(Number num, DelimiterContentProperty contentProperty) {
        return new WriteCellData<>(format(num, contentProperty));
    }

    /**
     * format
     *
     * @param num
     * @param contentProperty
     * @return
     */
    public static WriteCellData<?> formatToCellData(Number num, DelimiterContentProperty contentProperty) {
        WriteCellData<?> cellData = new WriteCellData<>(new BigDecimal(num.toString()));
        if (contentProperty != null && contentProperty.getNumberFormatProperty() != null
                && StringUtils.isNotBlank(contentProperty.getNumberFormatProperty().getFormat())) {
            WorkBookUtil.fillDataFormat(cellData, contentProperty.getNumberFormatProperty().getFormat(), null);
        }
        return cellData;
    }

    /**
     * parse
     *
     * @param string
     * @param contentProperty
     * @return
     */
    public static Short parseShort(String string, DelimiterContentProperty contentProperty) throws ParseException {
        if (!hasFormat(contentProperty)) {
            return new BigDecimal(string).shortValue();
        }
        return parse(string, contentProperty).shortValue();
    }

    /**
     * parse
     *
     * @param string
     * @param contentProperty
     * @return
     */
    public static Long parseLong(String string, DelimiterContentProperty contentProperty) throws ParseException {
        if (!hasFormat(contentProperty)) {
            return new BigDecimal(string).longValue();
        }
        return parse(string, contentProperty).longValue();
    }

    /**
     * parse Integer from string
     *
     * @param string          An integer read in string format
     * @param contentProperty Properties of the content read in
     * @return An integer converted from a string
     */
    public static Integer parseInteger(String string, DelimiterContentProperty contentProperty) throws ParseException {
        if (!hasFormat(contentProperty)) {
            return new BigDecimal(string).intValue();
        }
        return parse(string, contentProperty).intValue();
    }

    /**
     * parse
     *
     * @param string
     * @param contentProperty
     * @return
     */
    public static Float parseFloat(String string, DelimiterContentProperty contentProperty) throws ParseException {
        if (!hasFormat(contentProperty)) {
            return new BigDecimal(string).floatValue();
        }
        return parse(string, contentProperty).floatValue();
    }

    /**
     * parse
     *
     * @param string
     * @param contentProperty
     * @return
     */
    public static BigDecimal parseBigDecimal(String string, DelimiterContentProperty contentProperty)
            throws ParseException {
        if (!hasFormat(contentProperty)) {
            return new BigDecimal(string);
        }
        return new BigDecimal(parse(string, contentProperty).toString());
    }

    /**
     * parse
     *
     * @param string
     * @param contentProperty
     * @return
     */
    public static Byte parseByte(String string, DelimiterContentProperty contentProperty) throws ParseException {
        if (!hasFormat(contentProperty)) {
            return new BigDecimal(string).byteValue();
        }
        return parse(string, contentProperty).byteValue();
    }

    /**
     * parse
     *
     * @param string
     * @param contentProperty
     * @return
     */
    public static Double parseDouble(String string, DelimiterContentProperty contentProperty) throws ParseException {
        if (!hasFormat(contentProperty)) {
            return new BigDecimal(string).doubleValue();
        }
        return parse(string, contentProperty).doubleValue();
    }

    private static boolean hasFormat(DelimiterContentProperty contentProperty) {
        return contentProperty != null && contentProperty.getNumberFormatProperty() != null
                && !StringUtils.isEmpty(contentProperty.getNumberFormatProperty().getFormat());
    }

    /**
     * parse
     *
     * @param string
     * @param contentProperty
     * @return
     * @throws ParseException
     */
    private static Number parse(String string, DelimiterContentProperty contentProperty) throws ParseException {
        String format = contentProperty.getNumberFormatProperty().getFormat();
        RoundingMode roundingMode = contentProperty.getNumberFormatProperty().getRoundingMode();
        DecimalFormat decimalFormat = new DecimalFormat(format);
        decimalFormat.setRoundingMode(roundingMode);
        decimalFormat.setParseBigDecimal(true);
        return decimalFormat.parse(string);
    }
}
