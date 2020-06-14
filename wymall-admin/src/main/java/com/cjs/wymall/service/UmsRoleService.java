package com.cjs.wymall.service;

import com.cjs.wymall.model.UmsRole;

import java.util.List;

/**
 * @description: 后台角色管理Service
 * @author: cuijunsheng
 * @date: 2020/6/13
 */
public interface UmsRoleService {

    /**
     * 根据管理员id获取他对应的角色列表
     * @param adminId 管理员id
     * @return
     */
    List<UmsRole> listRolesByAdminId(Long adminId);
}