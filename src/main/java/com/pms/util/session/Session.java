package com.pms.util.session;

import java.util.Map;

/**
 * 这个接口是考虑到以后可能会换一种方式来保存用户的
 * 登陆状态，所以把Session的方法抽象出来，如果以后
 * 不用Session来保存状态了，换做服务器的内存来保存
 * 状态，那么把新的服务器的链接方式用来实现这个接口，
 * 这一工程可能还需要很久，接口到以后可能也需要做一些
 * 更改。大家加油把
 *
 * @since 2017-11-04 @author hyx
 */
public interface Session {

    /**
     * 传入一个Key值，然后去判断这个Key值是否对应了一个具体的Value
     * 如果存在则返回True，如果不存在就返回false
     * 在false的时候一定要注意处理空指针的异常
     * @param name
     *  要查询的Key值
     * @return
     *  true  key有一个value对象
     *  false key没有对应一个value对象
     */
    public boolean isExist(String name);

    /**
     * 传入一个key值然后取出这个key对应的value值
     * @param name
     * @param <T>
     *     value的类型参数
     * @return
     */
    public <T> T get(String name);

    /**
     * 放入一个Key值与Object对象到Session当中去
     * 一定不要忘记了类型，不然你取的时候不好取
     * @param attributeName
     *  放入的Key值
     * @param o
     *  放入的对象
     */
    public void put(String attributeName ,Object o);

}
