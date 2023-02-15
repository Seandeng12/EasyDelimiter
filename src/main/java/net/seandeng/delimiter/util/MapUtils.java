package net.seandeng.delimiter.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.BeanMap;

import java.util.Map;

/**
 * DAS-SECURITY
 *
 * @author : sean
 * @date : 2022/12/7 12:18
 **/
@Slf4j
public class MapUtils {

    public static <T> T mapToBean(Map<String, Object> map, Class<T> clazz) {
        try {
            T bean = clazz.newInstance();
            BeanMap beanMap = BeanMap.create(bean);
            beanMap.putAll(map);
            return bean;
        } catch (Exception e) {
            log.error("转换异常", e);
        }
        return null;
    }
}
