package com.zhang.controller.admin;
/**
 *  评论管理
 */

import com.zhang.pojo.Comment;
import com.zhang.pojo.PageBean;
import com.zhang.service.CommentService;
import com.zhang.util.DateJsonValueProcessor;
import com.zhang.util.ResponseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/comment")
public class CommentAdminController {
    @Resource
    private CommentService commentService;

    // 评论查询列表
    @RequestMapping("/listComment")
    public String listComment(@RequestParam(value = "page",required = false)String page,
                              @RequestParam(value = "rows",required = false)String rows,
                              @RequestParam(value = "state",required = false)String state,
                              HttpServletResponse response) throws IOException {
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        map.put("state", state);
        //查询评论列表
        List commentList = commentService.listComment(map);
        //查询评论总数
        Long total = commentService.getTotalComment(map);
        JSONObject result = new JSONObject();
        JsonConfig config = new JsonConfig();
        config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyy-MM-dd"));
        JSONArray jsonArray = JSONArray.fromObject(commentList, config);
        result.put("rows", jsonArray);
        result.put("total", total);
        ResponseUtil.write(response, result);
        return null;
    }

    //删除评论
    @RequestMapping("/deleteComment")
    public String deleteComment(@RequestParam("ids") String ids, HttpServletResponse response) throws IOException {
        String[] idsStr = ids.split(",");
        JSONObject result = new JSONObject();
        for (int i = 0; i < idsStr.length; i++) {
            commentService.deleteComment(Integer.parseInt(idsStr[i]));
        }
        result.put("success", Boolean.valueOf(true));
        ResponseUtil.write(response, result);
        return null;
    }

    //评论审核
    @RequestMapping("/review")
    public String review(@RequestParam("ids") String ids,
                         @RequestParam("state") String state,
                         HttpServletResponse response) throws IOException {
        String[] idsStr = ids.split(",");
        for (int i = 0; i < idsStr.length; i++) {
            Comment comment = new Comment();
            comment.setId(Integer.parseInt(idsStr[i]));
            comment.setState(Integer.parseInt(state));
            commentService.updateComment(comment);
        }
        JSONObject result = new JSONObject();
        result.put("success", Boolean.valueOf(true));
        ResponseUtil.write(response, result);
        return null;
    }
}
