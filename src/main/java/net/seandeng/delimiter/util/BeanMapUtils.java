package net.seandeng.delimiter.util;

import org.springframework.cglib.beans.BeanMap;
import org.springframework.cglib.core.DefaultNamingPolicy;

/**
 * bean utils
 *
 * @author sean.deng
 */
public class BeanMapUtils {

    /**
     * Helper method to create a new <code>BeanMap</code>.  For finer
     * control over the generated instance, use a new instance of
     * <code>BeanMap.Generator</code> instead of this static method.
     *
     * @param bean the JavaBean underlying the map
     * @return a new <code>BeanMap</code> instance
     */
    public static BeanMap create(Object bean) {
        BeanMap.Generator gen = new BeanMap.Generator();
        gen.setBean(bean);
        gen.setContextClass(bean.getClass());
        gen.setNamingPolicy(EasyDelimiterNamingPolicy.INSTANCE);
        return gen.create();
    }

    public static class EasyDelimiterNamingPolicy extends DefaultNamingPolicy {
        public static final EasyDelimiterNamingPolicy INSTANCE = new EasyDelimiterNamingPolicy();

        @Override
        protected String getTag() {
            return "ByEasyDelimiterCGLIB";
        }
    }
}
