package com.itqf.utils;

import java.util.HashMap;

/**
 * @Description:
 * @Company: 千锋互联
 * @Author: 李丽婷
 * @Date: 2019/10/14
 * @Time: 上午11:38
 */
public class R   extends HashMap {

    private  int  code;//操作成功还是失败标识  0:成功  1：失败
    private  String msg;//操作结果

    public R(){}


    public R(int code){
        // this.code = code;
        super.put("code",code);
    }
    public R(int code,String msg){
        super.put("code",code);
        super.put("msg",msg);
    }

    public  static  R  ok(){
        return  new R(0);
    }
    public  static  R  ok(String msg){
        return  new R(0,msg);
    }

    public  static  R  error(){
        return  new R(1);
    }
    public  static  R  error(String msg){
        return  new R(1,msg);
    }

    public   R put(String k,Object v){
        super.put(k,v);
        return this;
    }


}
