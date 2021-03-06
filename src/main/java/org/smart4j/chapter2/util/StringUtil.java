package org.smart4j.chapter2.util;


import org.apache.commons.lang3.StringUtils;

//字符串工具类
public final class StringUtil {
    //判断字符串是否为空
    public static boolean isEmpty(String string) {
        if (string != null) {
            string = string.trim();
        }
        return StringUtils.isEmpty(string);
    }

    //判断字符串是否为非空
    public static boolean isNotEmpty(String string) {
return !isEmpty(string);
    }
}
