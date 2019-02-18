package com.example.notes.component;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * cron配置定时任务
 *
 * @author Lzk
 */
@Component
public class ScheduledTask {

    @Scheduled(cron = "0/5 * * * * ?")
    public void doTask() {

    }

}
