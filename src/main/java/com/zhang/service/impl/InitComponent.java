package com.zhang.service.impl;

import com.zhang.pojo.Blog;
import com.zhang.pojo.BlogType;
import com.zhang.pojo.Blogger;
import com.zhang.pojo.Link;
import com.zhang.service.BlogService;
import com.zhang.service.BlogTypeService;
import com.zhang.service.BloggerService;
import com.zhang.service.LinkService;
import com.zhang.util.Const;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

@Component
public class InitComponent implements ServletContextListener, ApplicationContextAware{

    private static ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext application = servletContextEvent.getServletContext();
        // 博客类别
        BlogTypeService blogTypeService = (BlogTypeService) applicationContext.getBean("blogTypeService");
        List<BlogType> blogTypeList = blogTypeService.countList();
        application.setAttribute(Const.BLOG_TYPE_COUNT_LIST, blogTypeList);

        // 博主信息
        BloggerService bloggerService = (BloggerService) applicationContext.getBean("bloggerService");
        Blogger blogger = bloggerService.findBlogger();
        blogger.setPassword(null);
        application.setAttribute(Const.BLOGGER, blogger);

        // 按年月分类的博客数量
        BlogService blogService = (BlogService) applicationContext.getBean("blogService");
        List<Blog> blogCountList = blogService.countList();
        application.setAttribute(Const.BLOG_COUNT_LIST, blogCountList);

        // 友情链接
        LinkService linkService = (LinkService) applicationContext.getBean("linkService");
        List<Link> linkList = linkService.listLink(null);
        application.setAttribute(Const.LINK_LIST, linkList);
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
