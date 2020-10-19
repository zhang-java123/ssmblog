package com.zhang.service.impl;

import com.zhang.dao.CommentDao;
import com.zhang.pojo.Comment;
import com.zhang.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("commentService")
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentDao commentDao;

    public int addComment(Comment comment) {
        return commentDao.addComment(comment);
    }

    public int updateComment(Comment comment) {
        return commentDao.updateComment(comment);
    }

    public List<Comment> listComment(Map<String, Object> map) {
        return commentDao.listComment(map);
    }

    public Long getTotalComment(Map<String, Object> map) {
        return commentDao.getTotalComment(map);
    }

    public Integer deleteComment(Integer id) {
        return commentDao.deleteComment(id);
    }
}
