package com.cjs.wymall.config;

import com.cjs.wymall.bo.AdminUserDetails;
import com.cjs.wymall.model.UmsAdmin;
import com.cjs.wymall.model.UmsPermission;
import com.cjs.wymall.security.component.JwtAuthenticationTokenFilter;
import com.cjs.wymall.security.component.RestAuthenticationEntryPoint;
import com.cjs.wymall.security.component.RestfulAccessDeniedHandler;
import com.cjs.wymall.security.util.JwtTokenUtils;
import com.cjs.wymall.service.user.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

/**
 * @description: 后台管理spring security配置类
 * @author: cuijunsheng
 * @date: 2020/5/31
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UmsAdminService adminService;

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return username -> {
            UmsAdmin admin = adminService.getAdminByUsername(username);
            if (admin != null) {
                List<UmsPermission> permissionList = adminService.listPermissions(admin.getId());
                return new AdminUserDetails(admin,permissionList);
            }
            throw new UsernameNotFoundException("用户名或密码错误");
        };
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        //由于使用的是JWT，这里不需要要csrf
        httpSecurity.csrf()
                .disable()
                .sessionManagement() //基于token,不需要session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //允许对于网站资源的静态资源无授权访问
                .antMatchers(HttpMethod.GET,
                        "/",
                        "/*.html",
                        "favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/swagger-resources/**",
                        "/v2/api-docs/**")
                .permitAll()
                //对登录注册允许匿名访问
                .antMatchers("/admin/login", "/admin/createUser")
                .permitAll()
                //跨域请求会先进行一次options请求
                .antMatchers(HttpMethod.OPTIONS)
                .permitAll()
                //测试时全部放行
                //.antMatchers("/**")
                //.permitAll()
                //除上面的请求外，其他全部需要鉴权认证
                .anyRequest()
                .authenticated();

        //禁用缓存
        httpSecurity.headers().cacheControl();
        // 添加JWT filter
        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        //添加自定义未授权和未登录结果返回
        httpSecurity.exceptionHandling()
                .accessDeniedHandler(new RestfulAccessDeniedHandler())
                .authenticationEntryPoint(new RestAuthenticationEntryPoint());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    /**
     * SpringSecurity定义的用于对密码进行编码及比对的接口
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}