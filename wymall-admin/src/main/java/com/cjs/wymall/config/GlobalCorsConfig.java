package com.cjs.wymall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @description: 全局跨域配置
 * @author: cuijunsheng
 * @date: 2020/6/7
 */
@Configuration
public class GlobalCorsConfig {

    /**
     * 允许跨域调用的过滤器，浏览器有同源策略，不允许不同域名或者同域名不同端口资源之间进行访问调用，
     * 前后端分离前端项目调用后台接口必然会产生跨域，使用cors(cross-origin-resource-sharing)跨域资源
     * 共享解决
     *
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        //允许所有域名进行跨域调用
        config.addAllowedOrigin("*");
        //允许跨越发送cookie
        config.setAllowCredentials(true);
        //放行全部原始头信息
        config.addAllowedHeader("*");
        //允许所有请求方法跨域调用
        config.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}