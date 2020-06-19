package com.cjs.wymall.service;

import com.cjs.wymall.model.UmsResource;

import java.util.List;

/**
 * @description:
 * @author: cuijunsheng
 * @date: 2020/6/13
 */
public interface UmsResourceService {

    /**
     * 获取所有资源
     * @return
     */
    List<UmsResource> listResources();

    /**
     * 根据后台用户id获取可访问的资源
     * @param adminId 后台用户id
     * @return
     */
    List<UmsResource> listResourcesByAdminId(Long adminId);
}