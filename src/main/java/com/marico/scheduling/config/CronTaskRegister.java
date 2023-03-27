package com.marico.scheduling.config;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.config.CronTask;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 核心配置类
 * Created by marico on 2023-03-27
 */

@Configuration
public class CronTaskRegister implements DisposableBean {

    // 这个变量中保存着所有的定时任务
    private final Map<Runnable, ScheduledTask> scheduledTasks = new ConcurrentHashMap<>(16);

    @Autowired
    private TaskScheduler taskScheduler;

    public TaskScheduler getTaskScheduler() {
        return taskScheduler;
    }

    /**
     * 添加一个定时任务
     */
    public void addCronTask(Runnable task, String cronExpression) {
        addCronTask(new CronTask(task, cronExpression));
    }

    private void addCronTask(CronTask cronTask) {
        if (cronTask != null) {
            Runnable runnable = cronTask.getRunnable();
            if (scheduledTasks.containsKey(runnable)) {
                // 说明要添加的定时任务已经存在，要把已存在的定时任务移除，添加新的定时任务
                removeCronTask(runnable);
            }
            // 添加一个定时任务
            scheduledTasks.put(runnable, scheduledCronTask(cronTask));
        }
    }

    /**
     * 移除定时任务
     */
    public void removeCronTask(Runnable runnable) {
        // 1.从 Map 集合中移除
        ScheduledTask task = scheduledTasks.remove(runnable);
        // 2.取消正在执行的定时任务
        if (task != null) {
            task.cancel();
        }

    }

    /**
     * 添加一个定时任务
     */
    private ScheduledTask scheduledCronTask(CronTask cronTask) {
        ScheduledTask scheduledTask = new ScheduledTask();
        scheduledTask.future = taskScheduler.schedule(cronTask.getRunnable(), cronTask.getTrigger());
        return scheduledTask;
    }

    @Override
    public void destroy() throws Exception {
        // 1. 让所有定时任务停止执行
        for (ScheduledTask task : scheduledTasks.values()) {
            task.cancel();
        }
        // 2. 清空集合
        scheduledTasks.clear();
    }
}
