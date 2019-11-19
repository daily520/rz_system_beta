package com.itqf.service;

import com.itqf.dto.TableData;
import com.itqf.entity.SysMenu;
import com.itqf.utils.R;

import java.util.List;

public interface SysMenuService {
    public TableData findMenuByPage(String search, String order, int limit, int offset);
    public List<SysMenu> selectMenuTree();
    public R saveMenu(SysMenu sysMenu);
    public R getMenuInfo(long menu_id);
    R updateById(SysMenu sysMenu);
    R del(List<Long> ids);
    R findUserMenu(long userId);
}
