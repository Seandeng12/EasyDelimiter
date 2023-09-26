package net.seandeng.delimiter.metadata.data;

import com.alibaba.excel.enums.CellDataTypeEnum;
import net.seandeng.delimiter.analysis.DelimiterAnalyserImpl;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * read field data
 *
 * @author sean.deng
 */
public class ReadCellData implements Cloneable {

    private static final Logger LOGGER = LoggerFactory.getLogger(DelimiterAnalyserImpl.class);

    @Getter
    @Setter
    private CellDataTypeEnum type;

    @Getter
    @Setter
    private String stringValue;

    public ReadCellData() {
    }

    @Override
    public Object clone() {
        //浅拷贝
        try {
            // 直接调用父类的clone()方法
            return super.clone();
        } catch (CloneNotSupportedException e) {
            LOGGER.error("create readCellData error : {}", e.getMessage());;
        }
        return new ReadCellData();
    }
}
