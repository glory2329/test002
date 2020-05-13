package com.glory.demo.project.system.menu.service;

import com.glory.demo.common.utils.StringUtils;
import com.glory.demo.project.system.menu.domain.Menu;
import com.glory.demo.project.system.menu.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * author : glory
 * date : 2019/12/9 21:26
 * description : 菜单 业务层处理
 */
@Service
public class IMenuServiceImpl implements IMenuService {

    public static final String PREMISSION_STRING = "perms[\"{0}\"]";

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectPermsByUserId(Long userId)
    {
        List<String> perms = menuMapper.selectPermsByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms)
        {
            if (StringUtils.isNotEmpty(perm))
            {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    @Override
    public List<Menu> selectMenuList()
    {
        List<Menu> menuList = null;
        /*User user = ShiroUtils.getSysUser();
        if (user.isAdmin())
        {
            menuList = menuMapper.selectMenuList(menu);
        }
        else
        {
            menu.getParams().put("userId", user.getUserId());
            menuList = menuMapper.selectMenuListByUserId(menu);
        }*/
        menuList = menuMapper.selectMenuList();
        return menuList;
    }
}
