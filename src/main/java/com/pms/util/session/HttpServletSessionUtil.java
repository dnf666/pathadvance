package com.pms.util.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 这个抽象的是Http当中大家常用的Session的保存方式
 * 可能在使用的时候，HttpServletRequest会没有办法成功的注入到request
 * 当中去，所以在使用的时候，我就在想有没有必要要验证一下request是不是空指针
 * 这个问题
 */
@Component("httpSession")
public class HttpServletSessionUtil implements Session{

    @Autowired(required = false)
    private HttpServletRequest request;

    public boolean isExist(String name) {
        if (request.getSession().getAttribute(name) == null){
            return false;
        }
        return true;
    }

    public <T> T get(String name) {
        return (T) request.getSession().getAttribute(name);
    }

    public void put(String attributeName,Object o) {
        request.getSession().setAttribute(attributeName,o);
    }
}
