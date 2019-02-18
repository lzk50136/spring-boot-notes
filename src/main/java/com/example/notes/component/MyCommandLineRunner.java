package com.example.notes.component;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * SpringBoot给我们提供了两种“开机启动”方式：ApplicationRunner和CommandLineRunner。
 * <p>
 * 如果有多个类实现CommandLineRunner接口，如何保证顺序？
 * SpringBoot在项目启动后会遍历所有实现CommandLineRunner的实体类并执行run方法，
 * 如果需要按照一定的顺序去执行，那么就需要在实体类上使用一个@Order注解（或者实现Order接口）来表明顺序
 *
 * @author Lzk
 */
@Component
@Order(value = 1)
public class MyCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

    }

}
