package org.onete.framework.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * 封装请求信息
 * @since 1.0.0
 *
 * Created by wangcheng  on 2018/6/11.
 */
public class Request {
    /*
       请求方法
     */
    private String requestMethod;
    /*
       请求路径
     */
    private String requestPath;

    public Request(String requestMethod,String requestPath){
        this.requestMethod = requestMethod;
        this.requestPath = requestPath;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public String getRequestPath() {
        return requestPath;
    }

    /*
       如果两个对象相等当且仅当每个属性值都相等
     */
    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this,obj);
    }
    /*
       如果hashCode取决于该class的所有filed时需要使用反射机制来产生一个hashCode
     */
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
