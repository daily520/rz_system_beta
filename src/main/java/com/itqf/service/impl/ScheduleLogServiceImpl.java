package com.itqf.service.impl;

import com.itqf.entity.ScheduleJob;
import com.itqf.entity.ScheduleJobLog;
import com.itqf.mapper.ScheduleJobLogMapper;
import com.itqf.service.ScheduleLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("scheduleLogServiceImpl")
public class ScheduleLogServiceImpl implements ScheduleLogService {

    @Resource
    private ScheduleJobLogMapper scheduleJobLogMapper;
    @Override
    public void insertScheduleLog(ScheduleJobLog scheduleJobLog) {
        scheduleJobLogMapper.insertSelective(scheduleJobLog);
    }
}
