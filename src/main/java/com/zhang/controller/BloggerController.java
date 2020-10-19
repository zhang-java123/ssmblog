package com.zhang.controller;

import com.zhang.pojo.Blogger;
import com.zhang.service.BloggerService;
import com.zhang.util.CryptographyUtil;
import org.apache.shiro.SecurityUtils;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/blogger")
public class BloggerController {
    @Resource
    private BloggerService bloggerService;

    @RequestMapping("/login")
    public String login(Blogger blogger, HttpServletRequest request){
        String userName = blogger.getUserName();
        String password = blogger.getPassword();

        String s = CryptographyUtil.md5(password,"zhanghaibo");
        //subject
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, s);
        try{
            // 传递token
            subject.login(token);
            return "redirect:/admin/main.jsp";
        }catch (Exception e){
            e.printStackTrace();
            request.setAttribute("blogger", blogger);
            request.setAttribute("errorInfo", "用户名或密码错误");
        }
        return "login";
    }

    @RequestMapping("/aboutMe")
    public ModelAndView aboutMe(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("blogger",bloggerService.findBlogger());
        modelAndView.addObject("mainPage","foreground/blogger/bloggerInfo.jsp");
        modelAndView.addObject("pageTitle","关于博主");
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
