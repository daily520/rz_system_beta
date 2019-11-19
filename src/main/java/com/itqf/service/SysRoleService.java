package com.itqf.service;

import com.itqf.dto.SysUserDTO;
import com.itqf.dto.TableData;
import com.itqf.dto.UserRoleDTO;
import com.itqf.entity.SysRole;
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
public interface SysRoleService {


    TableData getRoleList(String search, String order, int limit, int offset);
    R saveRole(SysRole sysRole);
    R getRole(long roleId);
    R updateRole(SysRole sysRole);
    R deleteRoleBatch(List<Long> ids);
    R getNotHasRoles(long userId);
    R giveRole(UserRoleDTO userRoleDTO);
}
