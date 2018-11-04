package com.lyf.dubbo.service.service;

import com.lyf.dubbo.service.DemoService;

public class DemoServiceImpl implements DemoService {
 
    public String sayHello(String name) {
        System.out.println("服务接收: "+name);
        return "Hello " + name;
    }
 
}