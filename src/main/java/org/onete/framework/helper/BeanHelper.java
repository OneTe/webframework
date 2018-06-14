package org.onete.framework.helper;

import org.onete.framework.util.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Bean助手类
 * @since 1.0.0
 *
 * Created by wangcheng  on 2018/6/11.
 */
public final class BeanHelper {
    /*
       定义Bean映射，用于存放Bean类与Bean实例的映射关系
     */
    private static final Map<Class<?>,Object> BEAN_MAP = new HashMap<Class<?>,Object>();

    static {
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
        for(Class<?> beanClass : beanClassSet){
            Object obj = ReflectionUtil.newInstance(beanClass);
            BEAN_MAP.put(beanClass,obj);
        }
    }

    /*
       获取Bean映射
     */
    public static Map<Class<?>,Object> getBeanMap(){
        return BEAN_MAP;
    }
    /*
       获取Bean实例
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<?> cls){
        if(!BEAN_MAP.containsKey(cls)){
            throw new RuntimeException("can't not get bean by class: " + cls);
        }
        return (T) BEAN_MAP.get(cls);
    }

    /*
       设置Bean实例

       在AopHelper中需要获取所有目标类及其被拦截的切面类实例，并通过ProxyManager#createProxy方法来创建代理对象
       最后将其放入到Bean Map中
     */
    public static void setBean(Class<?> cls,Object obj){
        BEAN_MAP.put(cls,obj);
    }
}
