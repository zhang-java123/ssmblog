package com.zhang.dao;

import com.zhang.pojo.Blog;

import java.util.List;
import java.util.Map;

public interface BlogDao {

    // 无参数查询所有博客列表
    List<Blog> countList();

    // 带参数查询博客列表
    List<Blog> list(Map<String, Object> map);

    // 带参数查询博客数量
    Long getTotal(Map<String, Object> map);

    // 根据id查询一条博客信息
    Blog findById(Integer id);

    // 添加博客
    Integer addBlog(Blog blog);

    // 修改博客
    Integer updateBlog(Blog blog);

    // 根据id删除一条博客
    Integer deleteBlog(Integer id);

    //根据类型查询博客数量
    Integer getBlogByTypeId(Integer type_id);

    // 根据当前博客查询上一篇
    Blog getLastBlog(Integer id);

    // 根据当前博客查询下一篇
    Blog getNextBlog(Integer id);

}
