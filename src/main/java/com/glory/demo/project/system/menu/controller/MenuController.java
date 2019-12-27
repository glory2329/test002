package com.glory.demo.project.system.menu.controller;

import com.glory.demo.common.utils.RedisUtil;
import com.glory.demo.framework.web.controller.BaseController;
import com.glory.demo.project.system.menu.domain.Menu;
import com.glory.demo.project.system.menu.service.IMenuService;
import com.glory.demo.project.system.user.domain.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * author : glory
 * date : 2019/12/10 11:26
 * description :
 */
@Controller
@RequestMapping("/system/menu")
public class MenuController extends BaseController {

    private String prefix = "system/menu";

    @Autowired
    private IMenuService menuService;

    @Autowired
    private RedisUtil redisService;

    @GetMapping()
    public String menu()
    {
        return prefix + "/menu";
    }

    /*@PostMapping("/list")
    @ResponseBody
    public List<Menu> list(Menu menu)
    {
        List<Menu> menuList = menuService.selectMenuList();
        return menuList;
    }*/

    /*@GetMapping("/list")
    @ResponseBody
    public List<Menu> getlist(Menu menu)
    {
        List<Menu> menuList = menuService.selectMenuList();
        return menuList;
    }*/


    @GetMapping("/list")
    public String list(ModelMap mmap)
    {
        List<Menu> menuList = menuService.selectMenuList();
        mmap.put("menulist", menuList);
        redisService.lSet("menukey",menuList,120);
        return prefix + "/menu";
    }
}
