package com.zhang.controller.admin;

import com.zhang.pojo.Blogger;
import com.zhang.service.BloggerService;
import com.zhang.util.Const;
import com.zhang.util.CryptographyUtil;
import com.zhang.util.DateUtil;
import com.zhang.util.ResponseUtil;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/admin/blogger")
public class BloggerAdminController {

    @Resource
    private BloggerService bloggerService;

    @RequestMapping("/save")
    public String save(@RequestParam("imageFile")MultipartFile imageFile, Blogger blogger,
                       HttpServletResponse response, HttpServletRequest request) throws IOException {
        if(!imageFile.isEmpty()){
            String filePath = request.getServletContext().getRealPath("/");
            String imageName = DateUtil.getCurrentDateStr()+"."+imageFile.getOriginalFilename().split("\\.")[1];
            imageFile.transferTo(new File(filePath + "static/userImages/" + imageName));
            blogger.setImageName(imageName);
        }

        int resultTotal = bloggerService.updateBlogger(blogger);

        StringBuffer result = new StringBuffer();

        if(resultTotal > 0){
            result.append("<script language='javascript'>alert('修改成功');</script>");
        }else{
            result.append("<script language='javascript'>alert('修改失败');</script>");
        }
        ResponseUtil.write(response, result);
        return null;
    }

    // 获取博主json格式
    @RequestMapping("/find")
    public String find(HttpServletResponse response) throws IOException {
        Blogger blogger = (Blogger)SecurityUtils.getSubject().getSession().getAttribute(Const.CURRENT_USER);
        JSONObject jsonObject = JSONObject.fromObject(blogger);
        ResponseUtil.write(response, jsonObject);
        return null;
    }

    // 修改密码
    @RequestMapping("/modifyPassword")
    public String modifyPassword(@RequestParam("id") String id,
                                 @RequestParam("newPassword") String newPassword,
                                 HttpServletResponse response) throws IOException {
        Blogger blogger = new Blogger();
        blogger.setId(Integer.parseInt(id));
        blogger.setPassword(CryptographyUtil.md5(newPassword,"zhanghaibo"));
        int resultTotal = bloggerService.updateBlogger(blogger);
        JSONObject result = new JSONObject();
        if(resultTotal > 0){
            result.put("success", true);
        }else{
            result.put("success", false);
        }
        ResponseUtil.write(response, result);
        return null;
    }

    @RequestMapping("/logout")
    public String logout(){
        SecurityUtils.getSubject().logout();
        return "redirect:/login.jsp";
    }
}
