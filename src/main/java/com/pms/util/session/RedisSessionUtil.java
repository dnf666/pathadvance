package com.pms.util.session;

import org.springframework.stereotype.Component;

import java.util.Map;

//todo 在以后如果用到了，就来填坑
@Component
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
