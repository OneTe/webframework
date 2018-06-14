package org.onete.framework.proxy;

/**
 * 代理接口
 * @since 1.0.0
 *
 * Created by wangcheng  on 2018/6/14.
 */
public interface Proxy {
    /*
       执行链式代理
     */
    Object doProxy(ProxyChain proxyChain) throws Throwable;
}
