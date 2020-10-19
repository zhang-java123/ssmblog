package com.zhang.service.impl;

import com.zhang.dao.BlogDao;
import com.zhang.dao.CommentDao;
import com.zhang.pojo.Blog;
import com.zhang.service.BlogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("blogService")
public class BlogServiceImpl implements BlogService {

    @Resource
    private BlogDao blogDao;

    @Resource
    private CommentDao commentDao;

    public List<Blog> countList() {
        return blogDao.countList();
    }

    public List<Blog> list(Map<String, Object> map) {
        return blogDao.list(map);
    }

    public Long getTotal(Map<String, Object> map) {
        return blogDao.getTotal(map);
    }

    public Blog findById(Integer id) {
        return blogDao.findById(id);
    }

    public Integer addBlog(Blog blog) {
        return blogDao.addBlog(blog);
    }

    public Integer updateBlog(Blog blog) {
        return blogDao.updateBlog(blog);
    }

    public Integer deleteBlog(Integer id) {
        commentDao.deleteByBlogId(id);
        return blogDao.deleteBlog(id);
    }

    public Integer getBlogByTypeId(Integer type_id) {
        return blogDao.getBlogByTypeId(type_id);
    }

    public Blog getLastBlog(Integer id) {
        return blogDao.getLastBlog(id);
    }

    public Blog getNextBlog(Integer id) {
        return blogDao.getNextBlog(id);
    }
}
