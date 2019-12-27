package com.glory.demo.framework.shiro.Interceptor;

import com.glory.demo.project.system.user.service.IUserService;
import org.apache.shiro.SecurityUtils;
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
 * date : 2019/12/13 15:03
 * description : 拦截rememberMe的请求，添加user到session中
 */
public class RememberMeInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(RememberMeInterceptor.class);

    @Autowired
    private IUserService userService;

    public RememberMeInterceptor() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 获取session中的subject
        Subject user = SecurityUtils.getSubject();

        // 判断是不是通过记住我登录
        if( !user.isAuthenticated() && user.isRemembered()) {
            log.info("remembered me, put user in session");
            user.getSession().setAttribute("user", user.getPrincipal());

        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub

    }

}
