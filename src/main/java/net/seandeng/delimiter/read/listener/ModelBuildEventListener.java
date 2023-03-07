package net.seandeng.delimiter.read.listener;

import lombok.extern.slf4j.Slf4j;
import net.seandeng.delimiter.context.AnalysisContext;
import net.seandeng.delimiter.exception.DelimiterDataConvertException;
import net.seandeng.delimiter.metadata.Line;
import net.seandeng.delimiter.metadata.data.ReadCellData;
import net.seandeng.delimiter.read.metadata.holder.ReadFileHolder;
import net.seandeng.delimiter.read.metadata.property.DelimiterReadLineProperty;
import net.seandeng.delimiter.util.BeanMapUtils;
import net.seandeng.delimiter.util.ClassUtils;
import net.seandeng.delimiter.util.ConverterUtils;
import org.springframework.cglib.beans.BeanMap;

import java.util.Map;

/**
 * default User event
 *
 * @author deng
 */
@Slf4j
public class ModelBuildEventListener implements IgnoreExceptionReadListener<Map<Integer, ReadCellData<?>>> {

    @Override
    public void invoke(Map<Integer, ReadCellData<?>> cellDataMap, AnalysisContext context) {
        context.readRowHolder().setCurrentRowAnalysisResult(buildDefineClassModel(cellDataMap, context.readFileHolder(), context));
    }

    private Object buildDefineClassModel(Map<Integer, ReadCellData<?>> cellDataMap, ReadFileHolder readFileHolder, AnalysisContext context) {
        DelimiterReadLineProperty delimiterReadLineProperty = readFileHolder.delimiterReadLineProperty();
        Object resultModel;
        try {
            resultModel = delimiterReadLineProperty.getLineClazz().newInstance();
        } catch (Exception e) {
            throw new DelimiterDataConvertException("Can not instance class: " + delimiterReadLineProperty.getLineClazz().getName(), e);
        }
        Map<Integer, Line> lineMap = delimiterReadLineProperty.getLineMap();
        BeanMap dataMap = BeanMapUtils.create(resultModel);
        for (Map.Entry<Integer, Line> entry : lineMap.entrySet()) {
            Integer index = entry.getKey();
            Line line = entry.getValue();
            String fieldName = line.getFieldName();
            if (!cellDataMap.containsKey(index)) {
                continue;
            }
            ReadCellData<?> cellData = cellDataMap.get(index);
            Object value = ConverterUtils.convertToJavaObject(
                    cellData,
                    line.getField(),
                    ClassUtils.declaredDelimiterContentProperty(dataMap, readFileHolder.delimiterReadLineProperty().getLineClazz(), fieldName),
                    readFileHolder.converterMap(),
                    context,
                    context.readRowHolder().getRowIndex(),
                    index);
            if (value != null) {
                dataMap.put(fieldName, value);
            }
        }
        return resultModel;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}
