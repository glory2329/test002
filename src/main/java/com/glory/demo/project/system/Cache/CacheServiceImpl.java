package com.glory.demo.project.system.Cache;

import com.glory.demo.project.system.user.domain.User;
import com.glory.demo.project.system.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * author : glory
 * date : 2019/12/10 14:40
 * description :
 */
@Service
public class CacheServiceImpl implements ICacheService{
    @Autowired
    UserMapper userMapper;

    @Override
    @Cacheable(value = "user_details", key = "#uid", unless="#result == null")
    public User selectUserByUid(long uid){
        System.out.println(" Cacheable 有请求过来了");
        User userDetails = userMapper.selectUserById(uid);
        return userDetails;
    }

    @Override
    @CachePut(value = "user_details", key = "#user.getUserId()")  //或者 result.id //  或者@CachePut(value = "user_details", key = "#user.userId")
    public User updateUser(User user){
        System.out.println(" CachePut 有请求过来了");
        if(userMapper.updateUser(user) > 0) {
            // 这里也可以直接在updateByPrimaryKeySelective的方法里，修改后直接查询出该记录返回UserDetails实例，看需求。
            User userDetails = userMapper.selectUserById(user.getUserId());
            return userDetails;
        }else{
            return null;
        }
    }

   /* @Override
    @CacheEvict(value = "user_details", key = "#uid")
    public int delUserInfoById(int uid){
        System.out.println(" CacheEvict 有请求过来了");
        return userMapper.deleteByPrimaryKey(uid);
    }*/
}
