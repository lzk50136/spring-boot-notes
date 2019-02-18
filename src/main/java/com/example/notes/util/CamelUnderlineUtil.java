package com.example.notes.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 驼峰和下划线互相转换工具类
 *
 * @author Lzk
 */
public class CamelUnderlineUtil {
    private static final char UNDERLINE = '_';

    /**
     * 驼峰转下划线
     *
     * @param param 驼峰参数
     * @return 下划线参数
     */
    public static String camelToUnderline(String param) {
        if (StringUtils.isEmpty(param)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int len = param.length();
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 下划线转驼峰
     *
     * @param param 下划线参数
     * @return 驼峰参数
     */
    public static String underlineToCamel(String param) {
        if (StringUtils.isEmpty(param)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int len = param.length();
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (c == UNDERLINE) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

}
