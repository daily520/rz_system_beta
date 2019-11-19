package com.itqf.dto;

import com.itqf.entity.SysUser;
import lombok.Data;

/**
 * @Description:
 * @Company: 千锋互联
 * @Author: 李丽婷
 * @Date: 2019/10/14
 * @Time: 上午11:28
 */
//@Getter
//@Setter
//@ToString
@Data
public class SysUserDTO  extends SysUser {

    private String  captcha;
    private  boolean rememberMe;



}
