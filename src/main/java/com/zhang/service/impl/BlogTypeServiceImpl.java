package com.zhang.service.impl;

import com.zhang.dao.BlogTypeDao;
import com.zhang.pojo.BlogType;
import com.zhang.service.BlogTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("blogTypeService")
public class BlogTypeServiceImpl implements BlogTypeService {

    @Resource
    private BlogTypeDao blogTypeDao;

    public List<BlogType> countList() {
        return blogTypeDao.countList();
    }

    public BlogType findById(Integer id) {
        return blogTypeDao.findById(id);
    }

    public List<BlogType> list(Map<String, Object> paramMap) {
        return blogTypeDao.list(paramMap);
    }

    public Long getTotal(Map<String, Object> paramMap) {
        return blogTypeDao.getTotal(paramMap);
    }

    public Integer add(BlogType blogType) {
        return blogTypeDao.add(blogType);
    }

    public Integer update(BlogType blogType) {
        return blogTypeDao.update(blogType);
    }

    public Integer delete(Integer id) {
        return blogTypeDao.delete(id);
    }
}
