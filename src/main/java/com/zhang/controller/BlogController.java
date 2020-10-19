package com.zhang.controller;

import com.zhang.pojo.Blog;
import com.zhang.service.BlogService;
import com.zhang.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/blog")
public class BlogController {
    @Resource
    private BlogService blogService;

    @Resource
    private CommentService commentService;

    @RequestMapping("/articles/{id}")
    public ModelAndView details(@PathVariable("id") Integer id, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        Blog blog = blogService.findById(id);
        modelAndView.addObject("blog", blog);

        // 阅读人数加1
        blog.setClickHit(blog.getClickHit() + 1);
        blogService.updateBlog(blog);

        modelAndView.addObject("mainPage","foreground/blog/view.jsp");
        modelAndView.addObject("pageTitle", blog.getTitle()+"_个人博客系统");

        //上下博客
        modelAndView.addObject("pageCode",genUpAndDownPageCode(blogService.getLastBlog(id), blogService.getNextBlog(id), request.getServletContext().getContextPath()));

        // 评论
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("blog_id",blog.getId());
        map.put("state",1);
        modelAndView.addObject("commentList",commentService.listComment(map));

        modelAndView.setViewName("index");
        return modelAndView;
    }

    // 博客上下篇
    private String genUpAndDownPageCode(Blog lastBlog, Blog nextBlog, String projectContext){
        StringBuffer pageCode = new StringBuffer();
        if(lastBlog == null || lastBlog.getId() == null){
            pageCode.append("<p>上一篇：没有了</p>");
        }else{
            pageCode.append("<p>上一篇：<a href='"+projectContext+"/blog/articles/"+lastBlog.getId()+".html'>"
                    +lastBlog.getTitle()+"</a></p>");
        }

        if(nextBlog == null || nextBlog.getId() == null){
            pageCode.append("<p>下一篇：没有了</p>");
        }else{
            pageCode.append("<p>下一篇：<a href='"+projectContext+"/blog/articles/"+nextBlog.getId()+".html'>"
                    +nextBlog.getTitle()+"</a></p>");
        }
        return pageCode.toString();

    }
}
