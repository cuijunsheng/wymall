package com.cjs.wymall.config;

import com.cjs.wymall.model.UmsResource;
import com.cjs.wymall.security.component.DynamicSecurityService;
import com.cjs.wymall.security.config.BaseSecurityConfig;
import com.cjs.wymall.service.UmsAdminService;
import com.cjs.wymall.service.UmsResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


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
    @Autowired
    private UmsResourceService resourceService;

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        //获取登录用户信息(主要是用户和相关权限，实现UserDetailsService的loadUserByUsername())
        return username -> adminService.loadUserByUsername(username);
    }


    @Bean
    public DynamicSecurityService dynamicSecurityService() {
        return () -> {
            Map<String, ConfigAttribute> map = new ConcurrentHashMap<>();
            List<UmsResource> resourceList = resourceService.listResources();
            for (UmsResource resource : resourceList) {
                map.put(resource.getUrl(), new org.springframework.security.access.SecurityConfig(resource.getId() + ":" + resource.getName()));
            }
            return map;
        };
    }
}