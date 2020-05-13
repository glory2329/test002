package com.glory.demo.project.system.user.service;

import java.util.List;
import java.util.Set;

import com.glory.demo.project.system.user.domain.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

/**
 * author : glory
 * date : 2019/12/6 17:31
 * description : 用户 业务层
 */
public interface IUserService {


    public List<User> selectUserList();

    public User selectUserByLoginName(String userName);

    public User selectUserById(Long userId);

    public Set<String> getRoles(String username);

    public Set<String> getPermissions(Set<String> roles);

}
