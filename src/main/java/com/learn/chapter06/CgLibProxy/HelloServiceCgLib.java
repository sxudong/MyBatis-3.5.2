package com.learn.chapter06.CgLibProxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 6.1.3 CGLIB动态代理 P133
 */
public class HelloServiceCgLib implements MethodInterceptor {

    private Object target;

    /**
     * 创建代理对象
     * @param target
     * @return
     */
    public Object getInstance(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        // 回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }

    /**
     * 回调方法
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.err.println("********** 我是 CGLIB 的动态代理 **********");
        Object result;
        // 反射方法前调用.
        System.err.println("我准备说hello.");
        // 执行方法,相当于调用HelloServiceImpl类的sayHello方法.
        result = methodProxy.invokeSuper(obj, objects);
        // 反射方法后调用.
        System.err.println("我说过hello了.");
        return result;
    }
}
