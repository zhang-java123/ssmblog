package com.zhang.dao;

import com.zhang.pojo.BlogType;
import com.zhang.pojo.Link;

import java.util.List;
import java.util.Map;

public interface LinkDao {

    //根据id查询一条友情链接
    Link findById(Integer id);

    //不固定参数查询友情链接
    List<Link> listLink(Map<String, Object> paramMap);

    //不固定参数查询友情链接
    Long getTotal(Map<String, Object> paramMap);

    //添加友情链接
    Integer addLink(Link link);

    //修改友情链接
    Integer updateLink(Link link);

    //删除友情链接
    Integer deleteLink(Integer id);
}
