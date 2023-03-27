package com.marico.scheduling.dao;

import com.marico.scheduling.model.SysJob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by marico on 2023-03-27
 */

public interface SysJobRepository extends JpaRepository<SysJob, Integer> {

    /**
     * 根据任务状态查询定时任务
     * @param status 任务状态
     */
    List<SysJob> findAllByStatus(Integer status);

    /**
     * 根据id主键删除
     * @param jobId id主键
     */
    SysJob findSysJobByJobId(Integer jobId);

}
