package com.glory.demo.project.system.menu.service;

import com.glory.demo.project.system.menu.domain.Menu;

import java.util.List;
import java.util.Set;

/**
 * author : glory
 * date : 2019/12/9 21:25
 * description : 菜单 业务层
 */
public interface IMenuService {
    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    public Set<String> selectPermsByUserId(Long userId);

    public List<Menu> selectMenuList();
}
