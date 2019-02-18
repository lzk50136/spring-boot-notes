package com.example.notes.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * bean转json格式或者json转bean格式, 项目中我们通常使用这个工具类进行json---java互相转化
 *
 * @author Lzk
 */
public class JacksonUtil {
    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
    }

    private JacksonUtil() {

    }

    /**
     * @param object java对象
     * @return json字符串
     * @throws JsonProcessingException JsonProcessingException
     */
    public static String beanToJson(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }

    /**
     * @param string json字符串
     * @param tClass 要转换的类型
     * @param <T>    泛型
     * @return java对象
     * @throws IOException IOException
     */
    public static <T> T jsonToBean(String string, Class<T> tClass) throws IOException {
        return mapper.readValue(string, tClass);
    }

}
