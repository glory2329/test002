package com.glory.demo.project.system.user.service;

import com.glory.demo.framework.aspectj.annotation.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.glory.demo.framework.aspectj.enums.DataSourceType;
import com.glory.demo.project.system.user.domain.User;
import com.glory.demo.project.system.user.mapper.UserMapper;

/**
 * author : glory
 * date : 2019/12/6 17:32
 * description : 用户 业务层处理
 */
@Service
/*@DataSource(value = DataSourceType.SLAVE)*/
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<User> selectUserList()
    {
        return userMapper.getUsers();
    }


    @Override
    public User selectUserByLoginName(String userName)
    {
        // springboot  redis 缓存测试
       /*
        String key = "user_" + "20191210";
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        //判断redis中是否有键为key的缓存
        boolean hasKey = redisTemplate.hasKey(key);

        if (hasKey) {
            User user = operations.get(key);
            return user;
        } else {
            User user=userMapper.selectUserByLoginName(userName);
            // 写入缓存
            operations.set(key, user, 5, TimeUnit.HOURS);
            return user;
        }*/

        //  正常模式
        return userMapper.selectUserByLoginName(userName);
    }

    @Override
    public User selectUserById(Long userId)
    {
        return userMapper.selectUserById(userId);
    }

    @Override
    public Set<String> getRoles(String username)
    {
        Set<String> roleSet=new LinkedHashSet<String>();
        for (int i = 0; i < 3; i++) {
            roleSet.add(i+"");
        }
        return roleSet;
    }

    @Override
    public Set<String> getPermissions(Set<String> roles)
    {
        Set<String> roleSet=new LinkedHashSet<String>();
        for (int i = 0; i < 3; i++) {
            roleSet.add(i+"");
        }
        return roleSet;
    }





   /* *//**
     * 更新用户策略：先更新数据表，成功之后，删除原来的缓存，再更新缓存
     *//*
    public int updateUser(User user) {
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        int result = userDao.updateUser(user);
        if (result != 0) {
            String key = "user_" + user.getUid();
            boolean haskey = redisTemplate.hasKey(key);
            if (haskey) {
                redisTemplate.delete(key);
                System.out.println("删除缓存中的key-----------> " + key);
            }
            // 再将更新后的数据加入缓存
            User userNew = userDao.findUserById(user.getUid());
            if (userNew != null) {
                operations.set(key, userNew, 3, TimeUnit.HOURS);
            }
        }
        return result;
    }*/







}

