package com.learn.chapter06.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 6.1.1 反射技术
 */
public class ReflectTests {
    public static void main(String[] args) throws ClassNotFoundException,
            NoSuchMethodException, InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {

        // 通过反射创建 ReflectService 对象
        Object service = Class.forName(UserService.class.getName()).newInstance();
        // 获取服务方法 —— sayHello
        Method method = service.getClass().getMethod("sayHello", String.class);
        // 反射调用方法
        method.invoke(service, "张三");
    }
}
/* Output:
hello,张三
*///:~
