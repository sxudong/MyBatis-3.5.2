package com.learn.chapter06.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 6.1.2 JDK 动态代理
 * 实现JVM接口 p130
 */
public class HelloServiceProxy implements InvocationHandler {

    /**
     * 真实服务对象
     */
    private Object target;

    /**
     * 绑定委托对象并返回一个代理类.
     */
    Object bind(Object target) {
        this.target = target;
        // 取得代理对象.
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), // 类加载器
                target.getClass().getInterfaces(), // 真实服务对象接口，jdk代理需要提供接口
                this  // 代表当前 HelloServiceProxy类
        );
    }

    /**
     * 通过代理对象调用方法 首先进入这个方法
     * @param proxy -- 代理对象
     * @param method -- 被调用方法
     * @param args -- 方法的参数
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.err.println("********** 我是 JDK 动态代理 **********");
        Object result;
        // 反射方法前调用.
        System.err.println("我准备说hello.");
        // 执行方法，相当于调用 HelloServiceImpl类 的 sayHello 方法.
        result = method.invoke(target, args);
        // 反射方法后调用.
        System.err.println("我说过hello了.");
        return result;
    }
}
