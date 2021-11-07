package com.learn.chapter04.cache.config;

import org.apache.ibatis.cache.Cache;

import java.util.concurrent.locks.ReadWriteLock;

/**
 * 配置 MyBatis 自定义缓存
 */
public class MyCache implements Cache {
    @Override
    public String getId() { // 获取缓存编号
        return null;
    }

    @Override
    public void putObject(Object o, Object o1) { // 保存缓存对象

    }

    @Override
    public Object getObject(Object o) { // 通过key获取缓存对象
        return null;
    }

    @Override
    public Object removeObject(Object o) { // 通过key删除缓存对象
        return null;
    }

    @Override
    public void clear() { // 清空缓存

    }

    @Override
    public int getSize() { // 获取缓存对象大小
        return 0;
    }

    @Override
    public ReadWriteLock getReadWriteLock() { // 获取缓存的读写锁
        return null;
    }
}
