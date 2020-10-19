package com.zhang.util;
//操作字符串工具类
public class StringUtil {
    /**
     * 字符串前后加%
     * @param str
     * @return
     */
    public static String formatLike(String str){
        if(isNotEmpty(str)){
            return "%"+str+"%";
        }
        return null;
    }

    // 判断是否不为空
    public static boolean isNotEmpty(String str){
        if(str != null && !"".equals(str.trim())){
            return true;
        }
        return false;
    }

    public static boolean isEmpty(String str){
        if(str == null || "".equals(str.trim())){
            return true;
        }
        return false;
    }
}
