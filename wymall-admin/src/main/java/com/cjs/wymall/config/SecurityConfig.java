package com.cjs.wymall.config;

import com.cjs.wymall.security.config.BaseSecurityConfig;
import com.cjs.wymall.service.user.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;


/**
 * @description: 后台管理spring security配置类
 * @author: cuijunsheng
 * @date: 2020/5/31
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends BaseSecurityConfig {
    @Autowired
    private UmsAdminService adminService;

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        //获取登录用户信息(主要是用户和相关权限，实现UserDetailsService的loadUserByUsername())
        return username -> adminService.loadUserByUsername(username);
    }


}