package com.zhang.controller;

import com.zhang.pojo.Blog;
import com.zhang.pojo.Comment;
import com.zhang.service.BlogService;
import com.zhang.service.CommentService;
import com.zhang.util.ResponseUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 用户评论提交
 */
@Controller
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @Resource
    private BlogService blogService;

    @RequestMapping("/save")
    public String save(Comment comment, @RequestParam("imageCode")String imageCode,
                       HttpServletRequest request, HttpServletResponse response,
                       HttpSession session) throws IOException {
        String sRand = (String) session.getAttribute("sRand");
        JSONObject result = new JSONObject();
        int resultTotal = 0;
        if(!imageCode.equals(sRand)){
            result.put("success",false);
            result.put("errorInfo","验证码填写错误");
        }else{
            String userIp = request.getRemoteAddr();
            comment.setUserIp(userIp);
            if(comment.getId()==null){
                resultTotal = commentService.addComment(comment);
                // 给对应的博客评论加一
                Blog blog = blogService.findById(comment.getBlog().getId());
                blog.setReplyHit(blog.getReplyHit()+1);
                blogService.updateBlog(blog);
            }
        }
        if(resultTotal > 0){
            result.put("success",true);
        }else{
            result.put("success",false);
        }
        ResponseUtil.write(response, result);
        return null;

    }
}
