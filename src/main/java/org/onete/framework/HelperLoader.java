package org.onete.framework;

import org.onete.framework.helper.*;
import org.onete.framework.util.ClassUtil;

/**
 * 加载相应的Helper类
 * @since 1.0.0
 *
 * Created by wangcheng  on 2018/6/12.
 */
public final class HelperLoader {
    public static void init(){
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                //AopHelp要在IocHelp之前加载，因为首先要通过AopHelper获取代理对象，然后才能通过IocHelper进行依赖注入
                AopHelper.class,
                IocHelper.class,
                ControllerHelper.class,
        };
        for(Class<?> cls : classList){
            ClassUtil.loadClass(cls.getName(),false);
        }
    }
}
