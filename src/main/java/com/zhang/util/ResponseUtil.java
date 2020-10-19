package com.zhang.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;



/**
 * 数据写入response的工具类
 */
public class ResponseUtil {
    public static void write(HttpServletResponse response, Object o) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print(o.toString());
        writer.flush();
        writer.close();
    }
}
