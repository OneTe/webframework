package org.onete.framework.bean;

import org.onete.framework.util.CastUtil;
import org.onete.framework.util.CollectionUtil;

import java.util.Map;

/**
 * 请求参数对象
 * @since 1.0.0
 *
 * Created by wangcheng  on 2018/6/12.
 */
public class Param {
    private Map<String,Object> paramMap;
    public Param(Map<String,Object> paramMap){
        this.paramMap = paramMap;
    }
    /*
       根据参数名获取long型参数值
     */
    public long getLong(String name){
        return CastUtil.castLong(paramMap.get(name));
    }
    /*
       获取所有字段信息
     */
    public Map<String,Object> getMap(){
        return paramMap;
    }

    /*
       验证参数是否为空
     */
    public boolean isEmpty(){
        return CollectionUtil.isEmpty(paramMap);
    }
}
