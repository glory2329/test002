package com.glory.demo.project.system.role.service;

import com.glory.demo.common.utils.StringUtils;
import com.glory.demo.project.system.role.domain.Role;
import com.glory.demo.project.system.role.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * author : glory
 * date : 2019/12/9 21:09
 * description : 角色 业务层处理
 */
@Service
public class IRoleServiceImpl implements IRoleService{

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectRoleKeys(Long userId)
    {
        List<Role> perms = roleMapper.selectRolesByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (Role perm : perms)
        {
            if (StringUtils.isNotNull(perm))
            {
                permsSet.addAll(Arrays.asList(perm.getRoleKey().trim().split(",")));
            }
        }
        return permsSet;
    }
}
