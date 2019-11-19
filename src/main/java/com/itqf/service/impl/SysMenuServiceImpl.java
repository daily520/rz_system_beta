package com.itqf.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itqf.dto.SysMenuDTO;
import com.itqf.dto.TableData;
import com.itqf.entity.SysMenu;
import com.itqf.mapper.SysMenuMapper;
import com.itqf.service.SysMenuService;
import com.itqf.utils.R;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Resource
    private SysMenuMapper sysMenuMapper;
    @Override
    public TableData findMenuByPage(String search, String order, int limit, int offset) {
        PageHelper.offsetPage(offset,limit);
        List<SysMenuDTO> list =  sysMenuMapper.findMenu(search,order);
        PageInfo pageInfo = new PageInfo(list);

        long total = pageInfo.getTotal();
        List<SysMenu> menuList = pageInfo.getList();

        TableData data  =  new TableData();

        data.setTotal(total);
        data.setRows(menuList);

        return data;
    }

    @Override
    public List<SysMenu> selectMenuTree() {
        List<SysMenu> list=sysMenuMapper.findTreeMenu();


        SysMenu sysMenu=new SysMenu();
        sysMenu.setMenuId(0l);
        sysMenu.setParentId(-1l);
        sysMenu.setName("一级目录");
        sysMenu.setType(0);

        list.add(sysMenu);

        return list;
    }

    @Override
    public R saveMenu(SysMenu sysMenu) {
        int i=sysMenuMapper.insertSelective(sysMenu);
        return i>0?R.ok():R.error("新增失败");
    }

    @Override
    public R getMenuInfo(long menu_id) {
        SysMenu sysMenu=sysMenuMapper.selectByPrimaryKey(menu_id);
        R r=new R();
        r.put("menu",sysMenu);
        return r;

    }

    @Override
    public R updateById(SysMenu sysMenu) {
        int i=sysMenuMapper.updateByPrimaryKeySelective(sysMenu);
        return i>0?R.ok():R.error("修改失败");
    }

    @Override
    public R del(List<Long> ids) {
        for (Long id:ids) {
            if (id<=29){
                return R.error("系统内置菜单不能删除");
            }
        }
        int i=sysMenuMapper.del(ids);
        return i>0?R.ok():R.error("删除失败");
    }

    @Override
    public R findUserMenu(long userId) {
        List<SysMenuDTO> dir=sysMenuMapper.findDirByUserId(userId);

        for (SysMenuDTO menuDTO:dir) {
            List<SysMenuDTO> list=sysMenuMapper.findMenuByUserIdAndParentId(userId,menuDTO.getMenuId());
            menuDTO.setList(list);
        }
        return R.ok().put("menuList",dir).put("permissons","[]");
    }
}
