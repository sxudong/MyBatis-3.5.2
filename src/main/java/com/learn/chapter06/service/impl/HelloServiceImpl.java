package com.learn.chapter06.service.impl;

import com.learn.chapter06.service.HelloService;

/**
 * 6-1-2 JDK 动态代理
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public void sayHello(String name) {
        System.err.println("hello," + name);
    }
}
