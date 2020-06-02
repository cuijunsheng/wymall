package com.cjs.wymall.dao.user;

import com.cjs.wymall.model.UmsPermission;

import java.util.List;

/**
 * @description: 自定义后台用户与角色管理Dao
 * @author: cuijunsheng
 * @date: 2020/6/1
 */
public interface UmsAdminRoleRelationDao {

    /**
     * 根据用户id，获取用户所有权限(包括+-权限)
     * @param adminId 后台用户id
     * @return
     */
    List<UmsPermission> listPermissions(Long adminId);
}
