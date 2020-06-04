package com.cjs.wymall.service.user;

import com.cjs.wymall.dto.UmsAdminDTO;
import com.cjs.wymall.model.UmsAdmin;
import com.cjs.wymall.model.UmsPermission;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * @description:
 * @author: cuijunsheng
 * @date: 2020/5/31
 */
public interface UmsAdminService {
    /**
     * 后台用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录成功后生成jwt的token
     */
    String login(String username, String password);

    /**
     * 根据用户id获取用户权限列表
     *
     * @param adminId 后台用户id
     * @return
     */
    List<UmsPermission> listPermissions(Long adminId);

    /**
     * 根据用户名获取用户信息(主要是封装用户信息和权限的UserDetails时，查询用户信息)
     *
     * @param username 用户名
     * @return
     */
    UmsAdmin getAdminByUsername(String username);

    /**
     * 根据用户名生成带有权限信息的UserDetails
     * @param username
     * @return
     */
    UserDetails loadUserByUsername(String username);

    /**
     * 添加后台用户
     *
     * @param umsAdminDTO 用户信息
     * @return
     */
    UmsAdmin save(UmsAdminDTO umsAdminDTO);


}
