package com.zhang.controller;

import com.zhang.pojo.Blog;
import com.zhang.pojo.PageBean;
import com.zhang.service.BlogService;
import com.zhang.util.PageUtil;
import com.zhang.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  首页控制
 */
@Controller
public class IndexController {

    @Resource
    private BlogService blogService;

    @RequestMapping("/index")
    public ModelAndView index(@RequestParam(value = "page", required = false)String page,
                              @RequestParam(value = "type_id", required = false)String type_id,
                              @RequestParam(value = "releaseDateStr", required = false)String releaseDateStr,
                              HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title","个人博客系统");
        if(StringUtil.isEmpty(page)){
            page = "1";
        }
        PageBean pageBean = new PageBean(Integer.parseInt(page), 10);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", pageBean.getStart());
        map.put(("size"), pageBean.getPageSize());
        map.put("type_id", type_id);
        map.put("releaseDateStr", releaseDateStr);
        List<Blog> list = blogService.list(map);

        StringBuffer param = new StringBuffer();
        if(StringUtil.isNotEmpty(type_id)){
            param.append("type_id="+type_id+"&");
        }

        if(StringUtil.isNotEmpty(releaseDateStr)){
            param.append("releaseDateStr="+releaseDateStr+"&");
        }
        modelAndView.addObject("mainPage","foreground/blog/list.jsp");
        String pageCode = PageUtil.genPagination(request.getContextPath()+"/index.html", blogService.getTotal(map), Integer.parseInt(page),10,param.toString());
        modelAndView.addObject("pageCode", pageCode);
        modelAndView.addObject("blogList",list);
        return modelAndView;
    }
}
