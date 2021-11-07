package com.learn.chapter06.jdkProxy;


import com.learn.chapter06.service.HelloService;
import com.learn.chapter06.service.impl.HelloServiceImpl;

/**
 * 6-1-2 JDK动态代理
 */
public class jdkProxyTests {
    public static void main(String[] args) {
        HelloServiceProxy helloServiceProxy = new HelloServiceProxy();
        HelloService proxy = (HelloService) helloServiceProxy.bind(new HelloServiceImpl());
        proxy.sayHello("张三!");
    }
}
/* Output:
********** 我是JDK动态代理 **********
我准备说hello.
hello,张三!
我说过hello了.
*///:~
