package com.cjs.wymall.dao;

import com.cjs.wymall.model.UmsPermission;

import java.util.List;

/**
 * @description: 自定义用户和权限关系操作Dao
 * @author: cuijunsheng
 * @date: 2020/6/13
 */
public interface UmsPermissionDao {

    /**
     * 根据用户id，获取用户所有权限(包括+-权限)
     * @param adminId 后台用户id
     * @return
     */
    List<UmsPermission> listPermissionsByAdminId(Long adminId);
}
