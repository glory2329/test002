package com.glory.demo.framework.shiro.service;

import com.glory.demo.common.utils.DateUtils;
import com.glory.demo.common.utils.ServletUtils;
import com.glory.demo.common.utils.security.ShiroUtils;
import com.glory.demo.project.system.user.domain.User;
import com.glory.demo.project.system.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * author : glory
 * date : 2019/12/11 11:36
 * description : 登录校验方法
 */
@Component
@Slf4j
public class LoginService {

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private IUserService userService;

    /**
     * 登录
     */
    public User login(String username, String password) {
        log.info("login 用户[" + username + "],密码[" + password + "]");
        // 用户名或密码为空 错误
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            //AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("not.null")));
            //throw new Exception();
            log.info("账号或密码为空!"); //29c67a30398638269fe600f73a054934
            throw new AuthenticationException("msg:账号或密码为空.");
        }

        // 查询用户信息
        User user = userService.selectUserByLoginName(username);
        //  查询的
        String findPwd = user.getPassword();
        //  加密的
        String loginPwd = passwordService.encryptPassword(username, password, user.getSalt());

        if (!loginPwd.equals(findPwd)) {
            throw new AuthenticationException("msg:账号或密码错误.");
        }

        recordLoginInfo(user);
        return user;
    }


    /**
     * 记录登录信息
     */
    public void recordLoginInfo(User user) {
        user.setLoginIp(ShiroUtils.getIp());
        user.setLoginDate(DateUtils.getNowDate());
        //userService.(user);
    }
}

