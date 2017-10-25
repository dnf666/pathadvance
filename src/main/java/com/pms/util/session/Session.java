package com.pms.util.session;

import java.util.Map;

public interface Session {

    public boolean isExist(String name);

    public <T> T get(String name);

    public void put(String attributeName ,Object o);

}
