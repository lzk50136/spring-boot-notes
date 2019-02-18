package com.example.notes.component;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 异步任务
 *
 * @author Lzk
 */
@Component
public class AsyncTask {

    @Async
    public void executeAsyncTask() {

    }

}