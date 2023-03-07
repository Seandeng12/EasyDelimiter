package net.seandeng.delimiter.util;

import com.alibaba.excel.util.MapUtils;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import net.seandeng.delimiter.metadata.DelimiterContentProperty;
import org.springframework.cglib.beans.BeanMap;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 反射工具类
 *
 * @author sean.deng
 */
public class ClassUtils {

    public static final Map<Class<?>, FieldCache> FIELD_CACHE = new ConcurrentHashMap<>();

    /**
     * The cache configuration information for each of the class
     */
    public static final Map<Class<?>, Map<String, DelimiterContentProperty>> CLASS_CONTENT_CACHE
            = new ConcurrentHashMap<>();

    /**
     * The cache configuration information for each of the class
     */
    public static final Map<ContentPropertyKey, DelimiterContentProperty> CONTENT_CACHE = new ConcurrentHashMap<>();

    /**
     * Parsing field in the class
     *
     * @param clazz             Need to parse the class
     * @param sortedAllFieldMap Complete the map of sorts
     */
    public static void declaredFields(Class<?> clazz, Map<Integer, Field> sortedAllFieldMap) {
        FieldCache fieldCache = declaredFields(clazz);
        if (fieldCache == null) {
            return;
        }

        Map<Integer, Field> originSortedAllFieldMap = fieldCache.getSortedAllFieldMap();
        sortedAllFieldMap.putAll(originSortedAllFieldMap);
    }

    private static FieldCache declaredFields(Class<?> clazz) {
        if (clazz == null) {
            return null;
        }
        return FIELD_CACHE.computeIfAbsent(clazz, key -> {
            List<Field> tempFieldList = new ArrayList<>();
            Class<?> tempClass = clazz;
            while (tempClass != null) {
                Collections.addAll(tempFieldList, tempClass.getDeclaredFields());
                tempClass = tempClass.getSuperclass();
            }
            // Screening of field
            Map<Integer, List<Field>> orderFieldMap = new TreeMap<>();

            for (Field field : tempFieldList) {
                declaredOneField(field, orderFieldMap);
            }
            return new FieldCache(buildSortedAllFieldMap(orderFieldMap));
        });
    }

    private static Map<Integer, Field> buildSortedAllFieldMap(Map<Integer, List<Field>> orderFieldMap) {

        Map<Integer, Field> sortedAllFieldMap = new HashMap<>((orderFieldMap.size()) * 4 / 3 + 1);

        int index = 0;
        for (List<Field> fieldList : orderFieldMap.values()) {
            for (Field field : fieldList) {
                sortedAllFieldMap.put(index, field);
                index++;
            }
        }
        return sortedAllFieldMap;
    }

    private static void declaredOneField(Field field, Map<Integer, List<Field>> orderFieldMap) {
        // static，final，transient field will not add to field list
        boolean isStaticFinalOrTransient =
                (Modifier.isStatic(field.getModifiers()) && Modifier.isFinal(field.getModifiers()))
                        || Modifier.isTransient(field.getModifiers());
        if (isStaticFinalOrTransient) {
            return;
        }
        int order = Integer.MAX_VALUE;
        List<Field> orderFieldList = orderFieldMap.computeIfAbsent(order, key -> new ArrayList<>());
        orderFieldList.add(field);
    }

    private static class FieldCache {

        private final Map<Integer, Field> sortedAllFieldMap;

        public FieldCache(Map<Integer, Field> sortedAllFieldMap) {
            this.sortedAllFieldMap = sortedAllFieldMap;
        }

        public Map<Integer, Field> getSortedAllFieldMap() {
            return sortedAllFieldMap;
        }
    }

    public static DelimiterContentProperty declaredDelimiterContentProperty(Map<?, ?> dataMap, Class<?> lineClazz, String fieldName) {
        Class<?> clazz = null;
        if (dataMap instanceof BeanMap) {
            Object bean = ((BeanMap) dataMap).getBean();
            if (bean != null) {
                clazz = bean.getClass();
            }
        }
        return getDelimiterLineProperty(clazz, lineClazz, fieldName);
    }

    private static DelimiterContentProperty getDelimiterLineProperty(Class<?> clazz, Class<?> lineClazz, String fieldName) {
        return CONTENT_CACHE.computeIfAbsent(buildKey(clazz, lineClazz, fieldName), key -> Optional.ofNullable(declaredFieldContentMap(clazz))
                .map(map -> map.get(fieldName))
                .orElse(null));

    }

    private static ContentPropertyKey buildKey(Class<?> clazz, Class<?> headClass, String fieldName) {
        return new ContentPropertyKey(clazz, headClass, fieldName);
    }

    private static Map<String, DelimiterContentProperty> declaredFieldContentMap(Class<?> clazz) {
        if (clazz == null) {
            return null;
        }
        return CLASS_CONTENT_CACHE.computeIfAbsent(clazz, key -> {
            List<Field> tempFieldList = new ArrayList<>();
            Class<?> tempClass = clazz;
            while (tempClass != null) {
                Collections.addAll(tempFieldList, tempClass.getDeclaredFields());
                // Get the parent class and give it to yourself
                tempClass = tempClass.getSuperclass();
            }
            Map<String, DelimiterContentProperty> fieldContentMap = MapUtils.newHashMapWithExpectedSize(
                    tempFieldList.size());
            for (Field field : tempFieldList) {
                DelimiterContentProperty delimiterContentProperty = new DelimiterContentProperty();
                delimiterContentProperty.setField(field);
                fieldContentMap.put(field.getName(), delimiterContentProperty);
            }
            return fieldContentMap;
        });
    }

    @Getter
    @Setter
    @EqualsAndHashCode
    @AllArgsConstructor
    public static class ContentPropertyKey {
        private Class<?> clazz;
        private Class<?> lineClass;
        private String fieldName;
    }
}
