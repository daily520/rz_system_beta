package com.itqf.service;

import com.itqf.dto.SysUserDTO;
import com.itqf.dto.TableData;
import com.itqf.entity.SysUser;
import com.itqf.utils.R;
import com.itqf.utils.ResponseMsg;

import java.util.List;

/**
 * @Description:
 * @Company: 千锋互联
 * @Author: 李丽婷
 * @Date: 2019/10/14
 * @Time: 上午11:48
 */
public interface SysUserService {

    R login(SysUserDTO sysUserDTO);
    TableData getUserList(String search,String order,int limit,int offset);
    R saveUser(SysUser sysUser);
    R getUser(long userId);
    R updateUser(SysUser sysUser);
    R deleteBatch(List<Long> ids);
    R findAll();
    }
