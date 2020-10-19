package com.zhang.controller.admin;

import com.zhang.pojo.Blog;
import com.zhang.pojo.PageBean;
import com.zhang.service.BlogService;
import com.zhang.util.DateJsonValueProcessor;
import com.zhang.util.ResponseUtil;
import com.zhang.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsDateJsonValueProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/blog")
public class BlogAdminController {

    @Resource
    private BlogService blogService;

    /**
     *  保存一条博客信息
     */
    @RequestMapping("/save")
    public String save(Blog blog, HttpServletResponse response) throws IOException {
        int resultTotal = 0;
        if(blog.getId() == null){
            resultTotal = blogService.addBlog(blog);
        }else {
            resultTotal = blogService.updateBlog(blog);
        }
        JSONObject result = new JSONObject();
        if(resultTotal > 0){
            result.put("success", Boolean.valueOf(true));
        }else{
            result.put("success", Boolean.valueOf(false));
        }
        ResponseUtil.write(response, result);
        return null;
    }

    // 查询博客信息列表
    @RequestMapping("/listBlog")
    public String list(@RequestParam(value = "page",required = false) String page,
                       @RequestParam(value = "rows",required = false) String rows,
                       HttpServletResponse response, Blog blog) throws IOException {
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start",pageBean.getStart());
        map.put("size",pageBean.getPageSize());
        map.put("title", StringUtil.formatLike(blog.getTitle()));
        // 分页查询博客信息列表
        List<Blog> list = blogService.list(map);
        // 获取总共有多少条博客信息
        Long total = blogService.getTotal(map);

        // 封装到json
        JSONObject result = new JSONObject();
        JsonConfig config = new JsonConfig();
        config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
        JSONArray jsonArray = JSONArray.fromObject(list,config);
        result.put("rows", jsonArray);
        result.put("total", total);
        ResponseUtil.write(response, result);
        return null;
    }

    //根据主键查询博客信息
    @RequestMapping("/findById")
    public String findById(@RequestParam("id")String id, HttpServletResponse response) throws IOException {
        Blog blog = blogService.findById(Integer.parseInt(id));
        JSONObject jsonObject = JSONObject.fromObject(blog);
        ResponseUtil.write(response,jsonObject);
        return null;
    }

    //删除博客
    @RequestMapping("/delete")
    public String delete(@RequestParam("ids")String ids, HttpServletResponse response) throws IOException {
        String[] idsStr = ids.split(",");
        for (int i = 0; i < idsStr.length; i++) {
            blogService.deleteBlog(Integer.parseInt(idsStr[i]));
        }
        JSONObject result = new JSONObject();
        result.put("success", Boolean.valueOf(true));
        ResponseUtil.write(response, result);
        return null;
    }
}
