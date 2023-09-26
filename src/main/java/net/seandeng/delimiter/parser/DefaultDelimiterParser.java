package net.seandeng.delimiter.parser;

import com.alibaba.excel.enums.CellDataTypeEnum;
import net.seandeng.delimiter.context.AnalysisContext;
import net.seandeng.delimiter.metadata.data.ReadCellData;
import net.seandeng.delimiter.read.metadata.holder.ReadFileHolder;
import net.seandeng.delimiter.read.metadata.holder.ReadRowHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * file parse
 *
 * @author deng
 */
@Slf4j
public class DefaultDelimiterParser {

    private final String file;

    private final AnalysisContext analysisContext;

    private final Class<?> clazz;

    public DefaultDelimiterParser(AnalysisContext analysisContext, String file, Class<?> clazz) {
        this.analysisContext = analysisContext;
        this.file = file;
        this.clazz = clazz;
    }

    /**
     * 两种方法
     * 1: 直接读取所有行内容 (容易出现内存溢出 -> 如何list太大)
     * <p>
     * 2:使用 Files 类静态方法进行文件操作注意释放文件句柄
     */
    public void parser() {
        AtomicInteger rowIndex = new AtomicInteger(0);
        // 按需读取
        try (Stream<String> streamLine = Files.lines(Paths.get(file), StandardCharsets.UTF_8)) {
            streamLine.forEach(line -> {
                doParser(line, rowIndex.get());
                rowIndex.incrementAndGet();
            });
        } catch (IOException e) {
            log.info("读取文件行异常：{}", e.getMessage(), e);
        }
    }

    private void doParser(String line, Integer rowIndex) {
        ReadFileHolder readFileHolder = analysisContext.readFileHolder();
        String[] cells = line.split("\\|");
        int loopLength = cells.length;
        ReadCellData cellData = new ReadCellData();
        for (int j = 0; j < loopLength; j++) {
            String value = cells[j];
            ReadCellData tempCellData = (ReadCellData) cellData.clone();
            if (StringUtils.isEmpty(value) || "null".equals(value)) {
                tempCellData.setType(CellDataTypeEnum.EMPTY);
            } else {
                tempCellData.setType(CellDataTypeEnum.STRING);
            }
            tempCellData.setStringValue(value);
            // 放入cellMap中
            readFileHolder.getCellMap().put(j, tempCellData);
        }
        analysisContext.readRowHolder(new ReadRowHolder(rowIndex, readFileHolder.getCellMap()));
        analysisContext.readRowHolder().setCurrentRowAnalysisResult(readFileHolder.getCellMap());
        analysisContext.analysisEventProcessor().endLine(analysisContext);
    }
}
