package com.lyf;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Provider {
 
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        context.start();
        System.out.println("provider start ...");
        System.in.read(); // 按任意键退出
    }
 
}