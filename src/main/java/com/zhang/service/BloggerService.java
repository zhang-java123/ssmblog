package com.zhang.service;

import com.zhang.pojo.Blogger;
import org.apache.ibatis.annotations.Param;

public interface BloggerService {

    // 根据登录名查询博主对象
    Blogger getByUserName(@Param("userName") String userName);

    // 更新博主对象
    Integer updateBlogger(Blogger blogger);

    // 查询博主
    Blogger findBlogger();
}
