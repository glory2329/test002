package com.glory.demo.framework.shiro.Interceptor;

import com.glory.demo.project.system.user.domain.User;
import com.glory.demo.project.system.user.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * author : glory
 * date : 2019/12/13 14:56
 * description : SessionInterceptor 拦截器
 */
public class SessionInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(SessionInterceptor.class);

    @Autowired
    private IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        log.info("---preHandle---");
        System.out.println(request.getContextPath());
        Subject currentUser = SecurityUtils.getSubject();
        //判断用户是通过记住我功能自动登录,此时session失效
        if (!currentUser.isAuthenticated() && currentUser.isRemembered()) {
            try {
                User user = userService.selectUserByLoginName(currentUser.getPrincipals().toString());
                //对密码进行加密后验证
                UsernamePasswordToken token = new UsernamePasswordToken(user.getLoginName(), user.getPassword(), currentUser.isRemembered());
                //把当前用户放入session
                currentUser.login(token);
                Session session = currentUser.getSession();
                session.setAttribute("currentUser", user);
                //设置会话的过期时间--ms,默认是30分钟，设置负数表示永不过期
                session.setTimeout(-1000l);
            } catch (Exception e) {
                //自动登录失败,跳转到登录页面
                response.sendRedirect("/login");
                return false;
            }
            if (!currentUser.isAuthenticated()) {
                //自动登录失败,跳转到登录页面
                response.sendRedirect("/login");
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        log.info("---postHandle---");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        log.info("---afterCompletion---");
    }
}

