package com.itqf.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itqf.dto.TableData;
import com.itqf.entity.ScheduleJob;
import com.itqf.mapper.ScheduleJobMapper;
import com.itqf.service.ScheduleService;
import com.itqf.utils.R;
import com.itqf.utils.ScheduleUtils;
import org.quartz.Scheduler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Resource
    private ScheduleJobMapper scheduleJobMapper;

    @Resource
    private Scheduler scheduler;
    @Override
    public TableData getScheduleList(String search, String order, int limit, int offset) {
        PageHelper.offsetPage(offset,limit);
        List<ScheduleJob> list =scheduleJobMapper.getJobList(search,order);
        PageInfo pageInfo=new PageInfo(list);
        TableData tableData=new TableData();
        tableData.setRows(pageInfo.getList());
        tableData.setTotal(pageInfo.getTotal());
        return tableData;
    }

    @Override
    public R getInfo(long id) {
        ScheduleJob scheduleJob=scheduleJobMapper.selectByPrimaryKey(id);
        return R.ok().put("scheduleJob",scheduleJob);
    }

    @Override
    public R insertJob(ScheduleJob scheduleJob) {
        int i=scheduleJobMapper.insertSelective(scheduleJob);
        System.out.println("自增主键"+i);
        ScheduleUtils.createTask(scheduler,scheduleJob);
        return i>0?R.ok():R.error();
    }

    @Override
    public R updateJob(ScheduleJob scheduleJob) {
        int i=scheduleJobMapper.updateByPrimaryKeySelective(scheduleJob);
        ScheduleUtils.updateSchedule(scheduler,scheduleJob);
        return i>0?R.ok():R.error();
    }

    @Override
    public R deleteByIds(List<Long> ids) {
        int i=scheduleJobMapper.deleteByIds(ids);
        for (Long id: ids){
            ScheduleUtils.deleteSchedule(scheduler,id);
        }
        return i>0?R.ok():R.error();
    }

    @Override
    public R resumeTask(List<Long> ids) {
        int i=scheduleJobMapper.updateStatus(ids, (byte) 0);
        for (Long id: ids){
            ScheduleUtils.resume(scheduler,id);
        }
        return i>0?R.ok():R.error();
    }

    @Override
    public R pause(List<Long> ids) {
        int i=scheduleJobMapper.updateStatus(ids, (byte) 1);
        for (Long id: ids){
            ScheduleUtils.pause(scheduler,id);
        }
        return i>0?R.ok():R.error();
    }

    @Override
    public R runOnce(List<Long> ids) {

        for (Long id: ids){
            ScheduleUtils.runOnce(scheduler,id);
        }
        return R.ok();
    }
}
