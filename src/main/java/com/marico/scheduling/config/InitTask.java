package com.marico.scheduling.config;

import com.marico.scheduling.model.SysJob;
import com.marico.scheduling.service.SysJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 项目初始化定时任务执行
 * Created by marico on 2023-03-27
 */

@Component
public class InitTask implements CommandLineRunner {

    @Autowired
    private CronTaskRegister cronTaskRegister;

    @Autowired
    private SysJobService sysJobService;

    @Override
    public void run(String... args) throws Exception {
        // 获取所有可执行的定时任务
        List<SysJob> list = sysJobService.getSysJobByStatus(1);
        for (SysJob sysJob : list) {
            // 遍历 list，执行每一个定时任务
            cronTaskRegister.addCronTask(new SchedulingRunnable(sysJob.getBeanName(), sysJob.getMethodName(), sysJob.getMethodParams()),
                    sysJob.getCronExpression());
        }
    }
}
