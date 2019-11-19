package com.itqf.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itqf.dto.TableData;
import com.itqf.dto.UserRoleDTO;
import com.itqf.entity.SysRole;
import com.itqf.mapper.SysRoleMapper;
import com.itqf.service.SysRoleService;
import com.itqf.utils.R;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public TableData getRoleList(String search, String order, int limit, int offset) {
        PageHelper.offsetPage(offset,limit);
        List<SysRole> list = sysRoleMapper.getRoleList(search,order);
        PageInfo info = new PageInfo(list);

        TableData tableData = new TableData();
        tableData.setRows(info.getList());
        tableData.setTotal(info.getTotal());


        return tableData;
    }

    @Override
    public R saveRole(SysRole sysRole) {
        int i=sysRoleMapper.insertSelective(sysRole);
        return i>0?R.ok():R.error();
    }

    @Override
    public R getRole(long roleId) {

        return R.ok().put("role",sysRoleMapper.selectByPrimaryKey(roleId));
    }

    @Override
    public R updateRole(SysRole sysRole) {
        int i=sysRoleMapper.updateByPrimaryKeySelective(sysRole);
        return i>0?R.ok():R.error();
    }

    @Override
    public R deleteRoleBatch(List<Long> ids) {
        int i=sysRoleMapper.deleteRoleBatch(ids);
        return i>0?R.ok():R.error();
    }

    @Override
    public R getNotHasRoles(long userId) {
        List<SysRole> list=sysRoleMapper.notHasRoles(userId);
        return R.ok().put("roles",list);
    }

    @Override
    public R giveRole(UserRoleDTO userRoleDTO) {
        int i=sysRoleMapper.insertUserRole(userRoleDTO);
        return i>0?R.ok("操作成功"):R.error("操作失败");
    }
}
