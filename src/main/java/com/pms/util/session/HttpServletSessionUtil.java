package com.pms.util.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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
