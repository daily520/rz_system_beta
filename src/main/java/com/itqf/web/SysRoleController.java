package com.itqf.web;

import com.google.code.kaptcha.Producer;
import com.itqf.dto.SysUserDTO;
import com.itqf.dto.TableData;
import com.itqf.dto.UserRoleDTO;
import com.itqf.entity.SysRole;
import com.itqf.entity.SysUser;
import com.itqf.service.SysRoleService;
import com.itqf.service.SysUserService;
import com.itqf.utils.R;
import com.itqf.utils.ResponseMsg;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.List;


@RestController   //@Controller  +  @ResponseBody
public class SysRoleController {

    @Resource
    private SysRoleService sysRoleService;


    @RequestMapping("/sys/role/list")
    public TableData getRoleList(String search, String order, int limit, int offset){
        return sysRoleService.getRoleList(search,order,limit,offset);
    }

    @RequestMapping("/sys/role/save")
    public R saveRole(@RequestBody SysRole sysRole,HttpSession session){
        SysUser sysUser= (SysUser) session.getAttribute("sysUser");
        sysRole.setCreateTime(new Date());
        sysRole.setCreateUserId(sysUser.getUserId());
        return sysRoleService.saveRole(sysRole);

    }

    @RequestMapping("/sys/role/info/{roleId}")
    public  R roleInfo(@PathVariable long roleId){
        return sysRoleService.getRole(roleId);

    }

    @RequestMapping("/sys/role/update")
    public R updateRole(@RequestBody SysRole sysRole){
        return sysRoleService.updateRole(sysRole);
    }

    @RequestMapping("/sys/role/del")
    public R deleteRole(@RequestBody List<Long> ids){
        return sysRoleService.deleteRoleBatch(ids);
    }

    @RequestMapping("/sys/user/notHasRole/{userId}")
    public R roles(@PathVariable long userId){
        return sysRoleService.getNotHasRoles(userId);
    }

    @RequestMapping("/sys/role/giveRole")
    public R giveUserRole(UserRoleDTO userRoleDTO){
        return sysRoleService.giveRole(userRoleDTO);
    }

}
