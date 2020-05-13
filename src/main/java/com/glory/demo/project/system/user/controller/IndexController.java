package com.glory.demo.project.system.user.controller;

import com.glory.demo.common.utils.StringUtils;
import com.glory.demo.framework.web.controller.BaseController;
import com.glory.demo.project.system.user.domain.User;
import com.glory.demo.project.system.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * author : glory
 * date : 2019/12/8 22:25
 * description : 系统首页控制器
 */
@Controller
@Slf4j
public class IndexController extends BaseController {

    @Autowired
    private IUserService userService;

   /* @ModelAttribute
    public User get(@RequestParam(required=false) String userName) {
        User entity = null;
        if (StringUtils.isNotBlank(userName)){
            entity = userService.selectUserByLoginName(userName);
        }
        if (entity == null){
            entity = new User();
        }
        return entity;
    }*/

    // 系统运行默认进入登录页面
    @GetMapping("/")
    public String index() {
        Subject subject = SecurityUtils.getSubject();
        User user=(User) subject.getPrincipal();
        if (user == null){
            return "redirect:/login";
        }else{
            return "redirect:/index";
        }
    }

    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap) {
        User entity = null;
        entity = userService.selectUserByLoginName("admin");
        mmap.put("user", entity);
        return "index";
    }
}
