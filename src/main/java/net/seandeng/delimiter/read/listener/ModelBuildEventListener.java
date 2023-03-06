package net.seandeng.delimiter.read.listener;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import net.seandeng.delimiter.context.AnalysisContext;
import net.seandeng.delimiter.exception.DelimiterAnalysisException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * default User event
 *
 * @author deng
 */
@Slf4j
public class ModelBuildEventListener implements IgnoreExceptionReadListener<Object> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModelBuildEventListener.class);

    @Override
    public void invoke(Object data, AnalysisContext context) {
        context.readRowHolder().setCurrentRowAnalysisResult(buildDefineClassModel(data, context));
    }

    private Object buildDefineClassModel(Object data, AnalysisContext context) {
        Object oneData = null;
        try {
            String line = StrUtil.str(data, "UTF-8");
            final String[] split = line.split("\\|");
            final List<String> values = ListUtil.of(split);
            // new instance Class
            oneData = Class.forName(context.readWorkbook().getClazz().getName()).newInstance();
            int fieldIndex = 0;
            for (Map.Entry<Integer, Field> entry : context.readRowHolder().getSortedAllFieldMap().entrySet()) {
                final String value = values.get(fieldIndex);
                fieldIndex ++;

                final Field field = entry.getValue();
                field.setAccessible(true);

                // 判断field
                final Class<?> type = field.getType();
                Object tempValue = value;
                if (type == Integer.class) {
                    tempValue = Integer.valueOf(value);
                } else if (type == Long.class) {
                    tempValue = Long.valueOf(value);
                }
                field.set(oneData, tempValue);
            }
        } catch (Exception e) {
            LOGGER.error("reflect Class error", e);
            throw new DelimiterAnalysisException("reflect Class error", e);
        }
        return oneData;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}
