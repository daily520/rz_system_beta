package com.itqf.web;

import com.google.code.kaptcha.Producer;
import com.itqf.config.KaptchaConfig;
import com.itqf.dto.SysUserDTO;
import com.itqf.dto.TableData;
import com.itqf.entity.SysUser;
import com.itqf.service.SysUserService;
import com.itqf.utils.R;
import com.itqf.utils.ResponseMsg;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.List;


@RestController   //@Controller  +  @ResponseBody
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    @Resource
    private Producer producer;

    @RequestMapping("sys/login")
    // {username: "aaaa", password: "aaaa", captcha: "aaaa"}
    //@RequestBody :把请求参数（json）--->java对象
    //@ResponseBody  :把返回值对象--->json对象
    public R login(@RequestBody SysUserDTO userDTO,HttpSession session){
//        Map map = new HashMap();
//
//        map.put("code",0);
//
//        return  map;
        if(userDTO.getCaptcha()==null||userDTO.getCaptcha()==""){
            return R.error("验证码不能为空");
        }
        String code= (String) session.getAttribute("code");
        if(!userDTO.getCaptcha().equalsIgnoreCase(code)){
            return R.error("验证码不正确");
        }
        R r=sysUserService.login(userDTO);
        SysUser sysUser= (SysUser) r.get("sysUser");
        session.setAttribute("sysUser",sysUser);
        return r;

    }

    @RequestMapping("captcha.jpg")
    public void createKaptcha(HttpServletResponse response, HttpSession session){
        try{
            String code=producer.createText();
            session.setAttribute("code",code);
            BufferedImage image=producer.createImage(code);
            ImageIO.write(image,"jpg",response.getOutputStream());
        }catch (Exception e){

        }
    }

    @RequestMapping("/sys/user/list")
    public TableData getUserList(String search, String order, int limit, int offset){
        return sysUserService.getUserList(search,order,limit,offset);
    }

    @RequestMapping("/sys/user/save")
    public R saveUser(@RequestBody SysUser sysUser){
        sysUser.setCreateTime(new Date());
        return sysUserService.saveUser(sysUser);
    }

    @RequestMapping("/sys/user/info/{userId}")
    public  R userInfo(@PathVariable long userId){
        return  sysUserService.getUser(userId);
    }

    @RequestMapping("/sys/user/upadate")
    public R updateUser(@RequestBody SysUser sysUser){
        return sysUserService.updateUser(sysUser);
    }

    @RequestMapping("/sys/user/del")
    public R deleteUser(@RequestBody List<Long> ids){
        return sysUserService.deleteBatch(ids);
    }

    @RequestMapping("/sys/user/logout")
    public  R logout(HttpSession session){
        session.invalidate();//session失效

        return R.ok();
    }
    @RequestMapping("/sys/user/info")
    public  R info(HttpSession session){
        return R.ok().put("user",(SysUser)session.getAttribute("sysUser"));
    }

    @RequestMapping("/sys/user/userList")
    public R allUsers(){
        return sysUserService.findAll();
    }
}
