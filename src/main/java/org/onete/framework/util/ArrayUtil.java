package org.onete.framework.util;

import org.apache.commons.lang3.ArrayUtils;

/**
 * 数据工具类
 * @since 1.0.0
 *
 * Created by wangcheng  on 2018/6/11.
 */
public final class ArrayUtil {
    /*
       判断数组是否非空
     */
    public static boolean isNotEmpty(Object[] array){
        return !ArrayUtils.isEmpty(array);
    }

    /*
       判断数组是否为空
     */
    public static boolean isEmpty(Object[] array){
        return ArrayUtils.isEmpty(array);
    }
}
