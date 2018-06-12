package org.onete.framework.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 在org.apache.commons.lang3.StringUtils上做简单的封装
 *
 * Created by wangcheng  on 2018/6/8.
 */
/*
    字符串工具类
 */
public final class StringUtil {
    /*
      判断字符串是否为空
     */
    public static boolean isEmpty(String str){
        if(str != null){
            str = str.trim();
        }
        return StringUtils.isEmpty(str);
    }
    /*
      判断字符串是否非空
     */
    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }

    /*
       分割字符串
     */
    public static String[] splitString(String str,String pattern){
        return StringUtils.split(str,pattern);
    }

    public static void main(String[] args){
        String pa = "wang=123";
        String p = "=";
        String[] rs = splitString(pa,p);
        for(String s : rs){
            System.out.print(s + " ");
        }
    }
}
