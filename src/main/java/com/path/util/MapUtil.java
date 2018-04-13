package com.path.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 这个类是 Map 的工具类，在 Controller 当中返回的 Json 数据格式按照如下
 * status 表示执行方法的状态
 *  1 表示成功
 *  0 表示失败
 *  -1 表示未知错误
 * message 表示错误的信息
 * object 如果前台需要，返回给前台的类
 */
public class MapUtil {

    /**
     * 这是一个静态方法，抽象了生成Map的过程提高了代码的利用率
     * @param status
     * @param message
     * @param object
     *  object 可是是一个 List 也可以是单个类，看业务需要
     * @return
     */
    public static Map toMap(Object status,String message,Object object){
        Map map = new HashMap();
        map.put("resultCode",status);
        map.put("message",message);
        map.put("data",object);
        return map;
    }

}
