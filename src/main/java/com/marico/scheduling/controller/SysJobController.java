package com.marico.scheduling.controller;

import com.marico.scheduling.model.RespBean;
import com.marico.scheduling.model.SysJob;
import com.marico.scheduling.service.SysJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by marico on 2023-03-27
 */

@RestController
@RequestMapping("/jobs")
public class SysJobController {

    @Autowired
    private SysJobService sysJobService;

    @GetMapping("/")
    public List<SysJob> getAllJobs() {
        return sysJobService.getAllJobs();
    }

    @PutMapping("/")
    public RespBean updateSysJob(@RequestBody SysJob sysJob) {
        Boolean result = sysJobService.updateSysJob(sysJob);
        if (result) {
            return RespBean.ok("更新成功");
        } else {
            return RespBean.error("更新失败");
        }
    }

    @PostMapping("/")
    public RespBean addSysJob(@RequestBody SysJob sysJob) {
        Boolean result = sysJobService.addSysJob(sysJob);
        if (result) {
            return RespBean.ok("添加成功");
        } else {
            return RespBean.error("添加失败");
        }
    }

    @DeleteMapping("/{jobId}")
    public RespBean deleteSysJob(@PathVariable Integer jobId) {
        Boolean result = sysJobService.delSysJobById(jobId);
        if (result) {
            return RespBean.ok("删除成功");
        } else {
            return RespBean.error("删除失败");
        }
    }


}
