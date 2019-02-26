package com.example.notes.component;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 执行定时任务
 *
 * @author Lzk
 */
@Component
public class ScheduledTask {

    /**
     * Cron表达式
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void executeScheduledTask() {

    }

}
