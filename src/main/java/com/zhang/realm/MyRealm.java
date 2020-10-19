package com.zhang.realm;

import com.zhang.pojo.Blogger;
import com.zhang.service.BloggerService;
import com.zhang.util.Const;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

public class MyRealm extends AuthorizingRealm {
    @Resource
    private BloggerService bloggerService;

    /**
     * 获取授权信息
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }


    /**
     * 登录验证
     * token令牌，基于用户名与密码的令牌
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //从令牌中取出用户名
        String userName = (String) token.getPrincipal();
        //从用户名查询博主信息，让shiro验证账号密码
        Blogger blogger = bloggerService.getByUserName(userName);
        if(blogger != null){
            SecurityUtils.getSubject().getSession().setAttribute(Const.CURRENT_USER, blogger);
            AuthenticationInfo authenInfo = new SimpleAuthenticationInfo(blogger.getUserName(),blogger.getPassword(),getName());
            // 返回一个和令牌相关的正确的验证信息
            return authenInfo;
        }
        return null;
    }
}
