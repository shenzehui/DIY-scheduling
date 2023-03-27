package com.marico.scheduling.config;

import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ScheduledFuture;

/**
 * Created by marico on 2023-03-27
 */

public class ScheduledTask {
    // 定时任务执行结果
    volatile ScheduledFuture<?> future;

    public void cancel() {
        ScheduledFuture<?> future = this.future;
        if (future != null) {
            future.cancel(true);
        }
    }
}
