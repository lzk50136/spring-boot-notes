package com.example.notes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * jar转war
 * 1，启动类继承SpringBootServletInitializer
 * 2，重写configure方法
 * 3，pom的packaging由jar改为war
 * 4,添加去除tomcat相关包的依赖
 * 5，添加jmx唯一名称到主配置文件
 * 6，去除端口和项目名称配置
 * 7，文件名等于项目访问路径名
 *
 * @author Lzk
 */
@SpringBootApplication
public class SpringBootNotesApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootNotesApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringBootNotesApplication.class);
    }

}