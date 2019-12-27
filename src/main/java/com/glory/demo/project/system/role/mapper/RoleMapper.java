package com.glory.demo.project.system.role.mapper;

import com.glory.demo.project.system.role.domain.Role;

import java.util.List;

/**
 * author : glory
 * date : 2019/12/9 21:16
 * description : 角色表 数据层
 */
public interface RoleMapper {

    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return 角色列表
     */
    public List<Role> selectRolesByUserId(Long userId);
}
