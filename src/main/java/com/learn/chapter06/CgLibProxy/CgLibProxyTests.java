package com.learn.chapter06.CgLibProxy;

import com.learn.chapter06.service.HelloService;
import com.learn.chapter06.service.impl.HelloServiceImpl;

/**
 * CgLib 动态代理测试
 */
public class CgLibProxyTests {
    public static void main(String[] args) {
        HelloServiceCgLib cglib = new HelloServiceCgLib();

        HelloService instance = (HelloService) cglib.getInstance(new HelloServiceImpl());
        instance.sayHello("CgLib");
    }
}
/* Output:
********** 我是CGLIB 的动态代理 **********
我准备说hello.
hello,CgLib
我说过hello了.
*///:~
