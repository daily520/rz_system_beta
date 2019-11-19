package com.itqf.task;

import org.springframework.stereotype.Component;

@Component("testTask")
public class TestTask {
    public void test(){
        System.out.println(System.currentTimeMillis()+"测试测试");
    }
}
