package net.seandeng.delimiter.metadata.data;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * data format
 *
 * @author Jiaju Zhuang
 */
@Getter
@Setter
@EqualsAndHashCode
public class DataFormatData {
    /**
     * index
     */
    private Short index;

    /**
     * format
     */
    private String format;

    /**
     * The source is not empty merge the data to the target.
     *
     * @param source source
     * @param target target
     */
    public static void merge(DataFormatData source, DataFormatData target) {
        if (source == null || target == null) {
            return;
        }
        if (source.getIndex() != null) {
            target.setIndex(source.getIndex());
        }
        if (StringUtils.isNotBlank(source.getFormat())) {
            target.setFormat(source.getFormat());
        }
    }

    @Override
    public DataFormatData clone() {
        DataFormatData dataFormatData = new DataFormatData();
        dataFormatData.setIndex(getIndex());
        dataFormatData.setFormat(getFormat());
        return dataFormatData;
    }
}
