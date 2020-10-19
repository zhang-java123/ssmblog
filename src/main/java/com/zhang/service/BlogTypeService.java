package com.zhang.service;

import com.zhang.pojo.BlogType;

import java.util.List;
import java.util.Map;

public interface BlogTypeService {
    // 无参数查询所有博客类型列表
    List<BlogType> countList();

    //根据id查询一条博客类型
    BlogType findById(Integer id);

    //不固定参数查询博客类型列表
    List<BlogType> list(Map<String, Object> paramMap);

    //不固定参数查询博客类型列表总数
    Long getTotal(Map<String, Object> paramMap);

    //添加博客类型
    Integer add(BlogType blogType);

    //修改博客类型
    Integer update(BlogType blogType);

    //删除博客类型
    Integer delete(Integer id);
}
