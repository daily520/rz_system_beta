package com.itqf.service;

import com.itqf.dto.TableData;
import com.itqf.entity.ScheduleJob;
import com.itqf.utils.R;

import java.util.List;

public interface ScheduleService {
    TableData getScheduleList(String search ,String order,int limit,int offset);
    R getInfo(long id);
    R insertJob(ScheduleJob scheduleJob);
    R updateJob(ScheduleJob scheduleJob);
    R deleteByIds(List<Long> ids);

    R resumeTask(List<Long> ids);

    R pause(List<Long> ids);
    R runOnce(List<Long> ids);
}
