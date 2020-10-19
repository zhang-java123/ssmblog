package com.zhang.service.impl;

import com.zhang.dao.BloggerDao;
import com.zhang.pojo.Blogger;
import com.zhang.service.BloggerService;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("bloggerService")
public class BloggerServiceImpl implements BloggerService {
    @Resource
    private BloggerDao bloggerDao;

    public Blogger getByUserName(String userName) {
        return bloggerDao.getByUserName(userName);
    }

    public Integer updateBlogger(Blogger blogger) {
        SecurityUtils.getSubject().getSession().setAttribute("currentUser", blogger);
        return bloggerDao.updateBlogger(blogger);
    }

    public Blogger findBlogger() {
        return bloggerDao.findBlogger();
    }
}
