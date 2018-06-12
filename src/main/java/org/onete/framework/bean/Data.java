package org.onete.framework.bean;

/**
 * 返回数据对象
 * @since 1.0.0
 *
 * Created by wangcheng  on 2018/6/12.
 */
public class Data {
    /*
       模型数据
     */
    private Object model;
    public Data(Object model){
        this.model = model;
    }

    public Object getModel() {
        return model;
    }
}
