package org.onete.framework.bean;

import java.lang.reflect.Method;

/**
 * 封装Action信息
 * @since 1.0.0
 *
 * Created by wangcheng  on 2018/6/12.
 */
public class Handler {
    /*
       Controller类
     */
    private Class<?> controllerClass;

    /*
       Action方法
     */
    private Method actionMethod;

    public Handler(Class<?> controllerClass,Method actionMethod){
        this.controllerClass = controllerClass;
        this.actionMethod = actionMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }
}
