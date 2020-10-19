package com.zhang.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类，得到当前秒的时间字符串
 */
public class DateUtil {
    public static String getCurrentDateStr(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        return sdf.format(date);
    }
}
