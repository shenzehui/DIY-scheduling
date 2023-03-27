package com.marico.scheduling.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

/**
 * 一个SysJob对象代表一个定时任务
 * Created by marico on 2023-03-27
 */

@Entity(name = "t_sys_job")
@Data
public class SysJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer jobId;

    private String beanName;

    private String methodName;

    // Cron 表达式
    private String methodParams;

    private String cronExpression;

    // 定时任务状态 0 表示暂停 1 表示运行
    private Integer status;

    private String remark;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;


    /**
     * beanName\methodName\methodParams\cronExpression 这四个如果全部相同，就认为是同一个定时任务
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysJob sysJob = (SysJob) o;
        return Objects.equals(beanName, sysJob.beanName) &&
                Objects.equals(methodName, sysJob.methodName) &&
                Objects.equals(methodParams, sysJob.methodParams) &&
                Objects.equals(cronExpression, sysJob.cronExpression);
    }

    @Override
    public int hashCode() {
        return Objects.hash(beanName, methodName, methodParams, cronExpression);
    }
}
