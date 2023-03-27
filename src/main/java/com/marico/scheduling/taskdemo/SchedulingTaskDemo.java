package com.marico.scheduling.taskdemo;

import org.springframework.stereotype.Component;

/**
 * Created by marico on 2023-03-27
 */

@Component
public class SchedulingTaskDemo {

    public void taskWithParams(String params) {
        System.out.println("执行带参数的定时任务..." + params);
    }

    public void taskWithoutParams() {
        System.out.println("执行不带参数的定时任务...");
    }
}
