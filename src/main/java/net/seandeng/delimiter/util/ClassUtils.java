package net.seandeng.delimiter.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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


    /**
     * <p>Gets a {@code List} of all interfaces implemented by the given
     * class and its superclasses.</p>
     *
     * <p>The order is determined by looking through each interface in turn as
     * declared in the source file and following its hierarchy up. Then each
     * superclass is considered in the same way. Later duplicates are ignored,
     * so the order is maintained.</p>
     *
     * @param cls the class to look up, may be {@code null}
     * @return the {@code List} of interfaces in order,
     * {@code null} if null input
     */
    public static List<Class<?>> getAllInterfaces(final Class<?> cls) {
        if (cls == null) {
            return null;
        }

        final LinkedHashSet<Class<?>> interfacesFound = new LinkedHashSet<>();
        getAllInterfaces(cls, interfacesFound);

        return new ArrayList<>(interfacesFound);
    }


    /**
     * Gets the interfaces for the specified class.
     *
     * @param cls             the class to look up, may be {@code null}
     * @param interfacesFound the {@code Set} of interfaces for the class
     */
    private static void getAllInterfaces(Class<?> cls, final HashSet<Class<?>> interfacesFound) {
        while (cls != null) {
            final Class<?>[] interfaces = cls.getInterfaces();

            for (final Class<?> i : interfaces) {
                if (interfacesFound.add(i)) {
                    getAllInterfaces(i, interfacesFound);
                }
            }

            cls = cls.getSuperclass();
        }
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

    @Getter
    @Setter
    @AllArgsConstructor
    public static class ContentPropertyKey {
        private Class<?> clazz;
        private Class<?> lineClass;
        private String fieldName;
    }
}
