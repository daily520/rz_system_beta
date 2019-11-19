package com.itqf.utils;

import com.alibaba.fastjson.JSON;
import com.itqf.Exception.RZException;
import com.itqf.entity.ScheduleJob;
import com.itqf.quartz.MyJob;
import org.quartz.*;

public class ScheduleUtils {

    private static final String JOB_NAME_PREFIX="myJob_";
    private static final String JOB_GROUP_PREFIX="myJobGroup_";
    private static final String TRIGGER_NAME_PREFIX="myTrigger_";
    private static final String TRIGGER_GROUP_PREFIX="myTriggerGroup_";

    public static void createTask(Scheduler scheduler, ScheduleJob scheduleJob) {
        try{
            JobDetail jobDetail= JobBuilder.newJob(MyJob.class)
                    .withIdentity(JOB_NAME_PREFIX+scheduleJob.getJobId(),
                            JOB_GROUP_PREFIX+scheduleJob.getJobId()).build();
            String json= JSON.toJSONString(scheduleJob);
            jobDetail.getJobDataMap().put("scheduleJob",json);
            CronTrigger cronTrigger= TriggerBuilder.newTrigger()
                    .withSchedule(CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression()))
                    .withIdentity(TRIGGER_NAME_PREFIX+scheduleJob.getJobId(),TRIGGER_GROUP_PREFIX+scheduleJob.getJobId()).build();
            scheduler.scheduleJob(jobDetail,cronTrigger);
            scheduler.start();
        }catch (Exception e){
            throw new RZException("创建自动任务失败");
        }
    }

    public static void updateSchedule(Scheduler scheduler,ScheduleJob scheduleJob){
        try{
            TriggerKey triggerKey=TriggerKey.triggerKey(TRIGGER_NAME_PREFIX+scheduleJob.getJobId(),TRIGGER_GROUP_PREFIX+scheduleJob.getJobId());
            CronTrigger trigger= (CronTrigger) scheduler.getTrigger(triggerKey);
            CronTrigger newTrigger=trigger.getTriggerBuilder()
                    .withSchedule(CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression())).build();
            scheduler.rescheduleJob(triggerKey,newTrigger);



        }
        catch (Exception e){
            throw new RZException("修改自动任务失败");
        }
    }

    public static boolean deleteSchedule(Scheduler scheduler,long jobId){
        try{
            return scheduler.deleteJob(JobKey.jobKey(JOB_NAME_PREFIX+jobId,JOB_GROUP_PREFIX+jobId));
        }
        catch (Exception e){
            throw new RZException("删除自动任务失败");
        }
    }

    public static  void pause(Scheduler scheduler,long jobId){
        try{
            JobKey jobKey=JobKey.jobKey(JOB_NAME_PREFIX+jobId,JOB_GROUP_PREFIX+jobId);
            scheduler.pauseJob(jobKey);
        }
        catch (Exception e){
            throw new RZException("停止自动任务失败");
        }
    }

    public static void resume(Scheduler scheduler,long jobId){
        try{
            JobKey jobKey=JobKey.jobKey(JOB_NAME_PREFIX+jobId,JOB_GROUP_PREFIX+jobId);
            scheduler.resumeJob(jobKey);
        }
        catch (Exception e){
            throw new RZException("恢复自动任务失败");
        }
    }

    public static void runOnce(Scheduler scheduler,long jobId){
        try{
            JobKey jobKey=JobKey.jobKey(JOB_NAME_PREFIX+jobId,JOB_GROUP_PREFIX+jobId);
            scheduler.triggerJob(jobKey);
        }
        catch (Exception e){
            throw new RZException("运行一次自动任务失败");
        }
    }
}
