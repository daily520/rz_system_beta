package com.itqf.mapper;

import com.itqf.entity.ScheduleJob;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScheduleJobMapper {
    int deleteByPrimaryKey(Long jobId);

    int insert(ScheduleJob record);

    int insertSelective(ScheduleJob record);

    ScheduleJob selectByPrimaryKey(Long jobId);

    int updateByPrimaryKeySelective(ScheduleJob record);

    int updateByPrimaryKey(ScheduleJob record);

    List<ScheduleJob> getJobList(@Param("search") String search,@Param("order") String order);

    int deleteByIds(List<Long> ids);

    int updateStatus(@Param("ids") List<Long> ids,@Param("status")byte status );
}