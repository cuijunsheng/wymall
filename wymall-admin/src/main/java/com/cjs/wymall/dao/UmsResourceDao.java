package com.cjs.wymall.dao;

import com.cjs.wymall.model.UmsResource;

import java.util.List;

/**
 * @description: 自定义资源相关操作Dao
 * @author: cuijunsheng
 * @date: 2020/6/14
 */
public interface UmsResourceDao {

    /**
     * 根据后台用户id获取可访问的资源
     * @param adminId 后台用户id
     * @return
     */
    List<UmsResource> listResourcesByAdminId(Long adminId);
}
