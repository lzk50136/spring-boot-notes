package com.example.notes.config;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 自定义配置文件读取
 *
 * @author Lzk
 */
public class ConfigProperty {
    private static final String URL = "spring-boot-notes.properties";
    private static final Properties PROPERTIES;

    static {
        PROPERTIES = new Properties();
        loadProperties();
    }

    private static void loadProperties() {
        InputStream is = null;
        Resource rs = new ClassPathResource(URL);
        try {
            is = rs.getInputStream();
            PROPERTIES.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getKey(String key) {
        return PROPERTIES.getProperty(key);
    }

}
