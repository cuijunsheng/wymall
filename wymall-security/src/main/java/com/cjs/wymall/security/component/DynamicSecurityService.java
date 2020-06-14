package com.cjs.wymall.security.component;

import org.springframework.security.access.ConfigAttribute;

import java.util.Map;

/**
 * @description: 动态权限相关业务类
 * @author: cuijunsheng
 * @date: 2020/6/13
 */
public interface DynamicSecurityService {

    /**
     * 加载资源ANT通配符和资源对应MAP
     */
    Map<String, ConfigAttribute> loadDataSource();
}
