package com.cjs.wymall.dao;

import com.cjs.wymall.model.UmsRole;

import java.util.List;

/**
 * @description: 自定义后台用户角色管理Dao
 * @author: cuijunsheng
 * @date: 2020/6/1
 */
public interface UmsRoleDao {

    /**
     * 根据管理员id获取角色列表
     * @param adminId
     * @return
     */
    List<UmsRole> listRolesByAdminId(Long adminId);

}
