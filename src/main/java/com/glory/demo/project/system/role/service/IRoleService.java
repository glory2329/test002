package com.glory.demo.project.system.role.service;

import java.util.Set;

/**
 * author : glory
 * date : 2019/12/9 21:08
 * description :  role角色业务层
 */
public interface IRoleService {

    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    public Set<String> selectRoleKeys(Long userId);
}
