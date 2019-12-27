package com.glory.demo.project.system.Cache;

import com.glory.demo.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;

/**
 * author : glory
 * date : 2019/12/10 14:44
 * description :
 */
@RestController
/*@RequestMapping("/system/user")*/
public class CacheController {

    @Autowired
    ICacheService  cacheService;

    @RequestMapping(value = "/cache/getUser")
    public User getUserDetailsByUid(){
        try {
            return cacheService.selectUserByUid((long)3);
        }catch (Exception e){
            System.out.println(e.toString());
            return null;
        }
    }

    @RequestMapping(value = "/cache/updateUser")
    public long updateUserInfo(){
        User userDetails = new User();
        userDetails.setUserId((long)3);
        userDetails.setUserName("测试用户redis");
        User user = cacheService.updateUser(userDetails);
        return user == null ? 0 : userDetails.getUserId();
    }

    @RequestMapping(value = "/cache/delUser")
    public User delUserInfoById(){
        return cacheService.selectUserByUid((long)3);
    }
}
