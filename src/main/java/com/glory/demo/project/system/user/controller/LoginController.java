package com.glory.demo.project.system.user.controller;

import com.glory.demo.framework.web.controller.BaseController;
import com.glory.demo.project.system.user.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * author : glory
 * date : 2019/12/8 14:18
 * description : 登录Controller
 */
@Controller
public class LoginController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/login")
    public String login() {
        return "login";
    }

   /* @PostMapping("/login")
    //public String login(User user, HttpServletRequest request, boolean rememberMe) {
    public String login(String username, String password, boolean rememberMe, HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        try {
            subject.login(token);

            log.info("isAuthenticated[" + subject.isAuthenticated() + "]");
            //User cuser = (User) subject.getPrincipal();
            //subject.getSession().setAttribute("user", cuser);
            //session.setAttribute("user", cuser);

            //设置记住我
            //token.setRememberMe(true);
            //判断是否登录成功
            if (subject.isAuthenticated()) {
                return "redirect:index";//登录成功
            } else {
                return "login";
            }
        } catch (AuthenticationException e) {
            return "login";
        }
    }
*/

    @PostMapping("/login")
    //public String login(User user, HttpServletRequest request) {
    public String login(HttpServletRequest request, String username, String password, boolean rememberMe, Model model, HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        //UsernamePasswordToken token=new UsernamePasswordToken("admin","admin123");
        //UsernamePasswordToken token = new UsernamePasswordToken(user.getLoginName(), user.getPassword());
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        try {
            subject.login(token);

            //User cuser = (User) subject.getPrincipal();
            //subject.getSession().setAttribute("user", cuser);
            //session.setAttribute("user", cuser);

            //设置记住我
            //token.setRememberMe(true);
            //判断是否登录成功
            if (subject.isAuthenticated()) {
                return "redirect:index";//登录成功
            } else {
                return "login";
            }
        } catch (AuthenticationException e) {
            return "login";
        }
    }

    @RequestMapping("/info")
    public String info() {
        return "index";
    }
}
