package com.example.notes.component;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * SpringBoot给我们提供了两种“开机启动”方式：ApplicationRunner和CommandLineRunner。
 *
 * @author Lzk
 */
@Component
public class MyApplicationRunner implements ApplicationRunner, Ordered {

    /**
     * 如果想要指定启动方法执行的顺序，
     * 可以通过实现org.springframework.core.Ordered接口或者使用org.springframework.core.annotation.Order注解来实现。
     *
     * @return 指定顺序
     */
    @Override
    public int getOrder() {
        //通过设置这里的数字来知道指定顺序
        return 1;
    }

    @Override
    public void run(ApplicationArguments var1) throws Exception {

    }

}