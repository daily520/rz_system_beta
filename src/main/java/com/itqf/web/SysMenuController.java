package com.itqf.web;

import com.itqf.dto.TableData;
import com.itqf.entity.SysMenu;
import com.itqf.entity.SysUser;
import com.itqf.service.SysMenuService;
import com.itqf.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class SysMenuController {
    @Autowired
    private SysMenuService sysMenuService;

    @RequestMapping("/sys/menu/list")
    public TableData findAllList(String search,String order,int limit,int offset){
        return sysMenuService.findMenuByPage(search,order,limit,offset);
    }

    @RequestMapping("/sys/menu/select")
    public R selectTreeMenu(){
        List<SysMenu> list=sysMenuService.selectMenuTree();
        return R.ok().put("menuList",list);
    }

    @RequestMapping("/sys/menu/save")
    public R saveMenu(@RequestBody SysMenu sysMenu){
        return sysMenuService.saveMenu(sysMenu);
    }

    //menu
    @RequestMapping("/sys/menu/info/{menuId}")
    public R getMenuInfo(@PathVariable long menuId){
        return sysMenuService.getMenuInfo(menuId);
    }

    @RequestMapping("/sys/menu/update")
    public R updateById(@RequestBody SysMenu sysMenu){
        return sysMenuService.updateById(sysMenu);
    }

    @RequestMapping("/sys/menu/del")
    public R del(@RequestBody List<Long> ids){
        return sysMenuService.del(ids);
    }

    @RequestMapping("/sys/menu/user")
    public R menuList(HttpSession session){
        SysUser sysUser= (SysUser) session.getAttribute("sysUser");
        return sysMenuService.findUserMenu(sysUser.getUserId());
    }
}
