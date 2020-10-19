package com.zhang.service;

import com.zhang.pojo.Comment;

import java.util.List;
import java.util.Map;

public interface CommentService {

    // 添加一条评论
    int addComment(Comment comment);

    // 更新一条评论
    int updateComment(Comment comment);

    // 评论查询
    List<Comment> listComment(Map<String, Object> map);

    // 评论数量
    Long getTotalComment(Map<String, Object> map);

    // 删除评论
    Integer deleteComment(Integer id);
}
