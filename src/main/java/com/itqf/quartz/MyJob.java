package com.itqf.quartz;

import com.alibaba.fastjson.JSON;
import com.itqf.entity.ScheduleJob;
import com.itqf.entity.ScheduleJobLog;
import com.itqf.service.ScheduleLogService;

import com.itqf.utils.SpringContextUtils;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Method;
import java.util.Date;

public class MyJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        long start=System.currentTimeMillis();
        ScheduleJobLog scheduleJobLog=new ScheduleJobLog();
        try {
            JobDataMap jobDataMap=jobExecutionContext.getJobDetail().getJobDataMap();
            String json= (String) jobDataMap.get("scheduleJob");
            ScheduleJob scheduleJob= JSON.parseObject(json,ScheduleJob.class);
            String beanName=scheduleJob.getBeanName();
            String methodName=scheduleJob.getMethodName();
            String params=scheduleJob.getParams();
            ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
            Object object=applicationContext.getBean(beanName);
            Method method=null;
            if (params==null||params.equals("")){
                method=object.getClass().getDeclaredMethod(methodName);
                method.invoke(object);
            }else {
                method=object.getClass().getDeclaredMethod(methodName,String.class);
                method.invoke(object,params);
                scheduleJobLog.setParams(params);
            }
            long end=System.currentTimeMillis();
            int s=(int)(end-start);
            //System.out.println(scheduleJob.toString());
            scheduleJobLog.setBeanName(beanName);
            scheduleJobLog.setMethodName(methodName);

            scheduleJobLog.setCreateTime(new Date());
            scheduleJobLog.setTimes(s);
            scheduleJobLog.setJobId(scheduleJob.getJobId());
            scheduleJobLog.setStatus(scheduleJob.getStatus());
            ScheduleLogService scheduleLogService= (ScheduleLogService) SpringContextUtils.getBean("scheduleLogServiceImpl");
            scheduleLogService.insertScheduleLog(scheduleJobLog);
            System.out.println("日志保存成功");
        }catch (Exception e){
            scheduleJobLog.setError(e.getMessage());
            System.out.println("-------------");
            e.printStackTrace();
        }

}
}
