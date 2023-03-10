package net.seandeng.delimiter.executor;

import cn.hutool.core.util.StrUtil;
import net.seandeng.delimiter.context.DelimiterWriteContext;
import net.seandeng.delimiter.util.BeanMapUtils;
import net.seandeng.delimiter.util.ClassUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.cglib.beans.BeanMap;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 分隔符格式字符执行
 *
 * @author sean.deng
 */
public class DelimiterWriteExecutor {

    private final DelimiterWriteContext delimiterWriteContext;

    public DelimiterWriteExecutor(DelimiterWriteContext delimiterWriteContext) {
        this.delimiterWriteContext = delimiterWriteContext;
    }

    public void add(Collection<?> data) {
        if (CollectionUtils.isEmpty(data)) {
            data = new ArrayList<>();
        }
        Map<Integer, Field> sortedAllFieldMap = new TreeMap<>();
        // 当前拼接字段的Holder
        for (Object oneRowData : data) {
            addOneRowOfDataToContent(oneRowData, sortedAllFieldMap);
        }
    }

    private void addOneRowOfDataToContent(Object oneRowData, Map<Integer, Field> sortedAllFieldMap) {
        if (oneRowData == null) {
            return;
        }
        // 初始化填写
        StringBuilder content = delimiterWriteContext.writeContentHolder();
        // 直接写入
        addJavaObjectToContent(oneRowData, content, sortedAllFieldMap);
        // 结尾换行符
        content.append("\n");
    }

    private void addJavaObjectToContent(Object oneRowData, StringBuilder content, Map<Integer, Field> sortedAllFieldMap) {
        BeanMap beanMap = BeanMapUtils.create(oneRowData);
        sortedField(oneRowData.getClass(), sortedAllFieldMap);
        int fieldIndex = 0;
        for (Map.Entry<Integer, Field> entry : sortedAllFieldMap.entrySet()) {
            fieldIndex ++;

            Field field = entry.getValue();
            String value = beanMap.get(field.getName()) == null ? "" : StrUtil.toString(beanMap.get(field.getName()));
            if (fieldIndex == sortedAllFieldMap.size()) {
                content.append(value);
            } else {
                content.append(value).append("|");
            }
        }
    }

    /**
     * 分割符具有顺序要求，反射根据类定义的字段排序
     * @param clazz
     * @param sortedAllFieldMap
     */
    private void sortedField(Class<?> clazz, Map<Integer, Field> sortedAllFieldMap) {
        if (!sortedAllFieldMap.isEmpty()) {
            return;
        }
        ClassUtils.declaredFields(clazz, sortedAllFieldMap);
    }
}
