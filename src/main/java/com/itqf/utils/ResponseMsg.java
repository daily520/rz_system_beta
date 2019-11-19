package com.itqf.utils;

import lombok.Data;


public class ResponseMsg {
    private int code;
    private String msg;

    public ResponseMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public ResponseMsg(int code) {
        this.code = code;
    }
    public ResponseMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public static ResponseMsg ok(){
        return new ResponseMsg(0);
    }
    public static ResponseMsg error(){
        return new ResponseMsg(1);
    }
    public static ResponseMsg ok(String msg){
        return new ResponseMsg(0,msg);
    }
    public static ResponseMsg error(String msg){
        return new ResponseMsg(1,msg);
    }
}
