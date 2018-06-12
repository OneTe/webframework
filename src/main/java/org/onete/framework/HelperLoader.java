package org.onete.framework;

import org.onete.framework.helper.BeanHelper;
import org.onete.framework.helper.ClassHelper;
import org.onete.framework.helper.ControllerHelper;
import org.onete.framework.helper.IocHelper;
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
                IocHelper.class,
                ControllerHelper.class
        };
        for(Class<?> cls : classList){
            ClassUtil.loadClass(cls.getName(),false);
        }
    }
}
