package com.glory.demo.project.system.menu.mapper;

import com.glory.demo.project.system.menu.domain.Menu;

import java.util.List;

/**
 * author : glory
 * date : 2019/12/9 21:24
 * description : 菜单表 数据层
 */
public interface MenuMapper {

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    public List<String> selectPermsByUserId(Long userId);

    public List<Menu> selectMenuList();
}
