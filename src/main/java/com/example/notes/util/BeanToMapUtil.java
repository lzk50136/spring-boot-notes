package com.example.notes.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.cglib.beans.BeanMap;

import java.util.List;
import java.util.Map;

/**
 * bean和map互相转换工具类
 *
 * @author Lzk
 */
public class BeanToMapUtil {

    /**
     * bean转map带object
     *
     * @param bean bean
     * @param <T>  泛型
     * @return map
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = Maps.newHashMap();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(CamelUnderlineUtil.camelToUnderline(key + ""), beanMap.get(key));
            }
        }
        return map;
    }

    /**
     * bean转map带string
     *
     * @param bean bean
     * @param <T>  泛型
     * @return map
     */
    public static <T> Map<String, String> beanToStringMap(T bean) {
        Map<String, String> map = Maps.newHashMap();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                if (beanMap.get(key) != null) {
                    map.put(CamelUnderlineUtil.camelToUnderline(key + ""), beanMap.get(key) + "");
                }
            }
        }
        return map;
    }

    /**
     * map转bean带object
     *
     * @param map  map
     * @param bean bean
     * @param <T>  泛型
     * @return bean
     */
    public static <T> T mapToBean(Map<String, Object> map, T bean) {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }

    /**
     * Object数组转Map数组
     *
     * @param objList Object数组
     * @param <T>     泛型
     * @return Map数组
     */
    public static <T> List<Map<String, Object>> objectsToMaps(List<T> objList) {
        List<Map<String, Object>> list = Lists.newArrayList();
        if (objList != null && objList.size() > 0) {
            Map<String, Object> map;
            T bean;
            for (int i = 0, size = objList.size(); i < size; i++) {
                bean = objList.get(i);
                map = beanToMap(bean);
                list.add(map);
            }
        }
        return list;
    }

    /**
     * Map数组转Object数组
     *
     * @param maps  maps
     * @param clazz clazz
     * @param <T>泛型
     * @return 对象数组
     * @throws InstantiationException InstantiationException
     * @throws IllegalAccessException InstantiationException
     */
    public static <T> List<T> mapsToObjects(List<Map<String, Object>> maps, Class<T> clazz) throws InstantiationException, IllegalAccessException {
        List<T> list = Lists.newArrayList();
        if (maps != null && maps.size() > 0) {
            Map<String, Object> map;
            T bean;
            for (Map<String, Object> map1 : maps) {
                map = map1;
                bean = clazz.newInstance();
                mapToBean(map, bean);
                list.add(bean);
            }
        }
        return list;
    }

}
