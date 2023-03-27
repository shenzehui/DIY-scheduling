package com.marico.scheduling.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * Created by marico on 2023-03-27
 */

@Configuration
public class SchedulingConfig {

    @Bean
    TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        // 最大线程数量
        threadPoolTaskScheduler.setPoolSize(4);
        // 取消定时任务时移除线程
        threadPoolTaskScheduler.setRemoveOnCancelPolicy(true);
        threadPoolTaskScheduler.setThreadNamePrefix("marico-scheduling-task");
        return threadPoolTaskScheduler;
    }
}
