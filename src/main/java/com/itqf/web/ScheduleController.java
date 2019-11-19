package com.itqf.web;

import com.itqf.dto.TableData;
import com.itqf.entity.ScheduleJob;
import com.itqf.service.ScheduleService;
import com.itqf.utils.R;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
public class ScheduleController {

    @Resource
    private ScheduleService scheduleService;

    @RequestMapping("/schedule/job/list")
    public TableData getSchedule(String search ,String order,int limit,int offset){
        return scheduleService.getScheduleList(search,order,limit,offset);
    }

    @RequestMapping("/schedule/job/info/{jobId}")
    public R getScheduleInfo(@PathVariable long jobId){
        return scheduleService.getInfo(jobId);
    }

    @RequestMapping("/schedule/job/save")
    public R insertJob(@RequestBody ScheduleJob scheduleJob){
        scheduleJob.setCreateTime(new Date());
        scheduleJob.setStatus((byte)0);
        return scheduleService.insertJob(scheduleJob);
    }

    @RequestMapping("/schedule/job/update")
    public R updateJob(@RequestBody ScheduleJob scheduleJob){
        return scheduleService.updateJob(scheduleJob);
    }
    @RequestMapping("/schedule/job/del")
    public R deleteJob(@RequestBody List<Long> ids){
        return scheduleService.deleteByIds(ids);
    }
    @RequestMapping("/schedule/job/resume")
    public R resumeTask(@RequestBody List<Long> ids){
        return scheduleService.resumeTask(ids);
    }
    @RequestMapping("/schedule/job/pause")
    public R pauseTask(@RequestBody List<Long> ids){
        return scheduleService.pause(ids);
    }
    @RequestMapping("/schedule/job/run")
    public R runOnceTask(@RequestBody List<Long> ids){
        return scheduleService.runOnce(ids);
    }

}
