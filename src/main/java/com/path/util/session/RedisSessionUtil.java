package com.path.util.session;

import org.springframework.stereotype.Component;

import java.util.Map;

//todo 在以后如果用到了，就来填坑

/**
 * 这个类是第二种场景,如果以后要用到Redis做服务器的缓存
 * 来保存用户的登陆状态的话，那么可能这个类就需要根据你们的
 * 需求来具体的设计了，但是一定要做好以下的一个工作
 * 1.全局Key的一个生成策略
 * 2.内存空间的一个划分
 */
@Component("redisSession")
public class RedisSessionUtil implements Session{
    public boolean isExist(String name) {
        return false;
    }

    public <T> T get(String name) {
        return null;
    }

    public void put(String attributeName, Object o) {

    }

}
