package com.glory.demo.framework.config;

import com.glory.demo.framework.shiro.Interceptor.RememberMeInterceptor;
import com.glory.demo.framework.shiro.Interceptor.SessionInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * author : glory
 * date : 2019/12/13 14:54
 * description : SpringMVC的配置文件
 */
/*@Configuration*/
public class ShiroWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

    /**
     * 此方法把该拦截器实例化成一个bean,否则在拦截器里无法注入其它bean
     * @return
     */
    @Bean
    RememberMeInterceptor sessionInterceptor() {
        return new RememberMeInterceptor();
    }

    /**
     * 配置拦截器
     * @param registry
     */
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login","/permission/userInsert/user");
    }
}
