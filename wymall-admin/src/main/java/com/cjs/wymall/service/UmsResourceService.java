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
}