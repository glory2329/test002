package com.glory.demo.project.system.Cache;

import com.glory.demo.project.system.user.domain.User;

/**
 * author : glory
 * date : 2019/12/10 14:39
 * description :
 */
public interface ICacheService {

     User selectUserByUid(long uid);
     User updateUser(User user);
}
