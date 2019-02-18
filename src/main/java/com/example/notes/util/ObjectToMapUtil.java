package com.example.notes.util;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 用于将对象中不为null 和 ‘’ （空串） 的属性，转化成Map集合
 * 如果值的前后有空格 还会去除掉
 * 对传递的数值 进行清洗，只传递有值得属性，提高系统效率，减少带宽占用
 *
 * @author Lzk
 */
public class ObjectToMapUtil {
    private static final String SERIAL_VERSION_UID = "serialVersionUID";

    /**
     * 对传入的对象进行数据清洗，将属性值为null和""的去掉，其他字段名和属性值存入map集合
     */
    public static Map<String, String> objectToMap(Object requestParameters) throws IllegalAccessException {
        Map<String, String> map = new HashMap<>();
        // 获取f对象对应类中的所有属性域
        Field[] fields = requestParameters.getClass().getDeclaredFields();
        for (int i = 0, len = fields.length; i < len; i++) {
            String varName = fields[i].getName();
            // 获取原来的访问控制权限
            boolean accessFlag = fields[i].isAccessible();
            // 修改访问控制权限
            fields[i].setAccessible(true);
            // 获取在对象f中属性fields[i]对应的对象中的变量
            Object o = fields[i].get(requestParameters);
            if (o != null && StringUtils.isNotBlank(o.toString().trim())) {
                map.put(varName, o.toString().trim());
                // 恢复访问控制权限
                fields[i].setAccessible(accessFlag);
            }
        }
        if (map.containsKey(SERIAL_VERSION_UID)) {
            map.remove("serialVersionUID");
        }
        return map;
    }

}