package com.itqf.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itqf.dto.SysMenuDTO;
import com.itqf.dto.SysUserDTO;
import com.itqf.dto.TableData;
import com.itqf.entity.SysMenu;
import com.itqf.entity.SysUser;
import com.itqf.mapper.SysUserMapper;
import com.itqf.service.SysUserService;
import com.itqf.utils.R;
import com.itqf.utils.ResponseMsg;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Company: 千锋互联
 * @Author: 李丽婷
 * @Date: 2019/10/14
 * @Time: 上午11:49
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public R login(SysUserDTO sysUserDTO) {

        SysUser sysUser = sysUserMapper.findUserByName(sysUserDTO.getUsername());
        if(sysUser==null){
            return  R.error("账号不存在！");
        }
        if (!sysUser.getPassword().equals(sysUserDTO.getPassword())){
            return  R.error("密码错误！");
        }

        return R.ok().put("sysUser",sysUser);
    }

    @Override
    public TableData getUserList(String search, String order, int limit, int offset) {
        PageHelper.offsetPage(offset,limit);
        List<SysUser> list = sysUserMapper.getUserList(search,order);
        PageInfo info = new PageInfo(list);

        TableData tableData = new TableData();
        tableData.setRows(info.getList());
        tableData.setTotal(info.getTotal());


        return tableData;
    }

    @Override
    public R saveUser(SysUser sysUser) {
        int i=sysUserMapper.insertSelective(sysUser);
        return i>0?R.ok():R.error();
    }

    @Override
    public R getUser(long userId) {
        return R.ok().put("user",sysUserMapper.selectByPrimaryKey(userId));
    }

    @Override
    public R updateUser(SysUser sysUser) {
        int i=sysUserMapper.updateByPrimaryKeySelective(sysUser);
        return i>0?R.ok():R.error("修改成功");
    }

    @Override
    public R deleteBatch(List<Long> ids) {
        int i=sysUserMapper.deleteBatch(ids);
        return i>0?R.ok():R.error("删除失败");
    }

    @Override
    public R findAll() {
        List<SysUser> list = sysUserMapper.getUserList(null,"asc");
        return R.ok().put("userList",list);
    }


}
