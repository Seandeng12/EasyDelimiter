package net.seandeng.delimiter.metadata;

import net.seandeng.delimiter.util.ClassUtils;
import net.seandeng.delimiter.util.FieldUtils;
import net.seandeng.delimiter.util.MapUtils;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.TreeMap;

/**
 * line Property
 *
 * @author deng
 */
@Getter
@Setter
@EqualsAndHashCode
public class DelimiterLineProperty {

    private static final Logger LOGGER = LoggerFactory.getLogger(DelimiterLineProperty.class);

    /**
     * Custom class
     */
    private Class<?> lineClazz;
    /**
     * Configuration lines information
     */
    private Map<Integer, Line> lineMap;

    public DelimiterLineProperty(Class<?> lineClazz) {
        this.lineClazz = lineClazz;
        lineMap = new TreeMap<>();
        initLineProperties();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("The initialization 'DelimiterLineProperty' is complete ");
        }
    }

    private void initLineProperties() {
        if (lineClazz == null) {
            return;
        }
        // Declared fields
        Map<Integer, Field> sortedAllFieldMap = MapUtils.newTreeMap();

        ClassUtils.declaredFields(lineClazz, sortedAllFieldMap);

        for (Map.Entry<Integer, Field> entry : sortedAllFieldMap.entrySet()) {
            initOneLineProperty(entry.getKey(), entry.getValue());
        }
    }

    private void initOneLineProperty(int index, Field field) {
        String fieldName = FieldUtils.resolveCglibFieldName(field);
        Line head = new Line(index, field, fieldName);
        lineMap.put(index, head);
    }
}
