package com.glory.demo.project.system.user.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.glory.demo.framework.web.controller.BaseController;
import com.glory.demo.project.system.user.service.IUserService;
import com.glory.demo.project.system.user.domain.User;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * author : glory
 * date : 2019/12/6 17:24
 * description : 用户信息Controller
 */
@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @GetMapping("/list")
    @ResponseBody
    public User list(Model model)
    {
        List<User> list = userService.selectUserList();
        return list.get(1);
    }

    @GetMapping("/list0")
    @ResponseBody
    public List<User> list()
    {
        List<User> list = userService.selectUserList();
        return list;
    }

}
