package com.zhang.controller.admin;

import com.zhang.pojo.Blog;
import com.zhang.pojo.BlogType;
import com.zhang.pojo.Blogger;
import com.zhang.pojo.Link;
import com.zhang.service.BlogService;
import com.zhang.service.BlogTypeService;
import com.zhang.service.BloggerService;
import com.zhang.service.LinkService;
import com.zhang.util.Const;
import com.zhang.util.ResponseUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin/system")
public class SystemAdminController {

    @Resource
    private BlogTypeService blogTypeService;

    @Resource
    private BloggerService bloggerService;

    @Resource
    private BlogService blogService;

    @Resource
    private LinkService linkService;

    /**
     * 刷新系统缓存
     */
    @RequestMapping("/refreshSystem")
    public String rerefreshSystem(HttpServletResponse response, HttpServletRequest request) throws IOException {
        ServletContext application = RequestContextUtils.findWebApplicationContext(request).getServletContext();

        // 博客类别
        List<BlogType> list = blogTypeService.countList();
        application.setAttribute(Const.BLOG_TYPE_COUNT_LIST,list);

        // 博主信息
        Blogger blogger = bloggerService.findBlogger();
        blogger.setPassword(null);
        application.setAttribute(Const.BLOGGER, blogger);

        // 按年月分类的博客数量
        List<Blog> blogCountList = blogService.countList();
        application.setAttribute(Const.BLOG_COUNT_LIST, blogCountList);

        // 友情链接
        List<Link> linkList = linkService.listLink(null);
        application.setAttribute(Const.LINK_LIST, linkList);

        JSONObject result = new JSONObject();
        result.put("success", Boolean.TRUE);
        ResponseUtil.write(response, result);
        return null;

    }
}
