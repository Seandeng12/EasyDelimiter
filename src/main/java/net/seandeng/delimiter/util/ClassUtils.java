package net.seandeng.delimiter.util;

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
}
