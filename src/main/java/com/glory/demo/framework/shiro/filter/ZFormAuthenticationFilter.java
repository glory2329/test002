package com.glory.demo.framework.shiro.filter;

import com.glory.demo.framework.shiro.service.LoginService;
import com.glory.demo.project.system.user.domain.User;
import com.glory.demo.project.system.user.service.IUserService;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * author : glory
 * date : 2019/12/13 19:17
 * description : rememberMe 过滤器
 */
public class ZFormAuthenticationFilter extends FormAuthenticationFilter {

    @Autowired
    private IUserService userService;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = getSubject(request, response);
        Session session = subject.getSession();
        // 如果是记住我登录的，则需要处理一下
        // isRemembered为true、isAuthenticated为false
       /* if (!subject.isAuthenticated() && subject.isRemembered()) {
            // 通过记住我第一次进程序，并且保存的principal中有内容，添加用户到session
            if (subject.getSession().getAttribute("user") == null && subject.getPrincipal() != null) {
                subject.getSession().setAttribute("user", (User) subject.getPrincipal());
            }
        }*/
        if (!subject.isAuthenticated() && subject.isRemembered() && subject.getPrincipal() != null) {
            // 通过记住我第一次进程序，并且保存的principal中有内容，添加用户到session
            Object principal = subject.getPrincipal();
            if (principal != null) {
                String account = principal.toString();
                User user = userService.selectUserByLoginName(account);
                session.setAttribute("user", user);
            }
        }
        return subject.isAuthenticated() || subject.isRemembered();
    }

}
