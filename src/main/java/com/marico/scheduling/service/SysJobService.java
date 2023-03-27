package com.marico.scheduling.service;

import com.marico.scheduling.config.CronTaskRegister;
import com.marico.scheduling.config.SchedulingRunnable;
import com.marico.scheduling.dao.SysJobRepository;
import com.marico.scheduling.exception.ApiException;
import com.marico.scheduling.model.SysJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by marico on 2023-03-27
 */

@Service
public class SysJobService {

    @Autowired
    private SysJobRepository sysJobRepository;

    @Autowired
    private CronTaskRegister cronTaskRegister;

    /**
     * 根据任务状态查询定时任务
     */
    public List<SysJob> getSysJobByStatus(int status) {
        return sysJobRepository.findAllByStatus(status);
    }

    /**
     * 查询所有定时任务
     */
    public List<SysJob> getAllJobs() {
        return sysJobRepository.findAll();
    }

    public Boolean updateSysJob(SysJob sysJob) {
        sysJob.setUpdateTime(new Date());
        SysJob job = sysJobRepository.saveAndFlush(sysJob);
        if (job != null) {
            // 更新成功
            SchedulingRunnable runnable = new SchedulingRunnable(sysJob.getBeanName(), sysJob.getMethodName(), sysJob.getMethodParams());
            if (sysJob.getStatus() == 1) {
                // 定时任务开启状态，开启定时任务
                cronTaskRegister.addCronTask(runnable, sysJob.getCronExpression());
            } else {
                // 定时任务是关闭状态，移除定时任务
                cronTaskRegister.removeCronTask(runnable);
            }
            return true;
        }
        return false;
    }

    public Boolean addSysJob(SysJob sysJob) {
        List<SysJob> list = sysJobRepository.findAll();
        for (SysJob job : list) {
            if (job.equals(sysJob)) {
                // 作业重复
                throw new ApiException("作业重复");
            }
        }
        sysJob.setCreateTime(new Date());
        sysJob.setUpdateTime(new Date());
        SysJob sj = sysJobRepository.save(sysJob);
        if (sj != null && sj.getStatus() == 1) {
            // 添加成功
            SchedulingRunnable runnable = new SchedulingRunnable(sysJob.getBeanName(), sysJob.getMethodName(), sysJob.getMethodParams());
            // 开启定时任务
            cronTaskRegister.addCronTask(runnable, sysJob.getCronExpression());
            return true;
        }
        //  status = 0 默认不开启
        return true;
    }

    public Boolean delSysJobById(Integer jobId) {
        SysJob sysJob = sysJobRepository.findSysJobByJobId(jobId);
        if (sysJob == null) {
            throw new ApiException("该数据不存在");
        }
        sysJobRepository.deleteById(jobId);
        SchedulingRunnable runnable = new SchedulingRunnable(sysJob.getBeanName(), sysJob.getMethodName(), sysJob.getMethodParams());
        cronTaskRegister.removeCronTask(runnable);
        return true;
    }
}
