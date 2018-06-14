package org.onete.framework.annotation;

import java.lang.annotation.*;

/**
 * 切面注解
 * @since 1.0.0
 *
 * Created by wangcheng  on 2018/6/14.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
    /*
       注解
     */
    Class<? extends Annotation> value();
}
