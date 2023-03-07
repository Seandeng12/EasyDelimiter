package net.seandeng.delimiter.parser;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.io.IoUtil;
import com.alibaba.excel.enums.CellDataTypeEnum;
import net.seandeng.delimiter.context.AnalysisContext;
import net.seandeng.delimiter.metadata.data.ReadCellData;
import net.seandeng.delimiter.read.metadata.holder.ReadFileHolder;
import net.seandeng.delimiter.read.metadata.holder.ReadRowHolder;
import net.seandeng.delimiter.util.ClassUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * file parse
 *
 * @author deng
 */
public class DefaultDelimiterParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultDelimiterParser.class);

    private final AnalysisContext analysisContext;

    private final InputStream inputStream;

    private final Class<?> clazz;

    public DefaultDelimiterParser(AnalysisContext analysisContext, InputStream inputStream, Class<?> clazz) {
        this.analysisContext = analysisContext;
        this.inputStream = inputStream;
        this.clazz = clazz;
    }

    public void parser() {
        List<String> lines = IoUtil.readLines(inputStream, "UTF-8", new ArrayList<>());
        Map<Integer, Field> sortedAllFieldMap = new TreeMap<>();
        sortedField(clazz, sortedAllFieldMap);
        LOGGER.info("DefaultDelimiterParser get sorted Map" + sortedAllFieldMap);
        for (int i = 0; i < lines.size(); i ++) {
            int rowIndex = i;
            String line = lines.get(rowIndex);
            ReadFileHolder readFileHolder = analysisContext.readFileHolder();
            String[] split = line.split("\\|");
            List<String> values = ListUtil.of(split);
            for (int j = 0; j < values.size(); j++) {
                ReadCellData<?> tempCellData = new ReadCellData<>();
                tempCellData.setStringValue(values.get(j));
                tempCellData.setType(CellDataTypeEnum.STRING);
                readFileHolder.getCellMap().put(j, tempCellData);
            }
            analysisContext.readRowHolder(new ReadRowHolder(rowIndex, readFileHolder.getCellMap()));
            analysisContext.readRowHolder().setCurrentRowAnalysisResult(readFileHolder.getCellMap());
            analysisContext.analysisEventProcessor().endLine(analysisContext);
        }
    }

    /**
     * sort the bean field which it has field format
     *
     * @param clazz             bean class
     * @param sortedAllFieldMap field map
     */
    private void sortedField(Class<?> clazz, Map<Integer, Field> sortedAllFieldMap) {
        if (!sortedAllFieldMap.isEmpty()) {
            return;
        }
        ClassUtils.declaredFields(clazz, sortedAllFieldMap);
    }
}
